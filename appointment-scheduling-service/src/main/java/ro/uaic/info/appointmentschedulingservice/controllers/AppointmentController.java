package ro.uaic.info.appointmentschedulingservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.appointmentschedulingservice.AppointmentDto;
import ro.uaic.info.appointmentschedulingservice.AppointmentRequest;
import ro.uaic.info.appointmentschedulingservice.beans.AvailableTimes;
import ro.uaic.info.appointmentschedulingservice.beans.SchedulingParameters;
import ro.uaic.info.appointmentschedulingservice.services.AppointmentService;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping("/{id}")
    public List<AppointmentDto> findAllByPatientId(@PathVariable("id") Long patientId) {
        return appointmentService.findAllByPatientId(patientId);
    }

    @PostMapping("/fetch-available-times")
    public List<AvailableTimes> fetchAvailableTimes(@RequestBody SchedulingParameters schedulingParameters) {
        return appointmentService.fetchAvailableTimes(schedulingParameters);
    }

    @PostMapping("/restricted")
    public AppointmentDto save(@RequestBody AppointmentDto appointmentDto) {
        return appointmentService.save(appointmentDto);
    }

    @PostMapping("/req")
    public void scheduleVisitorMode(@RequestBody AppointmentRequest appointmentRequest) {
        appointmentService.scheduleVisitorMode(appointmentRequest);
    }

    @PutMapping("/{id}")
    public AppointmentDto update(@RequestBody AppointmentDto appointmentDto) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        appointmentService.deleteById(id);
    }
}
