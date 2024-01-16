package ro.uaic.info.apigateway.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.uaic.info.apigateway.clients.UserNotificationServiceClient;
import ro.uaic.info.appointmentschedulingservice.AppointmentDto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserNotificationService {

    private final UserNotificationServiceClient client;

    public void sendingNotificationsWithWhatsApp(AppointmentDto appointmentDto) {
        Map<String, String> map = new HashMap<>();
        map.put("to", "0753100362");
        map.put("body", String.valueOf(LocalDateTime.of(appointmentDto.getDate(), appointmentDto.getStartTime())));
        map.put("type", "CONFIRMATION");

        client.sendingNotificationsWithWhatsApp(map);
    }
}
