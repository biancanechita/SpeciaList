package ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.City;

public interface CityRepository extends JpaRepository<City, Long> {
}