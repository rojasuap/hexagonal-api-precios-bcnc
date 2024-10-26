package es.bcnc.hexagonal.servicios.unitTest.Controller;

import es.bcnc.hexagonal.servicios.adapter.in.PriceWebModel;
import es.bcnc.hexagonal.servicios.application.port.in.GetPriceByCurrentDateAndProductIdAndBrandIdUserCase;
import es.bcnc.hexagonal.servicios.model.PriceModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PricesControllerTest {

    private static final Integer PRODUCT_ID=35455;
    private static final Integer BRAND_ID=1;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPriceByCurrentDateAndProductIdAndBrandIdUserCase getPricesByCurrentDateAndProductIdAndBrandIdUserCase;

    @DisplayName("Test 1: Petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    @Test
    @Order(1)
    void testControllerGetPricesByCurrentDateAndProductIdAndBrandId01() throws Exception{
        //given: Datos de Entrada
        String currentDate="2020-06-14 10.00.00";

        PriceWebModel priceWebModel = new PriceWebModel(1L,
                1,
                LocalDateTime.parse("2020-06-14 00.00.00", FORMATTER),
                LocalDateTime.parse("2020-12-31 23.59.59", FORMATTER),
                35455,
                0,
                new BigDecimal("35.50"),
                "EUR");
        PriceModel priceModel = PriceWebModel.toDomainModel(priceWebModel);
        given(getPricesByCurrentDateAndProductIdAndBrandIdUserCase.getPricesByCurrentDateAndProductIdAndBrandId("2020-06-14 10.00.00", 35455,1)).willReturn(Optional.of(priceModel));

        //when: Acción para probar
        ResultActions response = mockMvc.perform(get("/v1/prices/{currentDate}?productId={productId}&brandId={brandId}", currentDate,PRODUCT_ID, BRAND_ID));

        //then: Comporbar resultado de la prueba
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.priceList", is(priceWebModel.priceList()), Long.class))
                .andExpect(jsonPath("$.brandId", is(priceWebModel.brandId())))
                .andExpect(jsonPath("$.productId", is(priceWebModel.productId())))
                .andExpect(jsonPath("$.priority", is(priceWebModel.priority())))
                .andExpect(jsonPath("$.price").value(priceWebModel.price().stripTrailingZeros()))
                .andExpect(jsonPath("$.curr", is(priceWebModel.curr())));
    }

    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    @Test
    @Order(2)
    void testControllerGetPricesByCurrentDateAndProductIdAndBrandId02() throws Exception{
        //given: Datos de Entrada
        String currentDate="2020-06-14 10.00.00";

        PriceWebModel priceWebModel = new PriceWebModel(2L,
                1,
                null,
                null,
                35455,
                1,
                new BigDecimal("25.45"),
                "EUR");
        PriceModel priceModel = PriceWebModel.toDomainModel(priceWebModel);
        given(getPricesByCurrentDateAndProductIdAndBrandIdUserCase.getPricesByCurrentDateAndProductIdAndBrandId("2020-06-14 16.00.00", 35455,1)).willReturn(Optional.of(priceModel));

        //when: Acción para probar
        ResultActions response = mockMvc.perform(get("/v1/prices/{currentDate}?productId={productId}&brandId={brandId}", currentDate,PRODUCT_ID, BRAND_ID));

        //then: Comporbar resultado de la prueba
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.priceList",is(priceWebModel.priceList()),Long.class))
                .andExpect(jsonPath("$.brandId",is(priceWebModel.brandId())))
                .andExpect(jsonPath("$.productId",is(priceWebModel.productId())))
                .andExpect(jsonPath("$.priority",is(priceWebModel.priority())))
                .andExpect(jsonPath("$.price",is("25.45")))
                .andExpect(jsonPath("$.curr",is(priceWebModel.curr())));
    }

    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    @Test
    @Order(3)
    void testControllerGetPricesByCurrentDateAndProductIdAndBrandId03() throws Exception{
        //given: Datos de Entrada
        String currentDate="2020-06-14 21.00.00";

        PriceWebModel priceWebModel = new PriceWebModel(1L,
                1,
                null,
                null,
                35455,
                0,
                new BigDecimal("35.50"),
                "EUR");
        PriceModel priceModel = PriceWebModel.toDomainModel(priceWebModel);
        given(getPricesByCurrentDateAndProductIdAndBrandIdUserCase.getPricesByCurrentDateAndProductIdAndBrandId("2020-06-14 21.00.00", 35455,1)).willReturn(Optional.of(priceModel));

        //when: Acción para probar
        ResultActions response = mockMvc.perform(get("/v1/prices/{currentDate}?productId={productId}&brandId={brandId}", currentDate,PRODUCT_ID, BRAND_ID));

        //then: Comporbar resultado de la prueba
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.priceList",is(priceWebModel.priceList().toString())))
                .andExpect(jsonPath("$.brandId",is(priceWebModel.brandId())))
                .andExpect(jsonPath("$.startDate",is("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$.endDate",is("2020-12-31T23:59:59")))
                .andExpect(jsonPath("$.productId",is(priceWebModel.productId())))
                .andExpect(jsonPath("$.priority",is(priceWebModel.priority())))
                .andExpect(jsonPath("$.price",is("35.5")))
                .andExpect(jsonPath("$.curr",is(priceWebModel.curr())));
    }

    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
    @Test
    @Order(4)
    void testControllerGetPricesByCurrentDateAndProductIdAndBrandId04() throws Exception{
        //given: Datos de Entrada
        String currentDate="2020-06-14 10.00.00";

        PriceWebModel priceWebModel = new PriceWebModel(3L,
                1,
                null,
                null,
                35455,
                1,
                new BigDecimal("30.50"),
                "EUR");
        PriceModel priceModel = PriceWebModel.toDomainModel(priceWebModel);
        given(getPricesByCurrentDateAndProductIdAndBrandIdUserCase.getPricesByCurrentDateAndProductIdAndBrandId("2020-06-15 10.00.00", 35455,1)).willReturn(Optional.of(priceModel));

        //when: Acción para probar
        ResultActions response = mockMvc.perform(get("/v1/prices/{currentDate}?productId={productId}&brandId={brandId}", currentDate,PRODUCT_ID, BRAND_ID));

        //then: Comporbar resultado de la prueba
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.priceList",is(priceWebModel.priceList().toString())))
                .andExpect(jsonPath("$.brandId",is(priceWebModel.brandId())))
                .andExpect(jsonPath("$.startDate",is("2020-06-15T00:00:00")))
                .andExpect(jsonPath("$.endDate",is("2020-06-15T11:00:00")))
                .andExpect(jsonPath("$.productId",is(priceWebModel.productId())))
                .andExpect(jsonPath("$.priority",is(priceWebModel.priority())))
                .andExpect(jsonPath("$.price",is("30.5")))
                .andExpect(jsonPath("$.curr",is(priceWebModel.curr())));
    }

    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
    @Test
    @Order(5)
    void testControllerGetPricesByCurrentDateAndProductIdAndBrandId05() throws Exception{
        //given: Datos de Entrada
        String currentDate="2020-06-14 10.00.00";

        PriceWebModel priceWebModel = new PriceWebModel(4L,
                1,
                null,
                null,
                35455,
                1,
                new BigDecimal("38.95"),
                "EUR");
        PriceModel priceModel = PriceWebModel.toDomainModel(priceWebModel);
        given(getPricesByCurrentDateAndProductIdAndBrandIdUserCase.getPricesByCurrentDateAndProductIdAndBrandId("2020-06-16 21.00.00", 35455,1)).willReturn(Optional.of(priceModel));

        //when: Acción para probar
        ResultActions response = mockMvc.perform(get("/v1/prices/{currentDate}?productId={productId}&brandId={brandId}", currentDate,PRODUCT_ID, BRAND_ID));

        //then: Comporbar resultado de la prueba
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.priceList",is(priceWebModel.priceList().toString())))
                .andExpect(jsonPath("$.brandId",is(priceWebModel.brandId())))
                .andExpect(jsonPath("$.startDate",is("2020-06-15T16:00:00")))
                .andExpect(jsonPath("$.endDate",is("2020-12-31T23:59:59")))
                .andExpect(jsonPath("$.productId",is(priceWebModel.productId())))
                .andExpect(jsonPath("$.priority",is(priceWebModel.priority())))
                .andExpect(jsonPath("$.price",is("35.5")))
                .andExpect(jsonPath("$.curr",is(priceWebModel.curr())));

    }
}
