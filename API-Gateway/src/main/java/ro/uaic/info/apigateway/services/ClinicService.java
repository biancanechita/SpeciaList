package ro.uaic.info.apigateway.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.info.apigateway.clients.MedicalInventoryServiceClient;
import ro.uaic.info.apigateway.services.impl.AbstractServiceImpl;
import ro.uaic.info.medicalinventoryserviceapi.ClinicDto;

@Service
public class ClinicService extends AbstractServiceImpl<ClinicDto> {

    public ClinicService() {
        this.settAbstractRestClient(new MedicalInventoryServiceClient<>());
        this.setUri("/clinics");
    }
}
