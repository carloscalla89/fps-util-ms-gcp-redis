package pe.farmaciasperuanas.digital.gcp.redis.adapter.in;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import pe.farmaciasperuanas.digital.gcp.redis.application.port.in.RedisService;
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

  private RedisService redisService;

  @Autowired
  public RedisRest(RedisService redisService) {
    this.redisService = redisService;
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

    return redisService.getObjectByKeyFromRedis(key);
  }

  @PutMapping("/keys/{key}")
  public Mono<ResponseDto> setObjectInCache(@PathVariable(value="key") String key,
                                            @RequestBody RequestRedisDto requestCacheManagerDto) {

    log.info("[START] setObjectInCache:key{}, request{}", key, requestCacheManagerDto);

    return redisService.setObjectInCache(key,requestCacheManagerDto);
  }

}