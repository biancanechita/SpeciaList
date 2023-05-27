package ro.uaic.info.doctoravailabilitymanagementmicroservice.services;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.CityDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.City;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers.CityMapper;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.CityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper = Mappers.getMapper(CityMapper.class);

    public List<CityDTO> findAll() {
        return cityRepository
                .findAll()
                .stream()
                .map(cityMapper::cityToCityDTO)
                .collect(Collectors.toList());
    }

    public CityDTO findById(Long id) {
        return cityRepository
                .findById(id)
                .map(cityMapper::cityToCityDTO)
                .orElse(null);
    }

    public CityDTO save(CityDTO cityDTO) {
        City city = cityMapper.cityDTOToCity(cityDTO);
        return Optional.of(city)
                .map(cityRepository::save)
                .map(cityMapper::cityToCityDTO)
                .orElse(null);
    }

    public void deleteById(Long id) {
        cityRepository.deleteById(id);
    }
}
