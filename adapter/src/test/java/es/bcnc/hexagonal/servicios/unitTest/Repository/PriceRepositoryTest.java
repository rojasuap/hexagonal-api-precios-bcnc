package es.bcnc.hexagonal.servicios.unitTest.Repository;

import es.bcnc.hexagonal.servicios.application.port.out.PriceRepository;
import es.bcnc.hexagonal.servicios.model.PriceModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PriceRepositoryTest {

    private static final Integer PRODUCT_ID=35455;
    private static final Integer BRAND_ID=1;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");

    @Autowired
    private PriceRepository priceRepository;

    @DisplayName("Test General Repository: Petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    @Test
    @Order(1)
    void testRepositoryGetPricesByCurrentDateAndProductIdAndBrandIdGeneral(){

        //given: Datos de Entrada
        String currentDate="2020-06-14 10.00.00";
        LocalDateTime currentDateTime = LocalDateTime.parse(currentDate, FORMATTER);
        PriceModel priceModel = new PriceModel(1L,
                1,
                LocalDateTime.parse("2020-06-14 00.00.00", FORMATTER),
                LocalDateTime.parse("2020-12-31 23.59.59", FORMATTER),
                35455,
                0,
                new BigDecimal("35.50"),
                "EUR");
        priceRepository.save(priceModel);

        //when: Acción para probar
        Optional<PriceModel> priceModelNuevo = priceRepository.getPricesByCurrentDateAndProductIdAndBrandId(currentDateTime,PRODUCT_ID,BRAND_ID);

        //then: Comporbar resultado de la prueba
        priceModelNuevo.ifPresent(model -> {assertThat(model.priceList()).isEqualTo(priceModel.priceList());
                assertThat(model.brandId()).isEqualTo(priceModel.brandId());
                assertThat(model.startDate()).isEqualTo(priceModel.startDate());
                assertThat(model.endDate()).isEqualTo(priceModel.endDate());
                assertThat(model.productId()).isEqualTo(priceModel.productId());
                assertThat(model.price()).isEqualTo(priceModel.price());
                assertThat(model.priority()).isEqualTo(priceModel.priority());
                assertThat(model.curr()).isEqualTo(priceModel.curr());
        });

    }

}
