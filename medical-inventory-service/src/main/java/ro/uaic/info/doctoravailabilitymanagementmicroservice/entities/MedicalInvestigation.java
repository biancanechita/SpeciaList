package ro.uaic.info.doctoravailabilitymanagementmicroservice.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "investigations")
public class MedicalInvestigation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "investigation")
    private String investigation;

    @Column(name = "specialization_id")
    private Long specializationId;


}
