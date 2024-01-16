package ro.uaic.info.appointmentschedulingservice.beans;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class QueryOptions {

    private Map<String, String> filters;
    private List<SortExpression> sortingList;
}