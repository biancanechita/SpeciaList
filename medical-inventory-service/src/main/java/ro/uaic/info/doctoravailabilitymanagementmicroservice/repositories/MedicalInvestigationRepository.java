package ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.MedicalInvestigation;

import java.util.List;

public interface MedicalInvestigationRepository extends JpaRepository<MedicalInvestigation, Long>, MedicalInvestigationCustomRepository {

    List<MedicalInvestigation> findAllBySpecializationId(Long specializationId);

    @Query(value = "SELECT * FROM investigations " +
            "WHERE id in  (SELECT investigation_id " +
            "FROM doctor_investigations_expense WHERE doctor_id = :id)", nativeQuery = true)
    List<MedicalInvestigation> findAllByDoctorId(@Param("id") Long id);
}
