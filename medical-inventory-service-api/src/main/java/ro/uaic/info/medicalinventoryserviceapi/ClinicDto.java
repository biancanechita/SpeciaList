package ro.uaic.info.medicalinventoryserviceapi;

import lombok.Data;

@Data
public class ClinicDto {

    private Long id;
    private String clinic;
    private String address;
    private Long cityId;
    private String contactInformation;
}
