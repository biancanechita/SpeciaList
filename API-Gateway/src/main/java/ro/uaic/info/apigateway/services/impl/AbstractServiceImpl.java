package ro.uaic.info.apigateway.services.impl;

import ro.uaic.info.apigateway.QueryOptions;
import ro.uaic.info.apigateway.clients.AbstractRestClient;
import ro.uaic.info.apigateway.services.AbstractService;

import java.util.List;

public class AbstractServiceImpl<T> implements AbstractService<T> {
    protected AbstractRestClient<T> tAbstractRestClient;
    protected String uri;

    public void settAbstractRestClient(AbstractRestClient<T> tAbstractRestClient) {
        this.tAbstractRestClient = tAbstractRestClient;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public List<T> filterAndSortList(QueryOptions queryOptions) {
        return tAbstractRestClient.filterAndSortList(uri, queryOptions);
    }

    @Override
    public T getById(Long id) {
        return tAbstractRestClient.getById(uri, id);
    }

    @Override
    public T create(T t) {
        return tAbstractRestClient.create(uri, t);
    }


}
