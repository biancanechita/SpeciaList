package ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.impl;

import org.apache.commons.lang3.StringUtils;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.SortExpression;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.City;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Clinic;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Doctor;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.entities.Specialization;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.DoctorCustomRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DoctorCustomRepositoryImpl extends AbstractFilterAndSortUtilsImpl<Doctor> implements DoctorCustomRepository {

    public DoctorCustomRepositoryImpl() {
        super();
        this.settClass(Doctor.class);
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
                case "fullName":
                    queryParameters.add("(LOWER(doctor.firstName) LIKE :" + key
                            + " OR LOWER(doctor.lastName) LIKE :" + key + ")");
                    break;
                case "clinicId":
                case "id":
                case "specializationId":
                    queryParameters.add("doctor." + key + " = :" + key);
                    break;
                case "cityId":
                    queryParameters.add("clinic." + key + " = :" + key);
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
                case "clinic":
                case "fullName":
                    boundParameters.put(key, "%" + StringUtils.lowerCase(value) + "%");
                    break;
                case "cityId":
                case "clinicId":
                case "id":
                case "specializationId":
                    boundParameters.put(key, Long.valueOf(value));
                    break;

            }
        }
        return boundParameters;
    }

    @Override
    protected String useOrderByClause(List<SortExpression> sortingList) {
        StringBuilder orderBy = new StringBuilder(" ORDER BY ");

        if (sortingList.isEmpty()) {
            orderBy.append("doctor.id");
        } else {
            for (SortExpression sortExpression : sortingList) {
                switch (sortExpression.getColumn()) {
                    case "firstName":
                    case "lastName":
                    case "averageRating":
                        orderBy.append("doctor." + sortExpression.getColumn());
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
        return "SELECT doctor FROM " + this.tClass.getSimpleName() + " doctor "
                + "INNER JOIN " + Specialization.class.getSimpleName() + " specialization "
                + "ON doctor.specializationId = specialization.id "
                + "INNER JOIN " + Clinic.class.getSimpleName() + " clinic "
                + "ON doctor.clinicId = clinic.id "
                + "INNER JOIN " + City.class.getSimpleName() + " city "
                + "ON clinic.cityId = city.id";
    }
}
