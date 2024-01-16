package ro.uaic.info.doctoravailabilitymanagementmicroservice.mappers;

import javax.annotation.processing.Generated;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.DoctorInvestigationExpenseDTO;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.DoctorInvestigationExpense;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-27T13:25:53+0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.17 (Oracle Corporation)"
)
public class DoctorInvestigationExpenseMapperImpl implements DoctorInvestigationExpenseMapper {

    @Override
    public DoctorInvestigationExpenseDTO doctorInvestigationExpenseToDoctorInvestigationExpenseDTO(DoctorInvestigationExpense doctorInvestigationExpense) {
        if ( doctorInvestigationExpense == null ) {
            return null;
        }

        DoctorInvestigationExpenseDTO doctorInvestigationExpenseDTO = new DoctorInvestigationExpenseDTO();

        doctorInvestigationExpenseDTO.setId( doctorInvestigationExpense.getId() );
        doctorInvestigationExpenseDTO.setInvestigationId( doctorInvestigationExpense.getInvestigationId() );
        doctorInvestigationExpenseDTO.setDoctorId( doctorInvestigationExpense.getDoctorId() );
        doctorInvestigationExpenseDTO.setPrice( doctorInvestigationExpense.getPrice() );

        return doctorInvestigationExpenseDTO;
    }

    @Override
    public DoctorInvestigationExpense doctorInvestigationExpenseDTOToDoctorInvestigationExpense(DoctorInvestigationExpenseDTO doctorInvestigationExpenseDTO) {
        if ( doctorInvestigationExpenseDTO == null ) {
            return null;
        }

        DoctorInvestigationExpense doctorInvestigationExpense = new DoctorInvestigationExpense();

        doctorInvestigationExpense.setId( doctorInvestigationExpenseDTO.getId() );
        doctorInvestigationExpense.setInvestigationId( doctorInvestigationExpenseDTO.getInvestigationId() );
        doctorInvestigationExpense.setDoctorId( doctorInvestigationExpenseDTO.getDoctorId() );
        doctorInvestigationExpense.setPrice( doctorInvestigationExpenseDTO.getPrice() );

        return doctorInvestigationExpense;
    }
}
