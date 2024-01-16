package ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.impl;

import org.apache.commons.lang3.StringUtils;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.SortExpression;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Doctor;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.DoctorInvestigationExpense;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.MedicalInvestigation;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Specialization;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.MedicalInvestigationCustomRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MedicalInvestigationCustomRepositoryImpl extends AbstractFilterAndSortUtilsImpl<MedicalInvestigation> implements MedicalInvestigationCustomRepository {

    public MedicalInvestigationCustomRepositoryImpl() {
        super();
        this.settClass(MedicalInvestigation.class);
    }

    @Override
    protected List<String> buildQueryParameters(Map<String, String> filters) {
        List<String> queryParameters = new ArrayList<>();

        for (Map.Entry<String, String> entry : filters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (value.isEmpty()) {
                continue;
            }

            switch (key) {
                case "clinicId":
                    queryParameters.add("doctor." + key + " = :" + key);
                    break;
                case "specializationId":
                    queryParameters.add("medicalInvestigation." + key + " = :" + key);
                    break;

            }
        }
        return queryParameters;
    }

    @Override
    protected Map<String, Object> boundParameters(Map<String, String> filters) {
        Map<String, Object> boundParameters = new HashMap<>();

        for (Map.Entry<String, String> entry : filters.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (value.isEmpty()) {
                continue;
            }

            value = StringUtils.strip(value);

            switch (key) {
                case "clinicId":
                case "specializationId":
                    boundParameters.put(key, Long.valueOf(value));
                    break;

            }
        }
        return boundParameters;
    }

    @Override
    protected String useOrderByClause(List<SortExpression> sortingList) {
        return "";
    }

    @Override
    public String createQueryStatement() {
        return "SELECT DISTINCT medicalInvestigation FROM " + this.tClass.getSimpleName() + " medicalInvestigation "
                + "INNER JOIN " + DoctorInvestigationExpense.class.getSimpleName() + " doctorInvestigationExpense "
                + "ON medicalInvestigation.id = doctorInvestigationExpense.investigationId "
                + "INNER JOIN " + Doctor.class.getSimpleName() + " doctor "
                + "ON doctorInvestigationExpense.doctorId = doctor.id "
                + "INNER JOIN " + Specialization.class.getSimpleName() + " specialization "
                + "ON doctor.specializationId = specialization.id ";
    }
}
