package ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.impl;

import org.apache.commons.lang3.StringUtils;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.SortExpression;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Doctor;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Specialization;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.SpecializationCustomRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpecializationCustomRepositoryImpl extends AbstractFilterAndSortUtilsImpl<Specialization> implements SpecializationCustomRepository {

    public SpecializationCustomRepositoryImpl() {
        super();
        this.settClass(Specialization.class);
    }

    @Override
    public void settClass(Class<Specialization> specializationClass) {
        super.settClass(specializationClass);
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
                case "id":
                    queryParameters.add("specialization." + key + " = :" + key);
                    break;
                case "specialization":
                    queryParameters.add("LOWER(specialization." + key + ") LIKE :" + key);
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
                case "id":
                case "clinicId":
                    boundParameters.put(key, Long.valueOf(value));
                    break;
                case "specialization":
                    boundParameters.put(key, "%" + StringUtils.lowerCase(value) + "%");
                    break;

            }
        }
        return boundParameters;
    }

    @Override
    protected String useOrderByClause(List<SortExpression> sortingList) {
        StringBuilder orderBy = new StringBuilder(" ORDER BY ");

        if (sortingList.isEmpty()) {
            orderBy.append("specialization.id");
        } else {
            for (SortExpression sortExpression : sortingList) {
                switch (sortExpression.getColumn()) {
                    case "specialization":
                        orderBy.append("specialization." + sortExpression.getColumn());
                        break;
                }

                switch (sortExpression.getOrder()) {
                    case "DESC":
                        orderBy.append(" DESC");
                        break;
                    case "ASC":
                    default:
                        orderBy.append(" ASC");
                        break;
                }

                orderBy.append(",");
            }
        }
        return orderBy.toString().replaceAll(",$", "");
    }

    @Override
    public String createQueryStatement() {
        return "SELECT DISTINCT specialization FROM " + this.tClass.getSimpleName() + " specialization "
                + "INNER JOIN " + Doctor.class.getSimpleName() + " doctor "
                + "ON specialization.id = doctor.specializationId";
    }
}
