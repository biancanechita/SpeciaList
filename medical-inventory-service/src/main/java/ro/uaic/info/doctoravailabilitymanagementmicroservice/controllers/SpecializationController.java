package ro.uaic.info.doctoravailabilitymanagementmicroservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.QueryOptions;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.SpecializationDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.services.SpecializationService;

import java.util.List;

@RestController
@RequestMapping("/specializations")
@RequiredArgsConstructor
@CrossOrigin
public class SpecializationController {

    private final SpecializationService specializationService;

    @GetMapping
    public List<SpecializationDTO> all() {
        return specializationService.findAll();
    }

    @GetMapping("/{id}")
    public SpecializationDTO one(@PathVariable("id") Long id) {
        return specializationService.findById(id);
    }

    @PostMapping("/filter-and-sort-list")
    public List<SpecializationDTO> filterAndSortList(@RequestBody QueryOptions queryOptions) {
        return specializationService.filterAndSortList(queryOptions);
    }
}
