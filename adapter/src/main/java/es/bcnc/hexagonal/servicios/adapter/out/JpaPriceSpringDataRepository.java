package es.bcnc.hexagonal.servicios.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface JpaPriceSpringDataRepository extends JpaRepository<PriceJpaEntity,Long> {
    // Query Nativo como se ejecutaria SQL
    @Query(nativeQuery=true, value= "SELECT * FROM TBL_PRICES WHERE START_DATE<=:currentDate AND END_DATE>=:currentDate " +
            "AND PRODUCT_ID=:productId AND BRAND_ID=:brandId ORDER BY PRIORITY DESC LIMIT 1")
    Optional<PriceJpaEntity> findByCurrentDateAndProductIdAndBrandId(@Param("currentDate") LocalDateTime currentDate,
                                                                  @Param("productId") Integer productId,
                                                                  @Param("brandId") Integer brandId);

}
