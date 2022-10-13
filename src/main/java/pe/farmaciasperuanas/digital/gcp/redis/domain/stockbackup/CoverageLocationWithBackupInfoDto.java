package pe.farmaciasperuanas.digital.gcp.redis.domain.stockbackup;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CoverageLocationWithBackupInfoDto {

    private String id;
    private String zoneId;
    private double latitude;
    private double longitude;
    private String zoneName;
    private String zoneDescription;
    private boolean inCoverage;
    private String fulfillmentCenterCode;
    private boolean enabled;
    private Integer deliveryServiceId;
    private LocationDto location;
    private String districtCode;
    private List<CoverageZoneBackupInfoDto> zonesBackup;
    // TODO: Price X Zone DIGITAL - V3
    private List<ServiceTypeDto> deliveriesTypes;
}
