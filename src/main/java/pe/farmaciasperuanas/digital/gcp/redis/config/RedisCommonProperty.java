package pe.farmaciasperuanas.digital.gcp.redis.config;

import lombok.Data;

@Data
public class RedisCommonProperty {

    private String host;
    private int port;
    private int database;
}
