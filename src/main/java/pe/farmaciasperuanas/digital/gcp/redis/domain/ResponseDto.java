package pe.farmaciasperuanas.digital.gcp.redis.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto {

    private Boolean cacheHit;
    private Boolean success;
    private String data;
    private String message;
}
