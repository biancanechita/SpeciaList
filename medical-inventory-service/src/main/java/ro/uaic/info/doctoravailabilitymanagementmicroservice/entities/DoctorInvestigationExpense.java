package ro.uaic.info.doctoravailabilitymanagementmicroservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "doctor_investigations_expense")
public class DoctorInvestigationExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "investigation_id")
    private Long investigationId;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "price")
    private BigDecimal price;
}
