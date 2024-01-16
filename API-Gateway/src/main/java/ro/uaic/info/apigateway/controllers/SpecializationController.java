package ro.uaic.info.apigateway.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.uaic.info.apigateway.services.SpecializationService;
import ro.uaic.info.medicalinventoryserviceapi.SpecializationDto;

@RequestMapping("/specializations")
@RestController
@CrossOrigin
public class SpecializationController extends AbstractController<SpecializationDto> {

    public SpecializationController() {
        this.setService(new SpecializationService());
    }
}
