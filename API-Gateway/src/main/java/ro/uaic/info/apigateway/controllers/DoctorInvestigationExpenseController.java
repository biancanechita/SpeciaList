package ro.uaic.info.apigateway.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.uaic.info.apigateway.services.DoctorInvestigationExpenseService;
import ro.uaic.info.appointmentschedulingservice.DoctorInvestigationExpenseDto;

@RestController
@RequestMapping("/doctor-investigation-expense")
@CrossOrigin
public class DoctorInvestigationExpenseController extends AbstractController<DoctorInvestigationExpenseDto> {

    @Autowired
    public DoctorInvestigationExpenseController(DoctorInvestigationExpenseService service) {
        this.service = service;
    }
}
