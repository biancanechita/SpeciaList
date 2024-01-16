package ro.uaic.info.apigateway.services;

import ro.uaic.info.apigateway.QueryOptions;

import java.util.List;

public interface AbstractService<T> {

    List<T> filterAndSortList(QueryOptions queryOptions);

    T getById(Long id);

    T create(T t);
}
