package ro.uaic.info.doctoravailabilitymanagementmicroservice.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clinic_specialization")
public class ClinicSpecialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clinic_id")
    private Long clinicId;

    @Column(name = "specialization_id")
    private Long specializationId;
}
