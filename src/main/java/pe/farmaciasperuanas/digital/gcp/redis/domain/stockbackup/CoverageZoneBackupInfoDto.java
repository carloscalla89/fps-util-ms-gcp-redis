package pe.farmaciasperuanas.digital.gcp.redis.domain.stockbackup;

import lombok.Data;

@Data
public class CoverageZoneBackupInfoDto {

    private CoverageLocationWithBackupInfoDto zone;
    private String preferableLocalBackupToShow;
    private String forceServiceAMPM;
    private String forceServicePROG;
    private String forceServiceEXP;
    // TODO: Price X Zone DIGITAL - V3
    private ServiceCostDto[] serviceCost;
}
