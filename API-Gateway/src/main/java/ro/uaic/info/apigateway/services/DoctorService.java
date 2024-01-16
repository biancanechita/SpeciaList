package ro.uaic.info.apigateway.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.uaic.info.apigateway.clients.MedicalInventoryServiceClient;
import ro.uaic.info.apigateway.services.impl.AbstractServiceImpl;
import ro.uaic.info.medicalinventoryserviceapi.DoctorDto;

@Service
public class DoctorService extends AbstractServiceImpl<DoctorDto> {

    private final MedicalInventoryServiceClient medicalInventoryServiceClient;

    @Autowired
    public DoctorService(MedicalInventoryServiceClient medicalInventoryServiceClient) {
        this.medicalInventoryServiceClient = medicalInventoryServiceClient;
        this.tAbstractRestClient = medicalInventoryServiceClient;
        this.setUri("/doctors");
    }

    public String getFullName(Long id) {
        return medicalInventoryServiceClient.getFullName(uri, id);
    }
}
