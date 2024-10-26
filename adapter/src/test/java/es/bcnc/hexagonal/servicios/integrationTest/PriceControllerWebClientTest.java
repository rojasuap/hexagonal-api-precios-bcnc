package es.bcnc.hexagonal.servicios.integrationTest;

import es.bcnc.hexagonal.servicios.adapter.in.PriceWebModel;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.math.BigDecimal;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class PriceControllerWebClientTest {

    @Autowired
    private WebTestClient webTestClient;

    @DisplayName("Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    @Test
    @Order(1)
    void testIntegrationGetPricesByCurrentDateAndProductIdAndBrandId01(){

        //given
        PriceWebModel priceWebModel = new PriceWebModel(1L,
                1,
                null,
                null,
                35455,
                0,
                new BigDecimal("35.50"),
                "EUR");

        //when
        WebTestClient.ResponseSpec response = this.webTestClient
                .get()
                .uri("http://localhost:9090/v1/prices/2020-06-14 10.00.00?productId=35455&brandId=1")
                .exchange();

        //then
               response.expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(priceWebModel.priceList())
                .jsonPath("$.brandId").isEqualTo(priceWebModel.brandId())
                .jsonPath("$.startDate").isEqualTo("2020-06-14T00:00:00")
                .jsonPath("$.endDate").isEqualTo("2020-12-31T23:59:59")
                .jsonPath("$.productId").isEqualTo(priceWebModel.productId())
                .jsonPath("$.priority").isEqualTo(priceWebModel.priority())
                .jsonPath("$.price").isEqualTo("35.5")
                .jsonPath("$.curr").isEqualTo(priceWebModel.curr());
    }

    @DisplayName("Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    @Test
    @Order(2)
    void testIntegrationGetPricesByCurrentDateAndProductIdAndBrandId02(){

        //given
        PriceWebModel priceWebModel = new PriceWebModel(2L,
                1,
                null,
                null,
                35455,
                1,
                new BigDecimal("25.45"),
                "EUR");

        //when
        WebTestClient.ResponseSpec response = this.webTestClient
                .get()
                .uri("http://localhost:9090/v1/prices/2020-06-14 16.00.00?productId=35455&brandId=1")
                .exchange();

        //then
        response.expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(priceWebModel.priceList())
                .jsonPath("$.brandId").isEqualTo(priceWebModel.brandId())
                .jsonPath("$.startDate").isEqualTo("2020-06-14T15:00:00")
                .jsonPath("$.endDate").isEqualTo("2020-06-14T18:30:00")
                .jsonPath("$.productId").isEqualTo(priceWebModel.productId())
                .jsonPath("$.priority").isEqualTo(priceWebModel.priority())
                .jsonPath("$.price").isEqualTo("25.45")
                .jsonPath("$.curr").isEqualTo(priceWebModel.curr());
    }

    @DisplayName("Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)")
    @Test
    @Order(3)
    void testIntegrationGetPricesByCurrentDateAndProductIdAndBrandId03(){

        //given
        PriceWebModel priceWebModel = new PriceWebModel(1L,
                1,
                null,
                null,
                35455,
                0,
                new BigDecimal("35.50"),
                "EUR");

        //when
        WebTestClient.ResponseSpec response = this.webTestClient
                .get()
                .uri("http://localhost:9090/v1/prices/2020-06-14 21.00.00?productId=35455&brandId=1")
                .exchange();

        //then
        response.expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(priceWebModel.priceList())
                .jsonPath("$.brandId").isEqualTo(priceWebModel.brandId())
                .jsonPath("$.startDate").isEqualTo("2020-06-14T00:00:00")
                .jsonPath("$.endDate").isEqualTo("2020-12-31T23:59:59")
                .jsonPath("$.productId").isEqualTo(priceWebModel.productId())
                .jsonPath("$.priority").isEqualTo(priceWebModel.priority())
                .jsonPath("$.price").isEqualTo("35.5")
                .jsonPath("$.curr").isEqualTo(priceWebModel.curr());
    }

    @DisplayName("Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)")
    @Test
    @Order(4)
    void testIntegrationGetPricesByCurrentDateAndProductIdAndBrandId04(){

        //given
        PriceWebModel priceWebModel = new PriceWebModel(3L,
                1,
                null,
                null,
                35455,
                1,
                new BigDecimal("30.50"),
                "EUR");

        //when
        WebTestClient.ResponseSpec response = this.webTestClient
                .get()
                .uri("http://localhost:9090/v1/prices/2020-06-15 10.00.00?productId=35455&brandId=1")
                .exchange();

        //then
        response.expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(priceWebModel.priceList())
                .jsonPath("$.brandId").isEqualTo(priceWebModel.brandId())
                .jsonPath("$.startDate").isEqualTo("2020-06-15T00:00:00")
                .jsonPath("$.endDate").isEqualTo("2020-06-15T11:00:00")
                .jsonPath("$.productId").isEqualTo(priceWebModel.productId())
                .jsonPath("$.priority").isEqualTo(priceWebModel.priority())
                .jsonPath("$.price").isEqualTo("30.5")
                .jsonPath("$.curr").isEqualTo(priceWebModel.curr());
    }

    @DisplayName("Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)")
    @Test
    @Order(5)
    void testIntegrationGetPricesByCurrentDateAndProductIdAndBrandId05(){

        //given
        PriceWebModel priceWebModel = new PriceWebModel(4L,
                1,
                null,
                null,
                35455,
                1,
                new BigDecimal("38.95"),
                "EUR");

        //when
        WebTestClient.ResponseSpec response = this.webTestClient
                .get()
                .uri("http://localhost:9090/v1/prices/2020-06-16 21.00.00?productId=35455&brandId=1")
                .exchange();

        //then
        response.expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.priceList").isEqualTo(priceWebModel.priceList())
                .jsonPath("$.brandId").isEqualTo(priceWebModel.brandId())
                .jsonPath("$.startDate").isEqualTo("2020-06-15T16:00:00")
                .jsonPath("$.endDate").isEqualTo("2020-12-31T23:59:59")
                .jsonPath("$.productId").isEqualTo(priceWebModel.productId())
                .jsonPath("$.priority").isEqualTo(priceWebModel.priority())
                .jsonPath("$.price").isEqualTo("38.95")
                .jsonPath("$.curr").isEqualTo(priceWebModel.curr());
    }

}
