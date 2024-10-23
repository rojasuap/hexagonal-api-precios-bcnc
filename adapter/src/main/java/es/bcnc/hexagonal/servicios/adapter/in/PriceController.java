package es.bcnc.hexagonal.servicios.adapter.in;

import es.bcnc.hexagonal.servicios.application.port.in.GetPricesByCurrentDateAndProductIdAndBrandIdUserCase;
import es.bcnc.hexagonal.servicios.model.PriceModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static es.bcnc.hexagonal.servicios.adapter.in.common.GlobalConstant.API_PRICE;

@RestController
@RequestMapping(API_PRICE)
public class PriceController {

    private final GetPricesByCurrentDateAndProductIdAndBrandIdUserCase getPricesByCurrentDateAndProductIdAndBrandIdUserCase;

    public PriceController(GetPricesByCurrentDateAndProductIdAndBrandIdUserCase getPricesByCurrentDateAndProductIdAndBrandIdUserCase){
        this.getPricesByCurrentDateAndProductIdAndBrandIdUserCase =getPricesByCurrentDateAndProductIdAndBrandIdUserCase;
    }

    @GetMapping("/{currentDate}")
    public ResponseEntity<PriceWebModel> findPricesByCurrentDateAndProductIdAndBrandId(@PathVariable String currentDate,
                                                                                  @RequestParam Integer productId,
                                                                                  @RequestParam Integer brandId) {
        Optional<PriceModel> optPriceModel= getPricesByCurrentDateAndProductIdAndBrandIdUserCase.getPricesByCurrentDateAndProductIdAndBrandId(currentDate,productId,brandId);

        Optional<PriceWebModel> optPriceWebModel = optPriceModel.map(PriceWebModel::fromDomainModel);

        return optPriceWebModel.map(priceWebModel -> ResponseEntity.status(HttpStatus.OK).body(priceWebModel))
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

}
