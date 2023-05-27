package ro.uaic.info.doctoravailabilitymanagementmicroservice.services;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.ClinicDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Clinic;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers.ClinicMapper;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.ClinicRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;
    private final ClinicMapper clinicMapper = Mappers.getMapper(ClinicMapper.class);

    public long count() {
        return clinicRepository.count();
    }

    public List<ClinicDTO> findAll() {
        return clinicRepository
                .findAll()
                .stream()
                .map(clinicMapper::clinicToClinicDTO)
                .collect(Collectors.toList());
    }

    public ClinicDTO findById(Long id) {
        return clinicRepository
                .findById(id)
                .map(clinicMapper::clinicToClinicDTO)
                .orElse(null);
    }

    public ClinicDTO save(ClinicDTO clinicDTO) {
        Clinic clinic = clinicMapper.clinicDTOToClinic(clinicDTO);
        return Optional.of(clinic)
                .map(clinicRepository::save)
                .map(clinicMapper::clinicToClinicDTO)
                .orElse(null);
    }

    public void deleteById(Long id) {
        clinicRepository.deleteById(id);
    }
}
