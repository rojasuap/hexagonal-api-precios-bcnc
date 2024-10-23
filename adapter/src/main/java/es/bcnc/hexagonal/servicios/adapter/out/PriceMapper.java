package es.bcnc.hexagonal.servicios.adapter.out;

import es.bcnc.hexagonal.servicios.model.PriceModel;

import java.util.List;

@SuppressWarnings("ALL")
public class PriceMapper {
    private PriceMapper(){}

    //Convertir PriceModel a PriceJpaEntity
    public static PriceJpaEntity toEntity(PriceModel priceModel) {
        PriceJpaEntity priceEntity = new PriceJpaEntity();

        priceEntity.setPriceList(priceModel.priceList());
        priceEntity.setBrandId(priceModel.brandId());
        priceEntity.setStartDate(priceModel.startDate());
        priceEntity.setEndDate(priceModel.endDate());
        priceEntity.setProductId(priceModel.productId());
        priceEntity.setPriority(priceModel.priority());
        priceEntity.setPrice(priceModel.price());
        priceEntity.setCurr(priceModel.curr());

        return priceEntity;
    }

    //Convertir PriceJpaEntity a PriceModel
    public static PriceModel toDto(PriceJpaEntity priceJpaEntity) {

        return new PriceModel(priceJpaEntity.getPriceList(),
                priceJpaEntity.getBrandId(),
                priceJpaEntity.getStartDate(),
                priceJpaEntity.getEndDate(),
                priceJpaEntity.getProductId(),
                priceJpaEntity.getPriority(),
                priceJpaEntity.getPrice(),
                priceJpaEntity.getCurr());
    }

    //Convertir List<PriceJpaEntity> a List<PriceModel>
    public static List<PriceModel> toDtos(List<PriceJpaEntity> priceJpaEntitys){
        return priceJpaEntitys.stream().map(PriceMapper::toDto).toList();
    }
}
