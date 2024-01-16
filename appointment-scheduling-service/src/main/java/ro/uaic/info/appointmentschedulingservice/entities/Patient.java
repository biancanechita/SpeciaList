package ro.uaic.info.appointmentschedulingservice.entities;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@RequiredArgsConstructor
@Table(name = "patients")
public class Patient extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private final String firstName;

    @Column(name = "last_name")
    private final String lastName;

    @Column(name = "cnp")
    private final String cnp;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "user_id")
    private final Long userId;
}
