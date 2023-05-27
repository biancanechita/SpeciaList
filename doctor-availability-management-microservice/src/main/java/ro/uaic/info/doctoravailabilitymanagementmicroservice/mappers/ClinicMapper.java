package ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers;

import org.mapstruct.Mapper;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.ClinicDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Clinic;

@Mapper
public interface ClinicMapper {

    ClinicDTO clinicToClinicDTO(Clinic clinic);

    Clinic clinicDTOToClinic(ClinicDTO clinicDTO);
}
