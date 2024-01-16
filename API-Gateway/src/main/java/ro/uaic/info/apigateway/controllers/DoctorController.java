package ro.uaic.info.apigateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.apigateway.services.DoctorService;
import ro.uaic.info.medicalinventoryserviceapi.DoctorDto;

@RequestMapping("/doctors")
@RestController
@CrossOrigin
public class DoctorController extends AbstractController<DoctorDto> {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
        this.setService(doctorService);
    }

    @GetMapping("/{id}/get-full-name")
    public String getFullName(@PathVariable("id") Long id) {
        return doctorService.getFullName(id);
    }
}