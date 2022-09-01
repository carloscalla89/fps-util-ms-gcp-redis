package pe.farmaciasperuanas.digital.gcp.redis.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {

    private Boolean cacheHit;
    private Boolean saved;
    private String data;
    private byte[] bytes;
    private String error;
}
