package pe.farmaciasperuanas.digital.gcp.redis.application.port.in;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * Interface class for running Spring Boot framework.<br/>
 * <b>Class</b>: SubscriptionService<br/>
 * <b>Copyright</b>: &copy; 2022 Digital.<br/>
 * <b>Company</b>: Digital.<br/>
 *

 * <u>Developed by</u>: <br/>
 * <ul>
 * <li>carlos calla</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 * <li>Oct 19, 2022 EventSubscriptionService.</li>
 * </ul>
 * @version 1.0
 */

public interface EventSubscriptionService {

   String CHANNEL_EVENT_1 = "channel_event_1";

   @Input(CHANNEL_EVENT_1)
   SubscribableChannel inputSubscribable();

}
