package pe.farmaciasperuanas.digital.gcp.redis.domain.stockbackup;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocationDto {

    private String name;
    private String province;
    private String department;
    private String ubigeoCode;
}
