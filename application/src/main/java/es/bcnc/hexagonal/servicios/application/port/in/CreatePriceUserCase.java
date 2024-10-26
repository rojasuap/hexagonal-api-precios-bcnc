package es.bcnc.hexagonal.servicios.application.port.in;

import es.bcnc.hexagonal.servicios.model.PriceModel;

public interface CreatePriceUserCase {
    PriceModel createPrice(PriceModel priceModel);
}
