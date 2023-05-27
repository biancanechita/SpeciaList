package ro.uaic.info.doctoravailabilitymanagementmicroservice.config;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.CityDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.ClinicDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.DoctorDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.SpecializationDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.services.CityService;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.services.ClinicService;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.services.DoctorService;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.services.SpecializationService;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class DatabaseInitializer {

    private final Faker faker = new Faker();
    private final Random random = new Random();

    private final CityService cityService;
    private final ClinicService clinicService;
    private final DoctorService doctorService;
    private final SpecializationService specializationService;

    @PostConstruct
    public void initializeDatabase() {
        if (cityService.count() == 0) {
            for (int i = 0; i < 20; i++) {
                generateFakeCityDTO();
            }
        }

        if (clinicService.count() == 0) {
            for (int i = 0; i < 20; i++) {
                generateFakeClinicDTO();
            }
        }

        if (specializationService.count() == 0) {
            generateSpecializations();
        }

        if (doctorService.count() == 0) {
            for (int i = 0; i < 20; i++) {
                generateFakeDoctorDTO();
            }
        }
    }

    private void generateFakeCityDTO() {
        CityDTO cityDTO = new CityDTO();
        cityDTO.setName(faker.address().cityName());
        cityService.save(cityDTO);
    }

    private void generateFakeClinicDTO() {
        long count = cityService.count();
        long cityId = random.nextInt((int) count) + 1;

        if (cityService.findById(cityId) != null) {
            ClinicDTO clinicDTO = new ClinicDTO();
            clinicDTO.setName(faker.medical().hospitalName());
            clinicDTO.setAddress(faker.address().streetAddress());
            clinicDTO.setCityId(cityId);
            clinicDTO.setContactInformation(faker.phoneNumber().phoneNumber());
            clinicService.save(clinicDTO);
        }
    }

    private void generateSpecializations() {
        SpecializationDTO cardiology = new SpecializationDTO();
        cardiology.setName("Cardiology");
        cardiology.setDescription("Cardiology is a medical specialization that focuses on the prevention, diagnosis, and treatment of diseases and conditions related to the heart and blood vessels. Cardiologists are trained to manage various heart conditions such as heart attacks, heart failure, arrhythmias, and coronary artery disease.");
        specializationService.save(cardiology);

        SpecializationDTO dermatology = new SpecializationDTO();
        dermatology.setName("Dermatology");
        dermatology.setDescription("Dermatology is a medical specialization that deals with the diagnosis and treatment of disorders and conditions affecting the skin, hair, and nails. Dermatologists are trained to address issues like acne, eczema, psoriasis, skin infections, allergies, and skin cancer.");
        specializationService.save(dermatology);

        SpecializationDTO endocrinology = new SpecializationDTO();
        endocrinology.setName("Endocrinology");
        endocrinology.setDescription("Endocrinology is a medical specialization that focuses on disorders and conditions related to hormones and the endocrine system. Endocrinologists diagnose and manage diseases such as diabetes, thyroid disorders, hormonal imbalances, metabolic disorders, and adrenal gland disorders.");
        specializationService.save(endocrinology);

        SpecializationDTO gastroenterology = new SpecializationDTO();
        gastroenterology.setName("Gastroenterology");
        gastroenterology.setDescription("Gastroenterology is a medical specialization that involves the diagnosis and treatment of diseases and disorders of the digestive system, including the esophagus, stomach, intestines, liver, pancreas, and gallbladder. Gastroenterologists address conditions such as gastrointestinal infections, inflammatory bowel disease, liver diseases, and gastrointestinal cancers.");
        specializationService.save(gastroenterology);
    }

    private void generateFakeDoctorDTO() {
        long count = specializationService.count();
        long specializationId = random.nextInt((int) count) + 1;

        count = clinicService.count();
        long clinicId = random.nextInt((int) count) + 1;

        if (specializationService.findById(specializationId) != null && clinicService.findById(clinicId) != null) {
            DoctorDTO doctorDTO = new DoctorDTO();
            doctorDTO.setFirstName(faker.name().firstName());
            doctorDTO.setLastName(faker.name().lastName());
            doctorDTO.setSpecializationId(specializationId);
            doctorDTO.setContactInformation(faker.phoneNumber().phoneNumber());
            doctorDTO.setClinicId(clinicId);
            doctorService.save(doctorDTO);
        }
    }
}
