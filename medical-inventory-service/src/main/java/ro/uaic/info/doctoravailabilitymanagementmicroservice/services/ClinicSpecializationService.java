package ro.uaic.info.doctoravailabilitymanagementmicroservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.ClinicSpecialization;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.ClinicSpecializationRepository;

@RequiredArgsConstructor
@Service
public class ClinicSpecializationService {

    private final ClinicSpecializationRepository clinicSpecializationRepository;

    public void save(Long clinicId, Long specializationId) {
        ClinicSpecialization clinicSpecialization = new ClinicSpecialization();
        clinicSpecialization.setClinicId(clinicId);
        clinicSpecialization.setSpecializationId(specializationId);
        clinicSpecializationRepository.save(clinicSpecialization);
    }
}
