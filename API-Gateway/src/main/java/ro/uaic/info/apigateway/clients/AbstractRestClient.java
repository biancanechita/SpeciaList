package ro.uaic.info.apigateway.clients;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import ro.uaic.info.apigateway.QueryOptions;

import java.util.List;

public abstract class AbstractRestClient<T> {

    protected WebClient.Builder create(String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    }

    public abstract List<T> filterAndSortList(String uri, QueryOptions queryOptions);

    public abstract T getById(String uri, Long id);

    public abstract T create(String uri, T t);
}
