package pe.farmaciasperuanas.digital.gcp.redis.adapter.out;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import pe.farmaciasperuanas.digital.gcp.redis.application.port.out.GcpRedisService;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class GcpRedisServiceImpl implements GcpRedisService {

    private RedisTemplate redisTemplate;

    @Autowired
    public GcpRedisServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        log.info("Setting expire time in second by default...");
        redisTemplate.expire("orders",600, TimeUnit.SECONDS);
    }

    @Override
    public boolean set(String key, Object value) {
        return false;
    }

    @Override
    public boolean set(String key, Object value, Long expireTime) throws Exception{

        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);

        return true;
    }

    @Override
    public Long deleteHashkey(String key, Object... hashKeys) throws Exception {
        log.info("deleteHashKey - key:{}, hashKeys:{}",key,hashKeys);
        return redisTemplate.opsForHash().delete(key,hashKeys);

    }

    @Override
    public void remove(String... keys) {

    }

    @Override
    public void removePattern(String pattern) {

    }

    @Override
    public void remove(String key) {



    }

    @Override
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public Object get(String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    @Override
    public void hmSet(String collection, String hashKey, Object value) throws Exception{

        redisTemplate.opsForHash().put(collection, hashKey, value);
    }

    @Override
    public Object hmGet(String collection, String hkey) throws Exception {

        try {

            return redisTemplate.opsForHash().get(collection, hkey);

        } catch (Exception e) {
            if(e.getMessage() == null){
                log.error("Entry '{}' does not exist in cache", hkey);

                throw new Exception("Entry " + hkey +" does not exist in cache");

            } else {
                log.error("Unable to find entry '{}' in cache collection '{}': {}", hkey, collection, e.getMessage());

                throw new Exception("Unable to find entry " + hkey + " in cache collection " + collection + ": " + e.getMessage());
            }

        }
    }

    @Override
    public void lPush(String k, Object v) {

    }

    @Override
    public List<Object> lRange(String k, long l, long l1) {
        return null;
    }

    @Override
    public void setArray(String key, Object value) {

    }

    @Override
    public Set<Object> getArray(String key) {
        return null;
    }

    @Override
    public void zAdd(String key, Object value, double scoure) {

    }

    @Override
    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        return null;
    }

    @Override
    public void setExpirationTime(String collection, int seconds) {
        log.info("Setting expire time with seconds:{}",seconds);
        redisTemplate.expire("orders",seconds, TimeUnit.SECONDS);
    }
}
