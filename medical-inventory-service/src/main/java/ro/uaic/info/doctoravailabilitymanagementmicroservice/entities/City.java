package ro.uaic.info.doctoravailabilitymanagementmicroservice.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cities")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "city")
    private String city;
}
