package ro.uaic.info.appointmentschedulingservice.entities;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "appointments")
public class Appointment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "patient_id")
    private Long patientId;

    @Column(name = "doctor_id")
    private Long doctorId;

    @Column(name = "medical_investigation_id")
    private Long medicalInvestigationId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;
}
