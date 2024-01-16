package ro.uaic.info.doctoravailabilitymanagementmicroservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.CityDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.QueryOptions;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.services.CityService;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
@CrossOrigin
public class CityController {

    private final CityService cityService;

    @GetMapping
    public List<CityDTO> all() {
        return cityService.findAll();
    }

    @GetMapping("/{id}")
    public CityDTO one(@PathVariable("id") Long id) {
        return cityService.findById(id);
    }

    @PostMapping("/filter-and-sort-list")
    public List<CityDTO> filterAndSortList(@RequestBody QueryOptions queryOptions) {
        return cityService.filterAndSortList(queryOptions);
    }
}
