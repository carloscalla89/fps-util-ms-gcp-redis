package pe.farmaciasperuanas.digital.gcp.redis.adapter.in;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import pe.farmaciasperuanas.digital.gcp.redis.application.port.in.CacheManagerService;
import pe.farmaciasperuanas.digital.gcp.redis.application.port.in.EventSubscriptionService;
import pe.farmaciasperuanas.digital.gcp.redis.domain.atlasmongo.AtlasMongoEventDto;
import pe.farmaciasperuanas.digital.gcp.redis.domain.atlasmongo.zone.ZoneDto;
import reactor.core.publisher.Mono;

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

    @Autowired
    private CacheManagerService cacheManagerService;

    @StreamListener(EventSubscriptionService.CHANNEL_EVENT_1)
    public void handleMessage(AtlasMongoEventDto atlasMongoEventDto) {

        log.info("[START] New message received to delete caché:{}",atlasMongoEventDto);

        cacheManagerService
                .processEventReceived(atlasMongoEventDto)
                .subscribe(resp -> log.info("[END] New message received to delete caché with response:{}",resp));

    }
}
