package pe.farmaciasperuanas.digital.gcp.redis.domain.stockbackup;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductPackDTO {

    private boolean available;
    private boolean allowed;
    private boolean canPurcharse;
    private Integer code;
    private String warningMessage;
    private Integer requestedQuantity;
    private Integer availableQuantity;
    private Integer availableQuantityFraction;
    //private ProductDto product;
}
