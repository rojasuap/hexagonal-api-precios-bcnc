package es.bcnc.hexagonal.servicios.adapter.in;

import es.bcnc.hexagonal.servicios.model.PriceModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceWebModel(Long priceList, Integer brandId, LocalDateTime startDate,
                            LocalDateTime endDate, Integer productId, Integer priority,
                            BigDecimal price, String curr) {

    public static PriceWebModel fromDomainModel(PriceModel priceModel){
        return new PriceWebModel(priceModel.priceList(), priceModel.brandId(), priceModel.startDate(),
                                 priceModel.endDate(), priceModel.productId(), priceModel.priority(),
                                 priceModel.price(),priceModel.curr());
    }

    public static PriceModel toDomainModel(PriceWebModel priceWebModel){
        return new PriceModel(priceWebModel.priceList(), priceWebModel.brandId(), priceWebModel.startDate(),
                priceWebModel.endDate(), priceWebModel.productId(), priceWebModel.priority(),
                priceWebModel.price(),priceWebModel.curr());
    }
}
