package com.inditex.hiring.application.usecase;

import com.inditex.hiring.application.mapper.OfferMapper;
import com.inditex.hiring.domain.repository.OfferRepository;
import com.inditex.hiring.domain.usecase.GetOfferByIdUserCase;
import com.inditex.hiring.infraestructure.dto.OfferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetOfferByIdUserCaseImpl implements GetOfferByIdUserCase {

    private final OfferMapper offerMapper;

    private final OfferRepository offerRepository;

    @Override
    public Optional<OfferDto> getById(Long id) {

        return Optional.ofNullable(offerMapper.offerToOfferDto(
                offerRepository.findById(id.intValue()).orElse(null))
        );
    }
}
