package ro.uaic.info.doctoravailabilitymanagementmicroservice.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clinics")
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clinic")
    private String clinic;

    @Column(name = "address")
    private String address;

    @Column(name = "city_id")
    private Long cityId;

    @Column(name = "contact_information")
    private String contactInformation;
}
