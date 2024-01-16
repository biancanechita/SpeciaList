package ro.uaic.info.apigateway.services;

import org.springframework.stereotype.Service;
import ro.uaic.info.apigateway.clients.MedicalInventoryServiceClient;
import ro.uaic.info.apigateway.services.impl.AbstractServiceImpl;
import ro.uaic.info.medicalinventoryserviceapi.CityDto;

@Service
public class CityService extends AbstractServiceImpl<CityDto> {

    public CityService() {
        this.settAbstractRestClient(new MedicalInventoryServiceClient<>());
        this.setUri("/cities");
    }
}
