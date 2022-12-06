package pe.farmaciasperuanas.digital.gcp.redis.adapter.in;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.farmaciasperuanas.digital.gcp.redis.application.port.in.CacheManagerService;
import pe.farmaciasperuanas.digital.gcp.redis.domain.RequestRedisDto;
import pe.farmaciasperuanas.digital.gcp.redis.domain.ResponseDto;
import pe.farmaciasperuanas.digital.gcp.redis.domain.stockbackup.CoverageLocationWithBackupInfoDto;
import reactor.core.publisher.Mono;

import java.util.Base64;

/**
 * Controlador principal que expone el servicio a trav&eacute;s de HTTP/Rest para
 * las operaciones del recurso Redis<br/>
 * <b>Class</b>: RedisController<br/>
 * <b>Copyright</b>: 2022 Farmacias Peruanas.<br/>
 * <b>Company</b>:Farmacias Peruanas.<br/>
 *
 * <u>Developed by</u>: <br/>
 * <ul>
 * <li>carlos calla</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 * <li>15/07/2022 Creaci&oacute;n de Clase.</li>
 * </ul>
 * @version 1.0
 */
@Slf4j
@RequestMapping("/redis/cache-manager/v1.0/objects")
@RestController
public class RedisRest {

  private CacheManagerService cacheManagerService;

  @Autowired
  public RedisRest(CacheManagerService cacheManagerService) {
    this.cacheManagerService = cacheManagerService;
  }

  @GetMapping("/keys/{key}")
  public Mono<ResponseDto> getObjectFromKey(@PathVariable(value="key") String key) {

    log.info("[START] getObjectFromKey from REDIS:key{}", key);

    return cacheManagerService.getObjectByKeyFromRedis(key);
  }

  @PutMapping("/keys/{key}")
  public Mono<ResponseDto> setObjectInCache(@PathVariable(value="key") String key,
                                            @RequestBody RequestRedisDto requestCacheManagerDto) {

    log.info("[START] setObjectInCache:key{}, request{}", key, requestCacheManagerDto);

    return cacheManagerService
            .setObjectInCache(key,requestCacheManagerDto)
            .doOnSuccess(resp -> log.info("[END] setObjectInCache"));
  }

  @PutMapping("/hashes/collections/{collection}/keys/{key}")
  public Mono<ResponseDto> setHashStringInCache(@PathVariable(value="collection") String collection,
                                                @PathVariable(value="key") String key,
                                                @RequestBody RequestRedisDto requestCacheManagerDto) {

    log.info("[START] setHashBytesInCache: collection:{}, key:{}, request:{}", collection, key, requestCacheManagerDto);

    return cacheManagerService
            .setHashStringInCache(collection,key,requestCacheManagerDto)
            .doOnSuccess(resp -> log.info("[END] setHashBytesInCache:{}",resp));

  }

  @GetMapping("/hashes/collections/{collection}/keys/{key}")
  public Mono<ResponseDto> getHashStringFromKey(@PathVariable(value="collection") String collection,
                                                @PathVariable(value="key") String key) {

    log.info("[START] getHashStringFromKey - collection:{}, key:{}", collection, key);

    return cacheManagerService
            .getHashStringByKeyFromRedis(collection,key)
            .doOnSuccess(resp -> log.info("[END] getHashStringFromKey:{}",resp));
  }

  @GetMapping("/hashes/collections/{collection}/patterns/{pattern}")
  public Mono<ResponseDto> getByPatternAndDeleteKeys(@PathVariable(value="collection") String collection,
                                                    @PathVariable(value="pattern") String pattern) {

    log.info("[START] getByPatternAndDeleteKeys - collection:{}, pattern:{}", collection, pattern);

    return cacheManagerService
            .getByPatternAndDeleteKeys(collection,pattern)
            .doOnSuccess(resp -> log.info("[END] getByPatternAndDeleteKeys:{}",resp));
  }

  @GetMapping("/hashes/dummy/collections/{collection}/keys/{key}")
  public Mono<CoverageLocationWithBackupInfoDto> getHashStringDummyFromKey(@PathVariable(value="collection") String collection,
                                                                           @PathVariable(value="key") String key) {

    log.info("[START] getHashStringDummyFromKey - collection:{}, key:{}", collection, key);

    return cacheManagerService
            .getHashStringDummyByKeyFromRedis(collection,key)
            .doOnSuccess(resp -> log.info("[END] getHashStringFromKey:{}",resp));
  }

  @DeleteMapping("/hashes/collections/{collection}/keys/{keys}")
  public Mono<ResponseDto> deleteHashKey(@PathVariable(value="collection") String collection,
                                         @PathVariable(value="keys") String keys) {

    log.info("[START] deleteHashKey: collection:{}, key:{}", collection, keys);

    return cacheManagerService
            .deleteHashKey(collection,keys)
            .doOnSuccess(resp -> log.info("[END] deleteHashKey"));

  }

  @PutMapping("/hashes/dummy/collections/{collection}/keys/{key}")
  public Mono<ResponseDto> setHashStringInCacheDummy(@PathVariable(value="collection") String collection,
                                                     @PathVariable(value="key") String key,
                                                     @RequestBody RequestRedisDto requestCacheManagerDto) {

    log.info("[START] setHashStringInCacheDummy: collection:{}, key:{}, request:{}", collection, key, requestCacheManagerDto);

    return cacheManagerService
            .setHashStringDummyInCache(collection,key,requestCacheManagerDto)
            .doOnSuccess(resp -> log.info("[END] setHashBytesInCache:{}",resp));

  }

  @PutMapping("/hashes/collections/{collection}/expiration/{expiration}")
  public Mono<ResponseDto> setExpirationTime(@PathVariable(value="collection") String collection,
                                             @PathVariable(value="expiration") int expiration) {

    log.info("[START] setExpirationTime: collection:{}, expiration:{}", collection, expiration);

    return cacheManagerService
            .setExpirationTime(collection,expiration)
            .doOnSuccess(resp -> log.info("[END] deleteHashKey"));

  }

}
