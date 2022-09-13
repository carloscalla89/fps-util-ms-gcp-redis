package pe.farmaciasperuanas.digital.gcp.redis.domain;

import lombok.Data;

@Data
public class RequestRedisDto {

    private String payload;
    private String payloadBase64;
    private Integer expireTime;
}
