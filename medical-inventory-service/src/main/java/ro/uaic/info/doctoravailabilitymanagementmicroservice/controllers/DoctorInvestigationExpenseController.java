package ro.uaic.info.doctoravailabilitymanagementmicroservice.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.DoctorInvestigationExpenseDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.QueryOptions;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.services.DoctorInvestigationExpenseService;

import java.util.List;

@RestController
@RequestMapping("/doctor-investigation-expense")
@RequiredArgsConstructor
@CrossOrigin
public class DoctorInvestigationExpenseController {

    private final DoctorInvestigationExpenseService doctorInvestigationExpenseService;

    @GetMapping("/{id}")
    public List<DoctorInvestigationExpenseDTO> findAllByInvestigationId(@PathVariable("id") Long investigationId) {
        return doctorInvestigationExpenseService.findAllByInvestigationId(investigationId);
    }

    @PostMapping("/filter-and-sort-list")
    public List<DoctorInvestigationExpenseDTO> filterAndSortList(@RequestBody QueryOptions queryOptions) {
        return doctorInvestigationExpenseService.filterAndSortList(queryOptions);
    }
}
