package ro.uaic.info.apigateway;

import lombok.Data;

@Data
public class SortExpression {

    private String column;
    private String order;
}