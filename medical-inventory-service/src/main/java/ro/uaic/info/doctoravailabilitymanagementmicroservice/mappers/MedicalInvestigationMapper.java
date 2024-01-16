package ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers;

import org.mapstruct.Mapper;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.MedicalInvestigationDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.MedicalInvestigation;

@Mapper
public interface MedicalInvestigationMapper {

    MedicalInvestigationDTO medicalInvestigationToMedicalInvestigationDTO(MedicalInvestigation medicalInvestigation);

    MedicalInvestigation medicalInvestigationDTOToMedicalInvestigation(MedicalInvestigationDTO medicalInvestigationDTO);
}
