package pe.farmaciasperuanas.digital.gcp.redis.application.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pe.farmaciasperuanas.digital.gcp.redis.application.port.in.CacheManagerService;
import pe.farmaciasperuanas.digital.gcp.redis.application.port.out.GcpRedisService;
import pe.farmaciasperuanas.digital.gcp.redis.application.port.out.MongoService;
import pe.farmaciasperuanas.digital.gcp.redis.application.util.ResourceConfig;
import pe.farmaciasperuanas.digital.gcp.redis.domain.RequestRedisDto;
import pe.farmaciasperuanas.digital.gcp.redis.domain.ResponseDto;
import pe.farmaciasperuanas.digital.gcp.redis.domain.stockbackup.CoverageLocationWithBackupInfoDto;
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
    private MongoService mongoService;
    private ResourceConfig resourceConfig;

    public CacheManagerServiceImpl(GcpRedisService gcpRedisService, MongoService mongoService,
                                   ResourceConfig resourceConfig) {

        this.gcpRedisService = gcpRedisService;
        this.mongoService = mongoService;
        this.resourceConfig = resourceConfig;
    }

    @Override
    public Mono<ResponseDto> setObjectInCache(String key, RequestRedisDto requestCacheManagerDto) {

        return Mono
                .just(requestCacheManagerDto)
                .filter(requestRedisDto -> requestCacheManagerDto.getExpireTime() != null)
                .flatMap(requestRedisDto ->
                        processObjectInCache(key, requestRedisDto.getPayload(), requestRedisDto.getExpireTime())
                )
                .switchIfEmpty(
                        Mono.defer(() ->
                                mongoService
                                        .getPropertyByCode("EXPIRE_TIME")
                                        .flatMap(propertyResp ->
                                                processObjectInCache(
                                                        key,
                                                        requestCacheManagerDto.getPayload(),
                                                        Long.parseLong(propertyResp.getValue()))))
                );



    }


    private Mono<ResponseDto> processObjectInCache(String key, String payload, long expireTime) {
        try {

            gcpRedisService.set(
                    key,
                    payload,
                    expireTime);

            return Mono
                    .just(ResponseDto
                            .builder()
                            .success(true)
                            .build()
                    );

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error during setting the cache:{}",e.getMessage());

            return Mono
                    .just(ResponseDto
                            .builder()
                            .success(false)
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
                            .success(true)
                            .build()
                    );

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error during setting the cache:{}",e.getMessage());

            return Mono
                    .just(ResponseDto
                            .builder()
                            .success(false)
                            .message("Error:"+e.getMessage())
                            .build()
                    );
        }
    }

    @Override
    public Mono<ResponseDto> setHashStringDummyInCache(String collection, String hashKey, RequestRedisDto requestCacheManagerDto) {
        try {

            log.info("payload dummy:{}",resourceConfig.getStringScriptApi());

            gcpRedisService.hmSet(collection, hashKey, resourceConfig.getStringScriptApi());

            return Mono
                    .just(ResponseDto
                            .builder()
                            .success(true)
                            .build()
                    );

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Error during setting the cache:{}",e.getMessage());

            return Mono
                    .just(ResponseDto
                            .builder()
                            .success(false)
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
    public Mono<ResponseDto> getHashStringByKeyFromRedis(String collection, String hashKey) {

        try {

            return Optional
                    .ofNullable(gcpRedisService.hmGet(collection, hashKey))
                    .map(val -> Mono
                            .just(ResponseDto
                                    .builder()
                                    .cacheHit(true)
                                    .data(val.toString())
                                    .build()
                            )
                    )
                    .orElseGet(() -> Mono.just(ResponseDto.builder().cacheHit(false).build()));

        } catch (Exception e) {

            log.error("Error during getting hashkey:{}",e.getMessage());

            return Mono
                    .just(ResponseDto.builder().cacheHit(false).message(e.getMessage()).build());

        }

    }

    @Override
    public Mono<CoverageLocationWithBackupInfoDto> getHashStringDummyByKeyFromRedis(String collection, String hashKey) {
        try {

            return Optional
                    .ofNullable(gcpRedisService.hmGet(collection, hashKey))
                    .map(val -> Mono.just(jsonToObject(val.toString(), CoverageLocationWithBackupInfoDto.class)))
                    .orElseGet(() -> Mono.just(new CoverageLocationWithBackupInfoDto()));

        } catch (Exception e) {

            log.error("Error during getting hashkey:{}",e.getMessage());

            return Mono
                    .just(new CoverageLocationWithBackupInfoDto());

        }
    }

    @Override
    public Mono<ResponseDto> deleteHashKey(String collection, String hashKeys) {

        try {

            Long resp =  gcpRedisService.deleteHashkey(collection, hashKeys.split(","));

            log.info("response about deleteHashKey:{}",resp);

            return Mono
                    .just(ResponseDto
                            .builder()
                            .success(true)
                            .build()
                    );

        } catch(Exception e){
            log.error("Error during delete hashkey:{}",e.getMessage());

            return Mono
                    .just(ResponseDto
                            .builder()
                            .success(false)
                            .message("Error:"+e.getMessage())
                            .build()
                    );

        }
    }

    @Override
    public Mono<ResponseDto> setExpirationTime(String collection, int seconds) {
        try {

            log.info("Setting expiration using collection:{} and seconds:{}",collection, seconds);

            gcpRedisService.setExpirationTime(collection, seconds);

            return Mono
                    .just(ResponseDto
                            .builder()
                            .success(true)
                            .build()
                    );


        } catch(Exception e) {
            log.error("Error in setExpirationTime:{}", e.getMessage());

            return Mono
                    .just(ResponseDto
                            .builder()
                            .success(false)
                            .message("Error:"+e.getMessage())
                            .build()
                    );

        }
    }

    private <T> T jsonToObject(String json, Class<T> valueType) {
        try {
            return new ObjectMapper().readValue(json, valueType);
        } catch (JsonProcessingException ex) {
            log.error("jsonToObject:{} ", ex.getMessage());
        }
        return null;
    }
}
