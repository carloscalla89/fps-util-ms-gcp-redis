package pe.farmaciasperuanas.digital.gcp.redis.adapter.out;

import org.springframework.stereotype.Service;
import pe.farmaciasperuanas.digital.gcp.redis.adapter.entity.GcpRedisPropertiesEntity;
import pe.farmaciasperuanas.digital.gcp.redis.application.port.out.MongoService;
import reactor.core.publisher.Mono;

@Service
public class MongoServiceImpl implements MongoService {

    private GcpRedisPropertiesRepository gcpRedisPropertiesRepository;

    public MongoServiceImpl(GcpRedisPropertiesRepository gcpRedisPropertiesRepository) {
        this.gcpRedisPropertiesRepository = gcpRedisPropertiesRepository;
    }

    @Override
    public Mono<GcpRedisPropertiesEntity> getPropertyByCode(String code) {
        return gcpRedisPropertiesRepository.findByCode(code);
    }
}
