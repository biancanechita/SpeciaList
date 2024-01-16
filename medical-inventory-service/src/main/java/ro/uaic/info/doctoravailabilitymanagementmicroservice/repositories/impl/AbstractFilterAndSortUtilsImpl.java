package ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.impl;

import org.apache.commons.lang3.StringUtils;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.SortExpression;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.QueryOptions;
import ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories.FilterAndSortUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

public abstract class AbstractFilterAndSortUtilsImpl<T> implements FilterAndSortUtils<T> {

    @PersistenceContext
    private EntityManager entityManager;

    protected Class<T> tClass;

    public void settClass(Class<T> tClass) {
        this.tClass = tClass;
    }

    @Override
    public List<T> filterAndSortList(QueryOptions queryOptions) {
        StringBuilder query = new StringBuilder(createQueryStatement());

        List<String> queryParameters = buildQueryParameters(queryOptions.getFilters());
        Map<String, Object> boundParameters = boundParameters(queryOptions.getFilters());

        if (!queryParameters.isEmpty()) {
            query.append(" WHERE ");
            query.append(StringUtils.join(queryParameters, " AND "));
        }

        query.append(useOrderByClause(queryOptions.getSortingList()));

        TypedQuery<T> typedQuery = entityManager.createQuery(query.toString(), tClass);
        boundParameters.forEach(typedQuery::setParameter);

        return typedQuery.getResultList();
    }

    protected abstract List<String> buildQueryParameters(Map<String, String> filters);

    protected abstract Map<String, Object> boundParameters(Map<String, String> filters);

    protected abstract String useOrderByClause(List<SortExpression> sortingList);
}
