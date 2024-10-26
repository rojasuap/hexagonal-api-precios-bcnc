package es.bcnc.hexagonal.servicios.application.service;

import es.bcnc.hexagonal.servicios.application.exceptions.ResourceNotFoundException;
import es.bcnc.hexagonal.servicios.application.port.in.CreatePriceUserCase;
import es.bcnc.hexagonal.servicios.application.port.out.PriceRepository;
import es.bcnc.hexagonal.servicios.model.PriceModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class CreatePriceService implements CreatePriceUserCase {

    private final PriceRepository priceRepository;

    public CreatePriceService(PriceRepository priceRepository){
        this.priceRepository=priceRepository;
    }


    @Override
    public PriceModel createPrice(PriceModel priceModel) {
        return priceRepository.save(priceModel);
    }
}
