package ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers;

import org.mapstruct.Mapper;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.DoctorDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Doctor;

@Mapper
public interface DoctorMapper {

    DoctorDTO doctorToDoctorDTO(Doctor doctor);

    Doctor doctorDTOToDoctor(DoctorDTO doctorDTO);
}
