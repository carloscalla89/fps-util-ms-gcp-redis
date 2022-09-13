package pe.farmaciasperuanas.digital.gcp.redis.application.port.out;

import pe.farmaciasperuanas.digital.gcp.redis.adapter.entity.GcpRedisPropertiesEntity;
import reactor.core.publisher.Mono;

public interface MongoService {

    Mono<GcpRedisPropertiesEntity> getPropertyByCode(String code);
}
