package ro.uaic.info.medicalinventoryserviceapi;

import lombok.Data;

@Data
public class SpecializationDto {

    private Long id;
    private String specialization;
    private String description;
}
