package ro.uaic.info.apigateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.uaic.info.apigateway.services.ClinicService;
import ro.uaic.info.medicalinventoryserviceapi.ClinicDto;

@RequestMapping("/clinics")
@RestController
@CrossOrigin
public class ClinicController extends AbstractController<ClinicDto> {

    @Autowired
    public ClinicController(ClinicService clinicService) {
        this.service = clinicService;
    }
}
