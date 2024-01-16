package ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers;

import javax.annotation.processing.Generated;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.DoctorDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Doctor;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-27T13:25:53+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
public class DoctorMapperImpl implements DoctorMapper {

    @Override
    public DoctorDTO doctorToDoctorDTO(Doctor doctor) {
        if ( doctor == null ) {
            return null;
        }

        DoctorDTO doctorDTO = new DoctorDTO();

        doctorDTO.setId( doctor.getId() );
        doctorDTO.setFirstName( doctor.getFirstName() );
        doctorDTO.setLastName( doctor.getLastName() );
        doctorDTO.setSpecializationId( doctor.getSpecializationId() );
        doctorDTO.setContactInformation( doctor.getContactInformation() );
        doctorDTO.setBio( doctor.getBio() );
        doctorDTO.setAverageRating( doctor.getAverageRating() );
        doctorDTO.setTotalReviews( doctor.getTotalReviews() );
        doctorDTO.setPhotoUrl( doctor.getPhotoUrl() );
        doctorDTO.setClinicId( doctor.getClinicId() );

        return doctorDTO;
    }

    @Override
    public Doctor doctorDTOToDoctor(DoctorDTO doctorDTO) {
        if ( doctorDTO == null ) {
            return null;
        }

        Doctor doctor = new Doctor();

        doctor.setId( doctorDTO.getId() );
        doctor.setFirstName( doctorDTO.getFirstName() );
        doctor.setLastName( doctorDTO.getLastName() );
        doctor.setSpecializationId( doctorDTO.getSpecializationId() );
        doctor.setContactInformation( doctorDTO.getContactInformation() );
        doctor.setBio( doctorDTO.getBio() );
        doctor.setAverageRating( doctorDTO.getAverageRating() );
        doctor.setTotalReviews( doctorDTO.getTotalReviews() );
        doctor.setPhotoUrl( doctorDTO.getPhotoUrl() );
        doctor.setClinicId( doctorDTO.getClinicId() );

        return doctor;
    }
}
