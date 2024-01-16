package ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.DoctorInvestigationExpense;

import java.util.List;

public interface DoctorInvestigationExpenseRepository extends JpaRepository<DoctorInvestigationExpense, Long>, DoctorInvestigationExpenseCustomRepository {

    List<DoctorInvestigationExpense> findAllByInvestigationId(Long investigationId);
}
