package ro.uaic.info.appointmentschedulingservice.mappers;

import javax.annotation.processing.Generated;
import ro.uaic.info.appointmentschedulingservice.AppointmentDto;
import ro.uaic.info.appointmentschedulingservice.entities.Appointment;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-27T13:26:03+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
public class AppointmentMapperImpl implements AppointmentMapper {

    @Override
    public AppointmentDto appointmentToAppointmentDto(Appointment appointment) {
        if ( appointment == null ) {
            return null;
        }

        AppointmentDto appointmentDto = new AppointmentDto();

        appointmentDto.setId( appointment.getId() );
        appointmentDto.setPatientId( appointment.getPatientId() );
        appointmentDto.setDoctorId( appointment.getDoctorId() );
        appointmentDto.setMedicalInvestigationId( appointment.getMedicalInvestigationId() );
        appointmentDto.setDate( appointment.getDate() );
        appointmentDto.setStartTime( appointment.getStartTime() );

        return appointmentDto;
    }

    @Override
    public Appointment appointmentDtoToAppointment(AppointmentDto appointmentDto) {
        if ( appointmentDto == null ) {
            return null;
        }

        Appointment appointment = new Appointment();

        appointment.setId( appointmentDto.getId() );
        appointment.setPatientId( appointmentDto.getPatientId() );
        appointment.setDoctorId( appointmentDto.getDoctorId() );
        appointment.setMedicalInvestigationId( appointmentDto.getMedicalInvestigationId() );
        appointment.setDate( appointmentDto.getDate() );
        appointment.setStartTime( appointmentDto.getStartTime() );

        return appointment;
    }
}
