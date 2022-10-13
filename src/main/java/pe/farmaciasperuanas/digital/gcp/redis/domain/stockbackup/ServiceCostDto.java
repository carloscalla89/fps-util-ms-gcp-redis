package pe.farmaciasperuanas.digital.gcp.redis.domain.stockbackup;

import lombok.Data;
import org.bson.types.ObjectId;

@Data
public class ServiceCostDto {

    private String serviceTypeId = new ObjectId().toString();
    private String cost;
}
