package ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.impl;

import org.apache.commons.lang3.StringUtils;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.SortExpression;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Clinic;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Doctor;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.ClinicCustomRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClinicCustomRepositoryImpl extends AbstractFilterAndSortUtilsImpl<Clinic> implements ClinicCustomRepository {

    public ClinicCustomRepositoryImpl() {
        super();
        this.settClass(Clinic.class);
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
                case "clinic":
                    queryParameters.add("LOWER(clinic." + key + ") LIKE :" + key);
                    break;
                case "cityId":
                case "id":
                    queryParameters.add("clinic." + key + " = :" + key);
                    break;
                case "specializationId":
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
                case "cityId":
                case "id":
                case "specializationId":
                    boundParameters.put(key, Long.valueOf(value));
                    break;
                case "clinic":
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
            orderBy.append("clinic.id");
        } else {
            for (SortExpression sortExpression : sortingList) {
                switch (sortExpression.getColumn()) {
                    case "clinic":
                        orderBy.append(sortExpression.getColumn());
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
        return "SELECT DISTINCT clinic FROM " + this.tClass.getSimpleName() + " clinic "
                + "INNER JOIN " + Doctor.class.getSimpleName() + " doctor "
                + "ON clinic.id = doctor.clinicId ";
    }
}
