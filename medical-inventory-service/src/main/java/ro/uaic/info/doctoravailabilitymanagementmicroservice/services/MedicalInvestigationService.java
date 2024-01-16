package ro.uaic.info.doctoravailabilitymanagementmicroservice.services;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.MedicalInvestigationDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.QueryOptions;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers.MedicalInvestigationMapper;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.MedicalInvestigationRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MedicalInvestigationService {

    private final MedicalInvestigationRepository medicalInvestigationRepository;
    private final MedicalInvestigationMapper medicalInvestigationMapper = Mappers.getMapper(MedicalInvestigationMapper.class);
    private final DoctorService doctorService;

    public List<MedicalInvestigationDTO> filterAndSortList(QueryOptions queryOptions) {
        return medicalInvestigationRepository
                .filterAndSortList(queryOptions)
                .stream()
                .map(medicalInvestigationMapper::medicalInvestigationToMedicalInvestigationDTO)
                .collect(Collectors.toList());
    }

    public List<MedicalInvestigationDTO> findAllBySpecializationId(Long specializationId) {
        return medicalInvestigationRepository
                .findAllBySpecializationId(specializationId)
                .stream()
                .map(medicalInvestigationMapper::medicalInvestigationToMedicalInvestigationDTO)
                .sorted(Comparator.comparing(MedicalInvestigationDTO::getInvestigation))
                .collect(Collectors.toList());
    }

    public MedicalInvestigationDTO findById(Long id) {
        return medicalInvestigationRepository
                .findById(id)
                .map(medicalInvestigationMapper::medicalInvestigationToMedicalInvestigationDTO)
                .orElse(null);
    }

    public List<MedicalInvestigationDTO> findAllByDoctorId(Long id) {
        return medicalInvestigationRepository
                .findAllByDoctorId(id)
                .stream()
                .map(medicalInvestigationMapper::medicalInvestigationToMedicalInvestigationDTO)
                .collect(Collectors.toList());
    }
}
