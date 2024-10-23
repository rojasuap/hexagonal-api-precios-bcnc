package es.bcnc.hexagonal.servicios.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class PriceModel {

    //PRICE_LIST: Identificador de la tarifa de precios aplicable.
    private Long priceList;

    //BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
    private Integer brandId;
    //START_DATE , END_DATE: Rango de fechas en el que aplica el precio tarifa indicado.
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    //PRODUCT_ID: Identificador código de producto.
    private Integer productId;

    //PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
    private Integer priority;

    //PRICE: Precio final de venta.
    private BigDecimal price;

    //CURR: iso de la moneda.
    private String curr;
}
