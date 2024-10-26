package es.bcnc.hexagonal.servicios.application.port.out;

import es.bcnc.hexagonal.servicios.model.PriceModel;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<PriceModel> getPricesByCurrentDateAndProductIdAndBrandId(LocalDateTime currentDate,
                                                                      Integer productId,
                                                                      Integer brandId);

    PriceModel save(PriceModel priceModel);
}
