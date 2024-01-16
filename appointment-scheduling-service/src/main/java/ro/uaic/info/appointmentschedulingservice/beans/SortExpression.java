package ro.uaic.info.appointmentschedulingservice.beans;

import lombok.Data;

@Data
public class SortExpression {

    private String column;
    private String order;
}