package es.bcnc.hexagonal.servicios.application.service;

import es.bcnc.hexagonal.servicios.application.exceptions.ResourceNotFoundException;
import es.bcnc.hexagonal.servicios.application.port.in.GetPriceByCurrentDateAndProductIdAndBrandIdUserCase;
import es.bcnc.hexagonal.servicios.application.port.out.PriceRepository;
import es.bcnc.hexagonal.servicios.model.PriceModel;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class GetPriceByCurrentDateAndProductIdAndBrandIdService implements GetPriceByCurrentDateAndProductIdAndBrandIdUserCase {

    DateTimeFormatter formatoLocalDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");

    private final PriceRepository priceRepository;

    public GetPriceByCurrentDateAndProductIdAndBrandIdService(PriceRepository priceRepository){
        this.priceRepository=priceRepository;
    }


    @Override
    public Optional<PriceModel> getPricesByCurrentDateAndProductIdAndBrandId(String currentDate, Integer productId, Integer brandId) {
        LocalDateTime currentDateTime = LocalDateTime.parse(currentDate, formatoLocalDateTime);

        //Recuperamos del repository el price, en caso no sea encontrado se envia una exception.
        return Optional.ofNullable(priceRepository.getPricesByCurrentDateAndProductIdAndBrandId(currentDateTime, productId, brandId)
                .orElseThrow(() -> new ResourceNotFoundException("Precio no encontrado para los datos currentDate="
                        .concat(currentDate)
                        .concat(" - productId=" + productId.toString())
                        .concat(" - brandId=" + brandId.toString()))));
    }
}
