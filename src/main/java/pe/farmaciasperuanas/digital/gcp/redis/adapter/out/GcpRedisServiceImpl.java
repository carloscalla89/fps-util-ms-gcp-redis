package pe.farmaciasperuanas.digital.gcp.redis.adapter.out;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import pe.farmaciasperuanas.digital.gcp.redis.application.port.out.GcpRedisService;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class GcpRedisServiceImpl implements GcpRedisService {

    private RedisTemplate redisTemplate;

    @Autowired
    public GcpRedisServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean set(String key, Object value) {
        return false;
    }

    @Override
    public boolean set(String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
            operations.set(key, value);
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
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
    public void hmSet(String key, Object hashKey, Object value) {

    }

    @Override
    public Object hmGet(String key, Object hashKey) {
        return null;
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
}
