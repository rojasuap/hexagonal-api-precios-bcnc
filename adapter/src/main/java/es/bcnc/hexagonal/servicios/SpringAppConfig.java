package es.bcnc.hexagonal.servicios;

import es.bcnc.hexagonal.servicios.application.port.in.CreatePriceUserCase;
import es.bcnc.hexagonal.servicios.application.port.in.GetPriceByCurrentDateAndProductIdAndBrandIdUserCase;
import es.bcnc.hexagonal.servicios.application.port.out.PriceRepository;
import es.bcnc.hexagonal.servicios.application.service.CreatePriceService;
import es.bcnc.hexagonal.servicios.application.service.GetPriceByCurrentDateAndProductIdAndBrandIdService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAppConfig {

    private final PriceRepository priceRepository;

    public SpringAppConfig(PriceRepository priceRepository){
        this.priceRepository=priceRepository;
    }

    @Bean
    GetPriceByCurrentDateAndProductIdAndBrandIdUserCase getPricesByCurrentDateAndProductIdAndBrandIdUserCase(){
        return new GetPriceByCurrentDateAndProductIdAndBrandIdService(priceRepository);
    }

    @Bean
    CreatePriceUserCase createPriceUserCase(){
        return new CreatePriceService(priceRepository);
    }
}
