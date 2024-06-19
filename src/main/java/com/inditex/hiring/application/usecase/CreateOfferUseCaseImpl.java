package com.inditex.hiring.application.usecase;

import com.inditex.hiring.application.mapper.OfferMapper;
import com.inditex.hiring.domain.entities.Offer;
import com.inditex.hiring.domain.usecase.CreateOfferUseCase;
import com.inditex.hiring.domain.repository.OfferRepository;
import com.inditex.hiring.infraestructure.dto.OfferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOfferUseCaseImpl implements CreateOfferUseCase {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;

    @Override
    public OfferDto createOffer(OfferDto offerdto) {

        if (offerRepository.existsById(offerdto.getOfferId().intValue())){
            throw new DataIntegrityViolationException("Id de oferta que se intenta guardar ya existe ne bbdd");
        }

        Offer offer = this.offerMapper.offerDtoToOffer(offerdto);

        offer.setSize(offerdto.getProductPartnumber().substring(0,2));
        offer.setModel(offerdto.getProductPartnumber().substring(2,6));
        offer.setQuality(offerdto.getProductPartnumber().substring(6,9));

        return offerMapper.offerToOfferDto(offerRepository.save(offer));
    }

    //sql para pruebas
    //INSERT INTO offer (offer_id, brand_id, start_date, end_date, price_list, priority, price, curr
    // size, model, quality)
    //VALUES (1, 1, '2020-06-14T00:00:00Z', '2020-12-31T23:59:59Z', 1,  0, 35.50, 'EUR',
    // '22', '2323', '355');
}
