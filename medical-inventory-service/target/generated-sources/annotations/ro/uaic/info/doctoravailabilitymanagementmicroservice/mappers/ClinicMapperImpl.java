package ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers;

import javax.annotation.processing.Generated;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.ClinicDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Clinic;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-27T13:25:53+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
public class ClinicMapperImpl implements ClinicMapper {

    @Override
    public ClinicDTO clinicToClinicDTO(Clinic clinic) {
        if ( clinic == null ) {
            return null;
        }

        ClinicDTO clinicDTO = new ClinicDTO();

        clinicDTO.setId( clinic.getId() );
        clinicDTO.setClinic( clinic.getClinic() );
        clinicDTO.setAddress( clinic.getAddress() );
        clinicDTO.setCityId( clinic.getCityId() );
        clinicDTO.setContactInformation( clinic.getContactInformation() );

        return clinicDTO;
    }

    @Override
    public Clinic clinicDTOToClinic(ClinicDTO clinicDTO) {
        if ( clinicDTO == null ) {
            return null;
        }

        Clinic clinic = new Clinic();

        clinic.setId( clinicDTO.getId() );
        clinic.setClinic( clinicDTO.getClinic() );
        clinic.setAddress( clinicDTO.getAddress() );
        clinic.setCityId( clinicDTO.getCityId() );
        clinic.setContactInformation( clinicDTO.getContactInformation() );

        return clinic;
    }
}
