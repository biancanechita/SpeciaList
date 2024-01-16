package ro.uaic.info.appointmentschedulingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.info.appointmentschedulingservice.entities.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findAllByDoctorIdAndDateAfterOrderByDateAscStartTimeAsc(Long doctorId, LocalDate date);

    List<Appointment> findAllByPatientIdOrderByDate(Long patientId);
}
