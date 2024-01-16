package ro.uaic.info.appointmentschedulingservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.info.appointmentschedulingservice.beans.SensitivePersonalData;
import ro.uaic.info.appointmentschedulingservice.entities.Patient;
import ro.uaic.info.appointmentschedulingservice.repositories.PatientRepository;

@RequiredArgsConstructor
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public Long create(SensitivePersonalData personalData) {
        Patient patient = new Patient(personalData.getFirstName(), personalData.getLastName(), personalData.getCnp(), personalData.getUserId());
        return patientRepository.save(patient).getId();
    }

    public Long findByUserId(Long userId) {
        return patientRepository.findByUserId(userId);
    }
}
