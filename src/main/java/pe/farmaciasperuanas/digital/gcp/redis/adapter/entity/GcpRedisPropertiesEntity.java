package pe.farmaciasperuanas.digital.gcp.redis.adapter.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "gcpRedisProperties")
public class GcpRedisPropertiesEntity {

    @Id
    private String id;
    private String code;
    private String value;
}
