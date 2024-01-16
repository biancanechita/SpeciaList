package ro.uaic.info.usernotificationservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.info.usernotificationservice.beans.Notification;
import ro.uaic.info.usernotificationservice.clients.TwilioClient;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class NotificationService {

    private final TwilioClient twilioClient;

    public void sendingNotificationsWithWhatsApp(Notification notification) {
        LocalDateTime localDateTime = LocalDateTime.parse(notification.getBody());

        switch (notification.getType()) {
            case CONFIRMATION:
                notification.setBody("Programarea dumneavoastra din data de " +
                        localDateTime.getDayOfMonth() + " " +
                        localDateTime.getMonth() + ", ora " +
                        localDateTime.getHour() + ":" +
                        localDateTime.getMinute() + " este confirmata.");
            case REMINDER:
                notification.setBody("Buna ziua. Va reamintim de programarea dumneavoastra din data de " +
                        localDateTime.getDayOfMonth() + " " +
                        localDateTime.getMonth() + ", ora " +
                        localDateTime.getHour() + ":" +
                        localDateTime.getMinute() + ".");
        }
        twilioClient.sendingNotificationsWithWhatsApp(notification);
    }
}
