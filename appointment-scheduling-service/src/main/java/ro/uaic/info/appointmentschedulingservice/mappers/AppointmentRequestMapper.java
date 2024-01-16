package ro.uaic.info.appointmentschedulingservice.mappers;

import org.mapstruct.Mapper;
import ro.uaic.info.appointmentschedulingservice.AppointmentRequest;
import ro.uaic.info.appointmentschedulingservice.entities.AppointmentReq;

@Mapper
public interface AppointmentRequestMapper {

    AppointmentReq appointmentRequestToAppointmentReq(AppointmentRequest appointmentRequest);
}
