package ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Clinic;

import java.util.List;

public interface ClinicRepository extends JpaRepository<Clinic, Long>, ClinicCustomRepository {

    List<Clinic> findByCityId(Long cityId);
}
