package ro.uaic.info.doctoravailabilitymanagementmicroservice.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "specializations")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "description")
    private String description;
}
