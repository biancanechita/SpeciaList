package ro.uaic.info.apigateway.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.uaic.info.apigateway.clients.MedicalInventoryServiceClient;
import ro.uaic.info.apigateway.services.impl.AbstractServiceImpl;
import ro.uaic.info.appointmentschedulingservice.MedicalInvestigationDto;

import java.util.List;

@Service
public class MedicalInvestigationService extends AbstractServiceImpl<MedicalInvestigationDto> {

    private final MedicalInventoryServiceClient medicalInventoryServiceClient;

    @Autowired
    public MedicalInvestigationService(MedicalInventoryServiceClient medicalInventoryServiceClient) {
        this.medicalInventoryServiceClient = medicalInventoryServiceClient;
        this.settAbstractRestClient(medicalInventoryServiceClient);
        this.setUri("/medical-investigations");
    }

    public List<MedicalInvestigationDto> findAllByDoctorId(Long id) {
        return medicalInventoryServiceClient.findAllByDoctorId(uri, id);
    }
}
