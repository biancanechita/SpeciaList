package ro.uaic.info.apigateway.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.uaic.info.apigateway.services.CityService;
import ro.uaic.info.medicalinventoryserviceapi.CityDto;

@RequestMapping("/cities")
@RestController
@CrossOrigin
public class CityController extends AbstractController<CityDto> {

    public CityController() {
        this.setService(new CityService());
    }
}
