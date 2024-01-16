package ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long>, DoctorCustomRepository {

    @Query(value = "SELECT first_name AS firstName, last_name AS lastName FROM doctors WHERE id = :id", nativeQuery = true)
    Optional<FullName> getFullName(@Param("id") Long id);

    interface FullName {
        String getFirstName();

        String getLastName();
    }

}
