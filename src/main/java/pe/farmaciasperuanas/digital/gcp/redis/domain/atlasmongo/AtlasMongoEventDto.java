package pe.farmaciasperuanas.digital.gcp.redis.domain.atlasmongo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import pe.farmaciasperuanas.digital.gcp.redis.domain.atlasmongo.zone.ZoneDto;
import pe.farmaciasperuanas.digital.gcp.redis.domain.atlasmongo.zoneServicesType.ZoneServiceTypeDto;

import java.io.Serializable;

/**
 * Main class for running Spring Boot framework.<br/>
 * <b>Class</b>: Application<br/>
 * <b>Copyright</b>: 2022 Farmacias Peruanas.<br/>
 * <b>Company</b>: Farmacias Peruanas.<br/>
 *

 * <u>Developed by</u>: <br/>
 * <ul>
 * <li>carlos calla</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 * <li>Oct 19, 2022 AtlasMongoEventDto Class.</li>
 * </ul>
 * @version 1.0
 */
@Data
@Slf4j
public class AtlasMongoEventDto implements Serializable {

    private static final long serialVersionUID = -5792936291391193328L;
    private String id;
    private OperationDto operation;
    private ZoneDto collection;
    private ZoneServiceTypeDto collectionZoneServiceType;

}
