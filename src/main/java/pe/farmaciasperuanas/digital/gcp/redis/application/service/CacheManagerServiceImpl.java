package pe.farmaciasperuanas.digital.gcp.redis.application.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.farmaciasperuanas.digital.gcp.redis.application.port.in.CacheManagerService;
import pe.farmaciasperuanas.digital.gcp.redis.application.port.out.GcpRedisService;
import pe.farmaciasperuanas.digital.gcp.redis.domain.RequestRedisDto;
import pe.farmaciasperuanas.digital.gcp.redis.domain.ResponseDto;
import reactor.core.publisher.Mono;

import java.util.Optional;

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
* <li>15/07/2022 RedisServiceImpl class.</li>
* </ul>
 * @version 1.0
 */

@Slf4j
@Component
public class CacheManagerServiceImpl implements CacheManagerService {

    private GcpRedisService gcpRedisService;

    public CacheManagerServiceImpl(GcpRedisService gcpRedisService) {
        this.gcpRedisService = gcpRedisService;
    }

    @Override
    public Mono<ResponseDto> setObjectInCache(String key, RequestRedisDto requestCacheManagerDto) {
        try {

            gcpRedisService.set(key, requestCacheManagerDto.getPayload(), 60L);

            return Mono
                    .just(ResponseDto
                            .builder()
                            .saved(true)
                            .build()
                    );

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error during setting the cache:{}",e.getMessage());

            return Mono
                    .just(ResponseDto
                            .builder()
                            .saved(false)
                            .message("Error:"+e.getMessage())
                            .build()
                    );
        }
    }

    @Override
    public Mono<ResponseDto> setBytesInCache(String key, RequestRedisDto requestCacheManagerDto) {
        try {

            gcpRedisService.set(key, requestCacheManagerDto.getData(), 60L);

            return Mono
                    .just(ResponseDto
                            .builder()
                            .saved(true)
                            .build()
                    );

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error during setting the cache:{}",e.getMessage());

            return Mono
                    .just(ResponseDto
                            .builder()
                            .saved(false)
                            .message("Error:"+e.getMessage())
                            .build()
                    );
        }
    }

    @Override
    public Mono<ResponseDto> setHashBytesInCache(String collection, String hashKey, RequestRedisDto requestCacheManagerDto) {
        try {

            gcpRedisService.hmSet(collection, hashKey, requestCacheManagerDto.getData());

            return Mono
                    .just(ResponseDto
                            .builder()
                            .saved(true)
                            .build()
                    );

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error during setting the cache:{}",e.getMessage());

            return Mono
                    .just(ResponseDto
                            .builder()
                            .saved(false)
                            .message("Error:"+e.getMessage())
                            .build()
                    );
        }
    }

    @Override
    public Mono<ResponseDto> setHashStringInCache(String collection, String hashKey, RequestRedisDto requestCacheManagerDto) {
        try {

            gcpRedisService.hmSet(collection, hashKey, requestCacheManagerDto.getPayload());

            return Mono
                    .just(ResponseDto
                            .builder()
                            .saved(true)
                            .build()
                    );

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error during setting the cache:{}",e.getMessage());

            return Mono
                    .just(ResponseDto
                            .builder()
                            .saved(false)
                            .message("Error:"+e.getMessage())
                            .build()
                    );
        }
    }

    @Override
    public Mono<ResponseDto> getObjectByKeyFromRedis(String key) {
        return Optional
                .of(gcpRedisService.exists(key))
                .filter(val -> val)
                .map(val -> Mono
                        .just(ResponseDto
                                .builder()
                                .cacheHit(true)
                                .data(gcpRedisService.get(key).toString())
                                .build()
                        )

                )
                .orElseGet(() -> Mono.just(ResponseDto.builder().cacheHit(false).build()));
    }

    @Override
    public Mono<ResponseDto> getBytesByKeyFromRedis(String key) {
        return Optional
                .of(gcpRedisService.exists(key))
                .filter(val -> val)
                .map(val -> Mono
                        .just(ResponseDto
                                .builder()
                                .cacheHit(true)
                                .bytes((byte[]) gcpRedisService.get(key))
                                .build()
                        )

                )
                .orElseGet(() -> Mono.just(ResponseDto.builder().cacheHit(false).build()));
    }

    @Override
    public Mono<ResponseDto> getHashStringByKeyFromRedis(String collection, String hashKey) {

        try {
            Object object = gcpRedisService.hmGet(collection, hashKey);

            return Mono
                    .just(ResponseDto
                            .builder()
                            .cacheHit(true)
                            .data(object.toString())
                            .build()
                    );

        } catch (Exception e) {

            log.error("Error during getting hashkey:{}",e.getMessage());

            return Mono
                    .just(ResponseDto.builder().cacheHit(false).message(e.getMessage()).build());

        }

    }
}