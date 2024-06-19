package com.inditex.hiring.domain.usecase;

import com.inditex.hiring.domain.entities.Offer;
import com.inditex.hiring.infraestructure.dto.OfferDto;

import java.util.List;

public interface GetAllUseCase {

    List<OfferDto> getAll();
}
