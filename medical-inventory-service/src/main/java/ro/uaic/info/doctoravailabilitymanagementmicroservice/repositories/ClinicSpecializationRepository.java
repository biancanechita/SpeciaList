package ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.ClinicSpecialization;

public interface ClinicSpecializationRepository extends JpaRepository<ClinicSpecialization, Long> {
}
