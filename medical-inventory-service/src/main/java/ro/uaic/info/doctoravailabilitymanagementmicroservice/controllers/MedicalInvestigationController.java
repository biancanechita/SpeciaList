package ro.uaic.info.doctoravailabilitymanagementmicroservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.MedicalInvestigationDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.QueryOptions;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.MedicalInvestigation;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.services.MedicalInvestigationService;

import java.util.List;

@RestController
@RequestMapping("/medical-investigations")
@RequiredArgsConstructor
@CrossOrigin
public class MedicalInvestigationController {

    private final MedicalInvestigationService medicalInvestigationService;

    @GetMapping("/find-all-by-specialization-id/{id}")
    public List<MedicalInvestigationDTO> findAllBySpecializationId(@PathVariable("id") Long specializationId) {
        return medicalInvestigationService.findAllBySpecializationId(specializationId);
    }

    @PostMapping("/filter-and-sort-list")
    public List<MedicalInvestigationDTO> filterAndSortList(@RequestBody QueryOptions queryOptions) {
        return medicalInvestigationService.filterAndSortList(queryOptions);
    }

    @GetMapping("/{id}")
    public MedicalInvestigationDTO findById(@PathVariable("id") Long id) {
        return medicalInvestigationService.findById(id);
    }

    @GetMapping("/find-all-by-doctor-id/{id}")
    public List<MedicalInvestigationDTO> findAllByDoctorId(@PathVariable("id") Long id) {
        return medicalInvestigationService.findAllByDoctorId(id);
    }
}
