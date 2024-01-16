package ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers;

import javax.annotation.processing.Generated;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.CityDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.City;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-27T13:25:53+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
public class CityMapperImpl implements CityMapper {

    @Override
    public CityDTO cityToCityDTO(City city) {
        if ( city == null ) {
            return null;
        }

        CityDTO cityDTO = new CityDTO();

        cityDTO.setId( city.getId() );
        cityDTO.setCity( city.getCity() );

        return cityDTO;
    }

    @Override
    public City cityDTOToCity(CityDTO cityDTO) {
        if ( cityDTO == null ) {
            return null;
        }

        City city = new City();

        city.setId( cityDTO.getId() );
        city.setCity( cityDTO.getCity() );

        return city;
    }
}
