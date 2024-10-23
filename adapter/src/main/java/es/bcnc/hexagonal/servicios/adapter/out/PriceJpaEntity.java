package es.bcnc.hexagonal.servicios.adapter.out;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="PriceEntity")
@Table(name="TBL_PRICES")
@Builder
public class PriceJpaEntity {

    //PRICE_LIST: Identificador de la tarifa de precios aplicable.
    @Id
    @Column(name= "PRICE_LIST")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long priceList;

    //BRAND_ID: foreign key de la cadena del grupo (1 = ZARA).
    @Column(name= "BRAND_ID")
    private Integer brandId;

    //START_DATE , END_DATE: Rango de fechas en el que aplica el precio tarifa indicado.
    @Column(name= "START_DATE")
    private LocalDateTime startDate;

    @Column(name= "END_DATE")
    private LocalDateTime endDate;

    //PRODUCT_ID: Identificador código de producto.
    @Column(name= "PRODUCT_ID")
    private Integer productId;

    //PRIORITY: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).
    @Column(name= "PRIORITY")
    private Integer priority;

    //PRICE: Precio final de venta.
    @Column(name= "PRICE")
    private BigDecimal price;

    //CURR: iso de la moneda.
    @Column(name= "CURR")
    private String curr;
}
