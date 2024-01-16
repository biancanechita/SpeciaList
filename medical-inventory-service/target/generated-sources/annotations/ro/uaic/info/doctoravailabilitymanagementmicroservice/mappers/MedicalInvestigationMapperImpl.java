package ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers;

import javax.annotation.processing.Generated;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.MedicalInvestigationDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.MedicalInvestigation;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-27T13:25:53+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
public class MedicalInvestigationMapperImpl implements MedicalInvestigationMapper {

    @Override
    public MedicalInvestigationDTO medicalInvestigationToMedicalInvestigationDTO(MedicalInvestigation medicalInvestigation) {
        if ( medicalInvestigation == null ) {
            return null;
        }

        MedicalInvestigationDTO medicalInvestigationDTO = new MedicalInvestigationDTO();

        medicalInvestigationDTO.setId( medicalInvestigation.getId() );
        medicalInvestigationDTO.setInvestigation( medicalInvestigation.getInvestigation() );
        medicalInvestigationDTO.setSpecializationId( medicalInvestigation.getSpecializationId() );

        return medicalInvestigationDTO;
    }

    @Override
    public MedicalInvestigation medicalInvestigationDTOToMedicalInvestigation(MedicalInvestigationDTO medicalInvestigationDTO) {
        if ( medicalInvestigationDTO == null ) {
            return null;
        }

        MedicalInvestigation medicalInvestigation = new MedicalInvestigation();

        medicalInvestigation.setId( medicalInvestigationDTO.getId() );
        medicalInvestigation.setInvestigation( medicalInvestigationDTO.getInvestigation() );
        medicalInvestigation.setSpecializationId( medicalInvestigationDTO.getSpecializationId() );

        return medicalInvestigation;
    }
}
