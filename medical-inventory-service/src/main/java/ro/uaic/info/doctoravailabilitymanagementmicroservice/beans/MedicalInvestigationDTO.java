package ro.uaic.info.doctoravailabilitymanagementmicroservice.beans;

import lombok.Data;

@Data
public class MedicalInvestigationDTO {

    private Long id;
    private String investigation;
    private Long specializationId;
}
