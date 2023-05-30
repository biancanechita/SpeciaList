package ro.uaic.info.doctoravailabilitymanagementmicroservice.beans;

import lombok.Data;

@Data
public class SortExpression {

    private String column;
    private String order;
}
