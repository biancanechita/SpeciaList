package ro.uaic.info.usernotificationservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.uaic.info.usernotificationservice.beans.Notification;
import ro.uaic.info.usernotificationservice.services.NotificationService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void sendingNotificationsWithWhatsApp(@RequestBody Notification notification) {
        notificationService.sendingNotificationsWithWhatsApp(notification);
    }

}
