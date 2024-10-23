package es.bcnc.hexagonal.servicios.adapter.out;

import es.bcnc.hexagonal.servicios.application.port.out.PriceRepository;
import es.bcnc.hexagonal.servicios.model.PriceModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class JpaPriceRepository implements PriceRepository {

    private final JpaPriceSpringDataRepository jpaPriceSpringDataRepository;

    public JpaPriceRepository(JpaPriceSpringDataRepository jpaPriceSpringDataRepository){
        this.jpaPriceSpringDataRepository=jpaPriceSpringDataRepository;
    }

    @Override
    @Transactional
    public Optional<PriceModel> getPricesByCurrentDateAndProductIdAndBrandId(LocalDateTime currentDate, Integer productId, Integer brandId) {
        Optional<PriceJpaEntity> optPriceJpaEntity= jpaPriceSpringDataRepository.findByCurrentDateAndProductIdAndBrandId(currentDate, productId, brandId);

        return optPriceJpaEntity.map(PriceMapper::toDto);

    }
}
