package ro.uaic.info.appointmentschedulingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.uaic.info.appointmentschedulingservice.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(value = "SELECT id FROM patients WHERE user_id = :userId", nativeQuery = true)
    Long findByUserId(@Param("userId") Long userId);
}
