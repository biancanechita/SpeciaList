package ro.uaic.info.doctoravailabilitymanagementmicroservice.repositories;

import ro.uaic.info.doctoravailabilitymanagementmicroservice.beans.QueryOptions;

import java.util.List;

public interface FilterAndSortUtils<T> {

    List<T> filterAndSortList(QueryOptions queryOptions);

    abstract String createQueryStatement();
}
