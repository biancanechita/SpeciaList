package ro.uaic.info.appointmentschedulingservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uaic.info.appointmentschedulingservice.entities.AppointmentReq;

public interface AppointmentRequestRepository extends JpaRepository<AppointmentReq, Long> {
}
