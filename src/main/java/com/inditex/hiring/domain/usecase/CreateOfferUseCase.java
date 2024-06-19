package com.inditex.hiring.domain.usecase;

import com.inditex.hiring.domain.entities.Offer;
import com.inditex.hiring.infraestructure.dto.OfferDto;

public interface CreateOfferUseCase {

    OfferDto createOffer(OfferDto offerdto);
}
