package ro.uaic.info.usernotificationservice.clients;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ro.uaic.info.usernotificationservice.beans.Notification;

@Service
@Slf4j
public class TwilioClient {

    @Value("${TWILIO_ACCOUNT_SID}")
    public String ACCOUNT_SID;
    @Value("${TWILIO_AUTH_TOKEN}")
    public String AUTH_TOKEN;

    public void sendingNotificationsWithWhatsApp(Notification notification) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new PhoneNumber("whatsapp:+40753100362"),
                        new PhoneNumber("whatsapp:+14155238886"),
                        notification.getBody())
                .create();

        log.info(message.getSid());
    }
}
