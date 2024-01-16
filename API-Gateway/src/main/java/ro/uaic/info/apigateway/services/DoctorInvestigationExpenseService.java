package ro.uaic.info.apigateway.services;

import org.springframework.stereotype.Service;
import ro.uaic.info.apigateway.clients.MedicalInventoryServiceClient;
import ro.uaic.info.apigateway.services.impl.AbstractServiceImpl;
import ro.uaic.info.appointmentschedulingservice.DoctorInvestigationExpenseDto;

@Service
public class DoctorInvestigationExpenseService extends AbstractServiceImpl<DoctorInvestigationExpenseDto> {

    public DoctorInvestigationExpenseService(MedicalInventoryServiceClient medicalInventoryServiceClient) {
        this.settAbstractRestClient(medicalInventoryServiceClient);
        this.setUri("/doctor-investigation-expense");
    }
}
