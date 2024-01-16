package ro.uaic.info.doctoravailabilitymanagementmicroservice.services;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.CityDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.QueryOptions;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.City;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers.CityMapper;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.CityRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper = Mappers.getMapper(CityMapper.class);

    public long count() {
        return cityRepository.count();
    }

    public List<CityDTO> filterAndSortList(QueryOptions queryOptions) {
        return cityRepository
                .filterAndSortList(queryOptions)
                .stream()
                .map(cityMapper::cityToCityDTO)
                .collect(Collectors.toList());
    }

    public List<CityDTO> findAll() {
        return cityRepository
                .findAll()
                .stream()
                .map(cityMapper::cityToCityDTO)
                .sorted(Comparator.comparing(CityDTO::getCity))
                .collect(Collectors.toList());
    }

    public CityDTO findById(Long id) {
        return cityRepository
                .findById(id)
                .map(cityMapper::cityToCityDTO)
                .orElse(null);
    }

    public CityDTO findByName(String name) {
        return cityRepository
                .findByCity(name)
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
