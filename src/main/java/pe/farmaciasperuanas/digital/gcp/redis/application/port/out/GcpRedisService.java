package pe.farmaciasperuanas.digital.gcp.redis.application.port.out;

import java.util.List;
import java.util.Set;

public interface GcpRedisService {

    boolean set(String key, Object value);

    boolean set(String key, Object value, Long expireTime);

    void remove(String... keys);

    /**
     * Eliminar claves en lote
     * @param pattern
     */
    void removePattern(String pattern);

    /**
     * Eliminar el valor correspondiente
     * @param key
     */
    void remove(String key);
    /**
     * Determine si hay un valor correspondiente en el caché
     * @param key
     * @return
     */
    boolean exists(String key);

    /**
     * Leer caché
     * @param key
     * @return
     */
    Object get(String key);
    /**
     * Hash agregado
     * @param collection
     * @param hashKey
     * @param value
     */
    boolean hmSet(String collection, String hashKey, Object value);

    /**
     * Hash para obtener datos
     * @param collection
     * @param hashKey
     * @return
     */
    Object hmGet(String collection, String hashKey) throws Exception;

    /**
     * Lista agregada
     * @param k
     * @param v
     */
    void lPush(String k, Object v);

    /**
     * Lista de adquisición
     * @param k
     * @param l
     * @param l1
     * @return
     */
    List<Object> lRange(String k, long l, long l1);

    /**
     * Colección agregar
     * @param key
     * @param value
     */
    void setArray(String key, Object value);

    /**
     * Adquisición de colecciones
     * @param key
     * @return
     */
    Set<Object> getArray(String key);

    /**
     * Agregar colección ordenada
     * @param key
     * @param value
     * @param scoure
     */
    void zAdd(String key, Object value, double scoure);

    /**
     * Adquisición de colección ordenada
     * @param key
     * @param scoure
     * @param scoure1
     * @return
     */
    Set<Object> rangeByScore(String key, double scoure, double scoure1);
}
