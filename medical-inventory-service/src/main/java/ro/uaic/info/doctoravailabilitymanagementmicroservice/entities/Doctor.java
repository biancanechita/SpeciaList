package ro.uaic.info.doctoravailabilitymanagementmicroservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "specialization_id")
    private Long specializationId;

    @Column(name = "contact_information")
    private String contactInformation;

    @Column(name = "bio")
    private String bio;

    @Column(name = "average_rating")
    private BigDecimal averageRating;

    @Column(name = "total_reviews")
    private Integer totalReviews;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "clinic_id")
    private Long clinicId;

}
