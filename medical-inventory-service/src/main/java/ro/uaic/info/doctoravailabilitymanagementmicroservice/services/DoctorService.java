package ro.uaic.info.doctoravailabilitymanagementmicroservice.services;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.DoctorDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.QueryOptions;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Doctor;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers.DoctorMapper;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.DoctorRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper = Mappers.getMapper(DoctorMapper.class);

    public long count() {
        return doctorRepository.count();
    }

    public List<DoctorDTO> filterAndSortList(QueryOptions queryOptions) {
        return doctorRepository
                .filterAndSortList(queryOptions)
                .stream()
                .map(doctorMapper::doctorToDoctorDTO)
                .collect(Collectors.toList());
    }

    public List<DoctorDTO> findAll() {
        return doctorRepository
                .findAll()
                .stream()
                .map(doctorMapper::doctorToDoctorDTO)
                .collect(Collectors.toList());
    }

    public List<Long> findAllBySpecializationIdAndCityId(QueryOptions queryOptions) {
        return doctorRepository
                .filterAndSortList(queryOptions)
                .stream()
                .map(doctor -> doctor.getId())
                .collect(Collectors.toList());
    }

    public DoctorDTO findById(Long id) {
        return doctorRepository
                .findById(id)
                .map(doctorMapper::doctorToDoctorDTO)
                .orElse(null);
    }

    public DoctorDTO save(DoctorDTO doctorDTO) {
        Doctor doctor = doctorMapper.doctorDTOToDoctor(doctorDTO);
        doctor.setAverageRating(BigDecimal.valueOf(0));
        doctor.setTotalReviews(0);

        return Optional.of(doctor)
                .map(doctorRepository::save)
                .map(doctorMapper::doctorToDoctorDTO)
                .orElse(null);
    }

    public void deleteById(Long id) {
        doctorRepository.deleteById(id);
    }

    public String getFullName(Long id) {
        return doctorRepository
                .getFullName(id)
                .map(fullName -> fullName.getFirstName() + " " + fullName.getLastName())
                .orElse(null);
    }
}
