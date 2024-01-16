package ro.uaic.info.doctoravailabilitymanagementmicroservice.beans;

import lombok.Data;

@Data
public class ClinicDTO {

    private Long id;
    private String clinic;
    private String address;
    private Long cityId;
    private String contactInformation;
}
