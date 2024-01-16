package ro.uaic.info.appointmentschedulingservice.entities;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "appointment_requests")
public class AppointmentReq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "investigation_id")
    private Long medicalInvestigationId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;
}
