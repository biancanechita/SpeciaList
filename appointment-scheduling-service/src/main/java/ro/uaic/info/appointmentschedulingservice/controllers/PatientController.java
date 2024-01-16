package ro.uaic.info.appointmentschedulingservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.uaic.info.appointmentschedulingservice.beans.SensitivePersonalData;
import ro.uaic.info.appointmentschedulingservice.services.PatientService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/patients")
@CrossOrigin
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public Long create(@RequestBody SensitivePersonalData personalData) {
        return patientService.create(personalData);
    }
}
