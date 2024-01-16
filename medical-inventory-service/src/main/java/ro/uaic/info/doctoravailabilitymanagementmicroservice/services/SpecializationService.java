package ro.uaic.info.doctoravailabilitymanagementmicroservice.services;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.QueryOptions;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.SpecializationDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Specialization;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers.SpecializationMapper;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.SpecializationRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SpecializationService {

    private final SpecializationRepository specializationRepository;
    private final SpecializationMapper specializationMapper = Mappers.getMapper(SpecializationMapper.class);

    public long count() {
        return specializationRepository.count();
    }

    public List<SpecializationDTO> filterAndSortList(QueryOptions queryOptions) {
        return specializationRepository
                .filterAndSortList(queryOptions)
                .stream()
                .map(specializationMapper::specializationToSpecializationDTO)
                .collect(Collectors.toList());
    }

    public List<SpecializationDTO> findAll() {
        return specializationRepository
                .findAll()
                .stream()
                .map(specializationMapper::specializationToSpecializationDTO)
                .sorted(Comparator.comparing(SpecializationDTO::getSpecialization))
                .collect(Collectors.toList());
    }

    public SpecializationDTO findById(Long id) {
        return specializationRepository
                .findById(id)
                .map(specializationMapper::specializationToSpecializationDTO)
                .orElse(null);
    }

    public SpecializationDTO save(SpecializationDTO specializationDTO) {
        Specialization specialization = specializationMapper.specializationDTOToSpecialization(specializationDTO);
        return Optional.of(specialization)
                .map(specializationRepository::save)
                .map(specializationMapper::specializationToSpecializationDTO)
                .orElse(null);
    }

    public void deleteById(Long id) {
        specializationRepository.deleteById(id);
    }
}
