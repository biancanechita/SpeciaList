package ro.uaic.info.doctoravailabilitymanagementmicroservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.DoctorDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.QueryOptions;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.services.DoctorService;

import java.util.List;

@RestController
@RequestMapping("/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public List<DoctorDTO> all() {
        return doctorService.findAll();
    }

    @GetMapping("/{id}")
    public DoctorDTO one(@PathVariable("id") Long id) {
        return doctorService.findById(id);
    }

    @PostMapping("/filter-and-sort-list")
    public List<DoctorDTO> filterAndSortList(@RequestBody QueryOptions queryOptions) {
        return doctorService.filterAndSortList(queryOptions);
    }

    @PostMapping
    public void save(@RequestBody DoctorDTO doctorDTO) {
        doctorService.save(doctorDTO);
    }
}
