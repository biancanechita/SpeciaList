package ro.uaic.info.doctoravailabilitymanagementmicroservice.beans;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class QueryOptions {

    private Map<String, String> filters;
    List<SortExpression> sortingList;
}
