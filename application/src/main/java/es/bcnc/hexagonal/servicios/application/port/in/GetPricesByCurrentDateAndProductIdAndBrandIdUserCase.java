package es.bcnc.hexagonal.servicios.application.port.in;

import es.bcnc.hexagonal.servicios.model.PriceModel;

import java.util.Optional;

public interface GetPricesByCurrentDateAndProductIdAndBrandIdUserCase {
    Optional<PriceModel> getPricesByCurrentDateAndProductIdAndBrandId(String currentDate,
                                                                      Integer productId,
                                                                      Integer brandId);
}
