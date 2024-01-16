package ro.uaic.info.medicalinventoryserviceapi;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DoctorDto {

    private Long id;
    private String firstName;
    private String lastName;
    private Long specializationId;
    private String contactInformation;
    private String bio;
    private BigDecimal averageRating;
    private Integer totalReviews;
    private String photoUrl;
    private Long clinicId;
}
