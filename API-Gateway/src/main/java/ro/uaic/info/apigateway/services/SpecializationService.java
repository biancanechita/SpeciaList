package ro.uaic.info.apigateway.services;

import org.springframework.stereotype.Service;
import ro.uaic.info.apigateway.clients.MedicalInventoryServiceClient;
import ro.uaic.info.apigateway.services.impl.AbstractServiceImpl;
import ro.uaic.info.medicalinventoryserviceapi.SpecializationDto;

@Service
public class SpecializationService extends AbstractServiceImpl<SpecializationDto> {

    public SpecializationService() {
        this.settAbstractRestClient(new MedicalInventoryServiceClient<>());
        this.setUri("/specializations");
    }
}
