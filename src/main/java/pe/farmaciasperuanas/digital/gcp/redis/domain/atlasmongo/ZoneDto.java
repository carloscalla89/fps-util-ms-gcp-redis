package pe.farmaciasperuanas.digital.gcp.redis.domain.atlasmongo;

import lombok.Data;

import java.util.List;

@Data
public class ZoneDto {

    private String idZone;
    private List<String> companyCode;
    private List<String> channel;
}
