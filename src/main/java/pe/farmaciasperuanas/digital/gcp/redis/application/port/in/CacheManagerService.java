package pe.farmaciasperuanas.digital.gcp.redis.application.port.in;

import pe.farmaciasperuanas.digital.gcp.redis.domain.RequestRedisDto;
import pe.farmaciasperuanas.digital.gcp.redis.domain.ResponseDto;
import reactor.core.publisher.Mono;

/**
 * Interface for running Spring Boot framework.<br/>
 * <b>Class</b>: Application<br/>
 * <b>Copyright</b>: &copy; 2022 Digital.<br/>
 * <b>Company</b>: Digital.<br/>
 *

 * <u>Developed by</u>: <br/>
 * <ul>
 * <li>carlos calla</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 * <li>15/07/2022 GcpRedisService Interface.</li>
 * </ul>
 * @version 1.0
 */

public interface CacheManagerService {


    Mono<ResponseDto> setObjectInCache(String key, RequestRedisDto requestCacheManagerDto);
    Mono<ResponseDto> setBytesInCache(String key, RequestRedisDto requestCacheManagerDto);
    Mono<ResponseDto> setHashBytesInCache(String collection, String key, RequestRedisDto requestCacheManagerDto);
    Mono<ResponseDto> setHashStringInCache(String collection, String key, RequestRedisDto requestCacheManagerDto);
    Mono<ResponseDto> getObjectByKeyFromRedis(String key);
    Mono<ResponseDto> getBytesByKeyFromRedis(String key);

}