package ro.uaic.info.usernotificationservice.beans;

import lombok.Data;

@Data
public class Notification {

    private String to;
    private String body;
    private NotificationType type;
}
