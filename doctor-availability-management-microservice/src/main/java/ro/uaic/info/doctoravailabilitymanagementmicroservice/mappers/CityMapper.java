package ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers;

import org.mapstruct.Mapper;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.CityDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.City;

@Mapper
public interface CityMapper {

    CityDTO cityToCityDTO(City city);

    City cityDTOToCity(CityDTO cityDTO);
}
