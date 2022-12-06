package pe.farmaciasperuanas.digital.gcp.redis.application.port.in;

import pe.farmaciasperuanas.digital.gcp.redis.domain.RequestRedisDto;
import pe.farmaciasperuanas.digital.gcp.redis.domain.ResponseDto;
import pe.farmaciasperuanas.digital.gcp.redis.domain.atlasmongo.AtlasMongoEventDto;
import pe.farmaciasperuanas.digital.gcp.redis.domain.stockbackup.CoverageLocationWithBackupInfoDto;
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
    Mono<ResponseDto> setHashStringInCache(String collection, String key, RequestRedisDto requestCacheManagerDto);
    Mono<ResponseDto> setHashStringDummyInCache(String collection, String key, RequestRedisDto requestCacheManagerDto);
    Mono<ResponseDto> getObjectByKeyFromRedis(String key);
    Mono<ResponseDto> getHashStringByKeyFromRedis(String collection, String hashKey);
    Mono<ResponseDto> getByPatternAndDeleteKeys(String collection, String pattern);
    Mono<CoverageLocationWithBackupInfoDto> getHashStringDummyByKeyFromRedis(String collection, String hashKey);
    Mono<ResponseDto> deleteHashKey(String collection, String hashKeys);
    Mono<ResponseDto> setExpirationTime(String collection, int seconds);
    Mono<ResponseDto> processEventReceived(AtlasMongoEventDto atlasMongoEventDto);

}
