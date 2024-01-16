package ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers;

import javax.annotation.processing.Generated;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.SpecializationDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Specialization;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-27T13:25:53+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
public class SpecializationMapperImpl implements SpecializationMapper {

    @Override
    public SpecializationDTO specializationToSpecializationDTO(Specialization specialization) {
        if ( specialization == null ) {
            return null;
        }

        SpecializationDTO specializationDTO = new SpecializationDTO();

        specializationDTO.setId( specialization.getId() );
        specializationDTO.setSpecialization( specialization.getSpecialization() );
        specializationDTO.setDescription( specialization.getDescription() );

        return specializationDTO;
    }

    @Override
    public Specialization specializationDTOToSpecialization(SpecializationDTO specializationDTO) {
        if ( specializationDTO == null ) {
            return null;
        }

        Specialization specialization = new Specialization();

        specialization.setId( specializationDTO.getId() );
        specialization.setSpecialization( specializationDTO.getSpecialization() );
        specialization.setDescription( specializationDTO.getDescription() );

        return specialization;
    }
}
