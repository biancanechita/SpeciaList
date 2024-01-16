package ro.uaic.info.doctoravailabilitymanagementmicroservice.services;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.DoctorInvestigationExpenseDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.QueryOptions;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.DoctorInvestigationExpense;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers.DoctorInvestigationExpenseMapper;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.DoctorInvestigationExpenseRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DoctorInvestigationExpenseService {

    private final DoctorInvestigationExpenseRepository doctorInvestigationExpenseRepository;
    private final DoctorInvestigationExpenseMapper doctorInvestigationExpenseMapper = Mappers.getMapper(DoctorInvestigationExpenseMapper.class);

    public List<DoctorInvestigationExpenseDTO> filterAndSortList(QueryOptions queryOptions) {
        return doctorInvestigationExpenseRepository
                .filterAndSortList(queryOptions)
                .stream()
                .map(doctorInvestigationExpenseMapper::doctorInvestigationExpenseToDoctorInvestigationExpenseDTO)
                .collect(Collectors.toList());
    }

    public List<DoctorInvestigationExpenseDTO> findAllByInvestigationId(Long investigationId) {
        return doctorInvestigationExpenseRepository
                .findAllByInvestigationId(investigationId)
                .stream()
                .map(doctorInvestigationExpenseMapper::doctorInvestigationExpenseToDoctorInvestigationExpenseDTO)
                .collect(Collectors.toList());
    }
}
