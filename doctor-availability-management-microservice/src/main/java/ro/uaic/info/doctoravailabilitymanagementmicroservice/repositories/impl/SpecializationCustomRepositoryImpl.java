package ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.impl;

import org.apache.commons.lang3.StringUtils;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.SortExpression;
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
                case "name":
                    queryParameters.add("LOWER(" + key + ") LIKE :" + key);
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
                case "name":
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
            orderBy.append("id");
        } else {
            for (SortExpression sortExpression : sortingList) {
                switch (sortExpression.getColumn()) {
                    case "name":
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
        return "SELECT specialization FROM " + this.tClass.getSimpleName() + " specialization ";
    }
}
