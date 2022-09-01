package pe.farmaciasperuanas.digital.gcp.redis.adapter.in;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.farmaciasperuanas.digital.gcp.redis.application.port.in.CacheManagerService;
import pe.farmaciasperuanas.digital.gcp.redis.domain.RequestRedisDto;
import pe.farmaciasperuanas.digital.gcp.redis.domain.ResponseDto;
import reactor.core.publisher.Mono;

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

  /*

  @GetMapping("/hello/{name}")
  @Cacheable("hello")
  public String dummy(@PathVariable String name) throws InterruptedException {

    Thread.sleep(5000);
    return "Hello " + name;
  }

   */

  @GetMapping("/keys/{key}")
  public Mono<ResponseDto> getObjectFromKey(@PathVariable(value="key") String key) {

    log.info("[START] getObjectFromKey from REDIS:key{}", key);

    return cacheManagerService.getObjectByKeyFromRedis(key);
  }

  @PutMapping("/keys/{key}")
  public Mono<ResponseDto> setObjectInCache(@PathVariable(value="key") String key,
                                            @RequestBody RequestRedisDto requestCacheManagerDto) {

    log.info("[START] setObjectInCache:key{}, request{}", key, requestCacheManagerDto);

    return cacheManagerService.setObjectInCache(key,requestCacheManagerDto);
  }

  @PutMapping("/bytes/keys/{key}")
  public Mono<ResponseDto> setBytesInCache(@PathVariable(value="key") String key,
                                            @RequestBody RequestRedisDto requestCacheManagerDto) {

    log.info("[START] setBytesInCache:key{}, request{}", key, requestCacheManagerDto);

    return cacheManagerService.setObjectInCache(key,requestCacheManagerDto);
  }

  @PutMapping("/hashes/collections/{collection}/bytes/keys/{key}")
  public Mono<ResponseDto> setHashBytesInCache(@PathVariable(value="collection") String collection,
                                               @PathVariable(value="key") String key,
                                               @RequestBody RequestRedisDto requestCacheManagerDto) {

    log.info("[START] setHashBytesInCache:key{}, request{}", key, requestCacheManagerDto);

    return cacheManagerService.setHashBytesInCache(collection, key,requestCacheManagerDto);
  }

  @PutMapping("/hashes/collections/{collection}/keys/{key}")
  public Mono<ResponseDto> setHashStringInCache(@PathVariable(value="collection") String collection,
                                                @PathVariable(value="key") String key,
                                                @RequestBody RequestRedisDto requestCacheManagerDto) {

    log.info("[START] setHashBytesInCache:key{}, request{}", key, requestCacheManagerDto);

    return cacheManagerService.setHashStringInCache(collection,key,requestCacheManagerDto);
  }

  @GetMapping("/keys/{key}")
  public Mono<ResponseDto> getHashStringFromKey(@PathVariable(value="key") String key) {

    log.info("[START] getObjectFromKey from REDIS:key{}", key);

    return cacheManagerService.getObjectByKeyFromRedis(key);
  }

}