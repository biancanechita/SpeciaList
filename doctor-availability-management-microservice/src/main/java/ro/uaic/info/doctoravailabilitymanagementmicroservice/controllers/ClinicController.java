package ro.uaic.info.doctoravailabilitymanagementmicroservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.ClinicDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.services.ClinicService;

import java.util.List;

@RestController
@RequestMapping("/clinics")
@RequiredArgsConstructor
public class ClinicController {

    private final ClinicService clinicService;

    @GetMapping
    public List<ClinicDTO> all() {
        return clinicService.findAll();
    }

    @GetMapping("/{id}")
    public ClinicDTO one(@PathVariable("id") Long id) {
        return clinicService.findById(id);
    }
}
