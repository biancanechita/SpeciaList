package ro.uaic.info.appointmentschedulingservice.mappers;

import org.mapstruct.Mapper;
import ro.uaic.info.appointmentschedulingservice.AppointmentDto;
import ro.uaic.info.appointmentschedulingservice.entities.Appointment;

@Mapper
public interface AppointmentMapper {

    AppointmentDto appointmentToAppointmentDto(Appointment appointment);

    Appointment appointmentDtoToAppointment(AppointmentDto appointmentDto);
}
