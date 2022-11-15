package pe.farmaciasperuanas.digital.gcp.redis.domain.atlasmongo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Slf4j
public class OperationDto implements Serializable {

    private static final long serialVersionUID = -6948162194484423472L;
    // values: insert, delete, replace, update, drop, rename, dropDatabase, invalidate
    private String operationType;
    private String db;
    //zone, zoneServiceTypes
    private String coll;
}
