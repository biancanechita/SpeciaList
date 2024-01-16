package ro.uaic.info.appointmentschedulingservice.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ro.uaic.info.appointmentschedulingservice.AppointmentDto;
import ro.uaic.info.appointmentschedulingservice.AppointmentRequest;
import ro.uaic.info.appointmentschedulingservice.beans.AvailableTimes;
import ro.uaic.info.appointmentschedulingservice.beans.QueryOptions;
import ro.uaic.info.appointmentschedulingservice.beans.SchedulingParameters;
import ro.uaic.info.appointmentschedulingservice.clients.MedicalInventoryServiceClient;
import ro.uaic.info.appointmentschedulingservice.entities.Appointment;
import ro.uaic.info.appointmentschedulingservice.mappers.AppointmentMapper;
import ro.uaic.info.appointmentschedulingservice.mappers.AppointmentRequestMapper;
import ro.uaic.info.appointmentschedulingservice.repositories.AppointmentRepository;
import ro.uaic.info.appointmentschedulingservice.repositories.AppointmentRequestRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AppointmentService {

    @Data
    private static class TimeRange {
        private final LocalTime startTime;
        private final LocalTime endTime;
    }

    private final AppointmentRepository appointmentRepository;
    private final AppointmentRequestRepository appointmentRequestRepository;
    private final AppointmentMapper appointmentMapper = Mappers.getMapper(AppointmentMapper.class);
    private final AppointmentRequestMapper appointmentRequestMapper = Mappers.getMapper(AppointmentRequestMapper.class);
    private final MedicalInventoryServiceClient medicalInventoryServiceClient;
    private final PatientService patientService;

    private List<TimeRange> generateTimeRanges() {
        List<TimeRange> timeRanges = new ArrayList<>();

        for (int hour = 9; hour <= 18; hour++) {
            for (int minute = 0; minute <= 30 && (hour < 18 || minute == 0); minute = minute + 30) {
                LocalTime localTime = LocalTime.of(hour, minute);

                TimeRange timeRange = new TimeRange(localTime, localTime.plusMinutes(30));
                timeRanges.add(timeRange);
            }
        }

        return timeRanges;
    }

    public List<AvailableTimes> fetchAvailableTimes(SchedulingParameters schedulingParameters) {
        List<Long> doctorIds = new ArrayList<>();

        if (schedulingParameters.getDoctorId() != null) {
            doctorIds.add(schedulingParameters.getDoctorId());
        } else {
            QueryOptions queryOptions = new QueryOptions();

            Map<String, String> map = new HashMap<>();
            map.put("specializationId", schedulingParameters.getSpecializationId() != null ? String.valueOf(schedulingParameters.getSpecializationId()) : "");
            map.put("cityId", schedulingParameters.getCityId() != null ? String.valueOf(schedulingParameters.getCityId()) : "");

            queryOptions.setFilters(map);
            queryOptions.setSortingList(new ArrayList<>());

            doctorIds = medicalInventoryServiceClient.findAllDoctorsBySpecializationIdAndCityId(queryOptions);
        }

        List<AvailableTimes> availableTimesList = new ArrayList<>();

        for (Object o : doctorIds) {
            Long doctorId = ((Integer) o).longValue();

            boolean key = false;
            LocalDate dateAfter = schedulingParameters.getDateAfter();

            List<Appointment> appointments = appointmentRepository
                    .findAllByDoctorIdAndDateAfterOrderByDateAscStartTimeAsc(doctorId, dateAfter);


            List<TimeRange> timeRanges = generateTimeRanges();

            List<LocalTime> startTimes1 = new ArrayList<>();
            for (TimeRange timeRange : timeRanges
            ) {
                startTimes1.add(timeRange.getStartTime());
            }

            for (Appointment appointment : appointments
            ) {
                if (!appointment.getDate().equals(dateAfter)) {
                    if (!timeRanges.isEmpty()) {
                        AvailableTimes availableTimes = new AvailableTimes();

                        availableTimes.setDoctorId(doctorId);
                        availableTimes.setDate(dateAfter);

                        List<LocalTime> startTimes = new ArrayList<>();
                        for (TimeRange timeRange : timeRanges
                        ) {
                            startTimes.add(timeRange.getStartTime());
                        }

                        availableTimes.setStartTimes(startTimes);

                        availableTimesList.add(availableTimes);
                        key = true;
                        break;
                    } else {
                        if (appointment.getDate().equals(dateAfter.plusDays(1))) {
                            dateAfter = appointment.getDate();

                            timeRanges = generateTimeRanges();

                            TimeRange timeRange = new TimeRange(appointment.getStartTime(), appointment.getEndTime());
                            timeRanges.remove(timeRange);
                        } else {
                            dateAfter = dateAfter.plusDays(1);

                            AvailableTimes availableTimes1 = new AvailableTimes();

                            availableTimes1.setDoctorId(doctorId);
                            availableTimes1.setDate(dateAfter);
                            availableTimes1.setStartTimes(startTimes1);

                            availableTimesList.add(availableTimes1);
                            key = true;
                            break;
                        }
                    }
                } else {
                    TimeRange timeRange = new TimeRange(appointment.getStartTime(), appointment.getEndTime());
                    timeRanges.remove(timeRange);
                }
            }

            if (!key && !timeRanges.isEmpty()) {
                AvailableTimes availableTimes = new AvailableTimes();

                availableTimes.setDoctorId(doctorId);
                availableTimes.setDate(dateAfter);

                List<LocalTime> startTimes = new ArrayList<>();
                for (TimeRange timeRange : timeRanges
                ) {
                    startTimes.add(timeRange.getStartTime());
                }

                availableTimes.setStartTimes(startTimes);
                availableTimesList.add(availableTimes);
            }
        }
        return availableTimesList;
    }

    public List<AppointmentDto> findAllByPatientId(Long userId) {
        Long patientId = patientService.findByUserId(userId);

        return appointmentRepository
                .findAllByPatientIdOrderByDate(patientId)
                .stream()
                .map(appointmentMapper::appointmentToAppointmentDto)
                .collect(Collectors.toList());
    }

    public AppointmentDto findById(Long id) {
        return appointmentRepository
                .findById(id)
                .map(appointmentMapper::appointmentToAppointmentDto)
                .orElse(null);
    }

    public AppointmentDto save(AppointmentDto appointmentDto) {
        Long patientId = patientService.findByUserId(appointmentDto.getPatientId());

        Appointment appointment = appointmentMapper.appointmentDtoToAppointment(appointmentDto);
        appointment.setPatientId(patientId);
        appointment.setEndTime(appointment.getStartTime().plusMinutes(30));

        return appointmentMapper.appointmentToAppointmentDto(appointmentRepository.save(appointment));
    }

    public void scheduleVisitorMode(AppointmentRequest appointmentRequest) {
        appointmentRequestRepository
                .save(appointmentRequestMapper
                        .appointmentRequestToAppointmentReq(appointmentRequest));
    }

    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }
}
