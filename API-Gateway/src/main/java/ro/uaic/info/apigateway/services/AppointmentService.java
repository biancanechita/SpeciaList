package ro.uaic.info.apigateway.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.uaic.info.apigateway.clients.AppointmentSchedulingServiceClient;
import ro.uaic.info.apigateway.config.UserPrincipal;
import ro.uaic.info.appointmentschedulingservice.AppointmentDto;
import ro.uaic.info.appointmentschedulingservice.AppointmentRequest;
import ro.uaic.info.appointmentschedulingservice.AvailableTimes;
import ro.uaic.info.appointmentschedulingservice.SchedulingParameters;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class AppointmentService {

    private final AppointmentSchedulingServiceClient client;
    private final UserNotificationService userNotificationService;

    public List<AvailableTimes> fetchAvailableTimes(SchedulingParameters schedulingParameters) {
        return client.fetchAvailableTimes(schedulingParameters);
    }

    public List<AppointmentDto> findAllByUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            String userId = userPrincipal.getId();
            System.out.println(userId);
            return client.findAllByUserId(Long.valueOf(userId));
        }

        return null;
    }

    public AppointmentDto save(AppointmentDto appointmentDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        appointmentDto.setPatientId(Long.valueOf(userPrincipal.getId()));

        client.save(appointmentDto);

        try {
            userNotificationService.sendingNotificationsWithWhatsApp(appointmentDto);
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }

        return appointmentDto;
    }

    public void deleteById(Long id) {
        client.deleteById(id);
    }

    public void scheduleVisitorMode(AppointmentRequest appointmentRequest) {
        client.scheduleVisitorMode(appointmentRequest);
    }
}
