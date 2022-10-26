package pe.farmaciasperuanas.digital.gcp.redis.domain.atlasmongo.zone;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ZoneDto implements Serializable {

    private static final long serialVersionUID = 6987484569199644525L;

    /*
    private String idZone;
    private List<String> companyCode;
    private List<String> channel;
     */

    private String fulfillmentCenterCode;
    private Integer idZone;
    private String name;
    private String description;
    private Boolean enabled;
    private String zoneType;
    private List<Coordinates> zoneLimits;
    private Boolean isBackUpZone;
    private ZoneBackupDto zoneBackup;
    private List<String> channel;

}
