package ro.uaic.info.doctoravailabilitymanagementmicroservice.beans;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DoctorInvestigationExpenseDTO {

    private Long id;
    private Long investigationId;
    private Long doctorId;
    private BigDecimal price;
}
