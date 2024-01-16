package ro.uaic.info.apigateway;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class QueryOptions {

    private Map<String, String> filters;
    List<SortExpression> sortingList;
}