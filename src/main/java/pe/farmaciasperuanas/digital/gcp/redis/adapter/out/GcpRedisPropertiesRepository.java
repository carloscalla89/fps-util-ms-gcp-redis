package pe.farmaciasperuanas.digital.gcp.redis.adapter.out;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.farmaciasperuanas.digital.gcp.redis.adapter.entity.GcpRedisPropertiesEntity;
import reactor.core.publisher.Mono;

@Repository
public interface GcpRedisPropertiesRepository extends ReactiveMongoRepository<GcpRedisPropertiesEntity, ObjectId> {

    @Query(value = "{ code : ?0}")
    Mono<GcpRedisPropertiesEntity> findByCode(String code);
}
