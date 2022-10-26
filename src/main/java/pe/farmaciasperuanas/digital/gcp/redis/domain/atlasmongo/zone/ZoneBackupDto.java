package pe.farmaciasperuanas.digital.gcp.redis.domain.atlasmongo.zone;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

@Data
public class ZoneBackupDto implements Serializable {

    private static final long serialVersionUID = 7638058780684136964L;
    private String zoneId;
    private String preferableLocalBackupToShow;
    @Field(name = "force_service_ampm")
    private String forceServiceAmpm;
    @Field(name = "force_service_prog")
    private String forceServiceProg;
}
