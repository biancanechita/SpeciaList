package ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers;

import org.mapstruct.Mapper;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.SpecializationDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Specialization;

@Mapper
public interface SpecializationMapper {

    SpecializationDTO specializationToSpecializationDTO(Specialization specialization);

    Specialization specializationDTOToSpecialization(SpecializationDTO specializationDTO);
}
