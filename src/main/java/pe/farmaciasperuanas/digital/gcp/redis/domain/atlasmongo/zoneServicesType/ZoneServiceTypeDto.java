package pe.farmaciasperuanas.digital.gcp.redis.domain.atlasmongo.zoneServicesType;

import lombok.Data;

import java.io.Serializable;

@Data
public class ZoneServiceTypeDto implements Serializable {

    private static final long serialVersionUID = -6229059355767139034L;
    private String serviceTypeId;
    private String zoneId;
    private String segmentGap;
    private String startHour;
    private String endHour;
    private Boolean enabled;
    private String serviceTypeCode;
}
