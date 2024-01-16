package ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.impl;

import org.apache.commons.lang3.StringUtils;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.SortExpression;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Doctor;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.DoctorInvestigationExpense;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Specialization;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.DoctorInvestigationExpenseCustomRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorInvestigationExpenseCustomRepositoryImpl extends AbstractFilterAndSortUtilsImpl<DoctorInvestigationExpense> implements DoctorInvestigationExpenseCustomRepository {

    public DoctorInvestigationExpenseCustomRepositoryImpl() {
        super();
        this.settClass(DoctorInvestigationExpense.class);
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
                case "investigationId":
                    queryParameters.add("doctorInvestigationExpense." + key + " = :" + key);
                    break;
                case "clinicId":
                    queryParameters.add("doctor." + key + " = :" + key);
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
                case "investigationId":
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
        return "SELECT doctorInvestigationExpense FROM " + this.tClass.getSimpleName() + " doctorInvestigationExpense "
                + "INNER JOIN " + Doctor.class.getSimpleName() + " doctor "
                + "ON doctorInvestigationExpense.doctorId = doctor.id";
    }
}
