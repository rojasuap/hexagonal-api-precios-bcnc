package es.bcnc.hexagonal.servicios;

import es.bcnc.hexagonal.servicios.application.port.in.GetPricesByCurrentDateAndProductIdAndBrandIdUserCase;
import es.bcnc.hexagonal.servicios.application.port.out.PriceRepository;
import es.bcnc.hexagonal.servicios.application.service.GetPricesByCurrentDateAndProductIdAndBrandIdService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAppConfig {

    private final PriceRepository priceRepository;

    public SpringAppConfig(PriceRepository priceRepository){
        this.priceRepository=priceRepository;
    }

    @Bean
    GetPricesByCurrentDateAndProductIdAndBrandIdUserCase getPricesByCurrentDateAndProductIdAndBrandIdUserCase(){
        return new GetPricesByCurrentDateAndProductIdAndBrandIdService(priceRepository);
    }
}
