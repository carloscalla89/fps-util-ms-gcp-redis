package pe.farmaciasperuanas.digital.gcp.redis.domain.stockbackup;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceTypeDto<TimeMeasureUnit> {

    private String id;
    private String service;
    private TimeMeasureUnit timeMeasureUnit;
    private BigDecimal serviceCost;
    private boolean serviceNew;
    //private Channel channel;​
    private String startHour;
    private String endHour;
    @NotNull
    private Integer segmentGap;
    private boolean enabled;
    private Integer orderView;
    private String companyCode;
    private String serviceTypeId = new ObjectId().toString();
    private String fulfillmentCenterCode;
}
