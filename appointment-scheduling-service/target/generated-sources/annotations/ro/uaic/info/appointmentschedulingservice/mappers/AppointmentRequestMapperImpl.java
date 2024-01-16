package ro.uaic.info.appointmentschedulingservice.mappers;

import javax.annotation.processing.Generated;
import ro.uaic.info.appointmentschedulingservice.AppointmentRequest;
import ro.uaic.info.appointmentschedulingservice.entities.AppointmentReq;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-27T13:26:03+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
public class AppointmentRequestMapperImpl implements AppointmentRequestMapper {

    @Override
    public AppointmentReq appointmentRequestToAppointmentReq(AppointmentRequest appointmentRequest) {
        if ( appointmentRequest == null ) {
            return null;
        }

        AppointmentReq appointmentReq = new AppointmentReq();

        appointmentReq.setDoctorId( appointmentRequest.getDoctorId() );
        appointmentReq.setMedicalInvestigationId( appointmentRequest.getMedicalInvestigationId() );
        appointmentReq.setFirstName( appointmentRequest.getFirstName() );
        appointmentReq.setLastName( appointmentRequest.getLastName() );
        appointmentReq.setEmail( appointmentRequest.getEmail() );
        appointmentReq.setPhoneNumber( appointmentRequest.getPhoneNumber() );

        return appointmentReq;
    }
}
