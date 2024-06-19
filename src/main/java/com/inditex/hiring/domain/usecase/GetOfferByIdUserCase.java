package com.inditex.hiring.domain.usecase;

import com.inditex.hiring.infraestructure.dto.OfferDto;

import java.util.Optional;

public interface GetOfferByIdUserCase {

    Optional<OfferDto> getById(Long id);
}
