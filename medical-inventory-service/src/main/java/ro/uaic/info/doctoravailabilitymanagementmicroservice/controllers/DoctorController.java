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
@CrossOrigin
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

    @PostMapping("/find-all-by-specialization-id-and-city-id")
    public List<Long> findAllBySpecializationIdAndCityId(@RequestBody QueryOptions queryOptions) {
        return doctorService.findAllBySpecializationIdAndCityId(queryOptions);
    }

    @PostMapping
    public void save(@RequestBody DoctorDTO doctorDTO) {
        doctorService.save(doctorDTO);
    }

    @GetMapping("/{id}/get-full-name")
    public String getFullName(@PathVariable("id") Long id) {
        return doctorService.getFullName(id);
    }
}
