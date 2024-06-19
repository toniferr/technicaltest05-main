package com.inditex.hiring.application.mapper;

import com.inditex.hiring.infraestructure.dto.OfferDto;
import com.inditex.hiring.domain.entities.Offer;
import org.mapstruct.Mapper;

@Mapper
public interface OfferMapper {

    Offer offerDtoToOffer(OfferDto dto);

    OfferDto offerToOfferDto(Offer offer);
}
