package pe.farmaciasperuanas.digital.gcp.redis.domain.atlasmongo.zone;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Slf4j
public class Coordinates implements Serializable {

    private static final long serialVersionUID = -6925237484199113476L;
    private String latitude;
    private String longitude;
}
