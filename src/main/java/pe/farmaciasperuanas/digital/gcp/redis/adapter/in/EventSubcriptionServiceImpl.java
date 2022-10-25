package pe.farmaciasperuanas.digital.gcp.redis.adapter.in;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import pe.farmaciasperuanas.digital.gcp.redis.application.port.in.EventSubscriptionService;
import pe.farmaciasperuanas.digital.gcp.redis.domain.atlasmongo.AtlasMongoEventDto;

/**
 * Implement class for running Spring Boot framework.<br/>
 * <b>Copyright</b>: &copy; 2022 Digital.<br/>
 * <b>Company</b>: Digital.<br/>
 *

 * <u>Developed by</u>: <br/>
 * <ul>
* <li>carlos calla</li>
* </ul>
 * <u>Changes</u>:<br/>
 * <ul>
* <li>Oct 19, 2022 EventSubcriptionServiceImpl class.</li>
* </ul>
 * @version 1.0
 */

@Slf4j
@EnableBinding(EventSubscriptionService.class)
public class EventSubcriptionServiceImpl {

    @StreamListener(EventSubscriptionService.CHANNEL_EVENT_1)
    public void handleMessage(AtlasMongoEventDto Dto) {

       log.info("New message received:{}",Dto);

    }
}
