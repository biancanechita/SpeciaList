package ro.uaic.info.apigateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.apigateway.QueryOptions;
import ro.uaic.info.apigateway.services.MedicalInvestigationService;
import ro.uaic.info.appointmentschedulingservice.MedicalInvestigationDto;

import java.util.List;

@RequestMapping("/medical-investigations")
@RestController
@CrossOrigin
public class MedicalInvestigationController extends AbstractController<MedicalInvestigationDto> {

    private final MedicalInvestigationService medicalInvestigationService;

    @Autowired
    public MedicalInvestigationController(MedicalInvestigationService medicalInvestigationService) {
        this.medicalInvestigationService = medicalInvestigationService;
        this.service = medicalInvestigationService;
    }

    @GetMapping("/find-all-by-doctor-id/{id}")
    public List<MedicalInvestigationDto> findAllByDoctorId(@PathVariable("id") Long id) {
        return medicalInvestigationService.findAllByDoctorId(id);
    }

    @PostMapping("/filter-and-sort-list")
    public List<MedicalInvestigationDto> getAll(@RequestBody QueryOptions queryOptions) {
        return service.filterAndSortList(queryOptions);
    }
}
