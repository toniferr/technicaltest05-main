package com.inditex.hiring.application.usecase;

import com.inditex.hiring.domain.usecase.GetAllUseCase;
import com.inditex.hiring.domain.repository.OfferRepository;
import com.inditex.hiring.infraestructure.dto.OfferDto;
import com.inditex.hiring.application.mapper.OfferMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllUseCaseImpl implements GetAllUseCase {

    private final OfferRepository offerRepository;

    private final OfferMapper offerMapper;

    @Override
    public List<OfferDto> getAll() {
        return offerRepository.findAll()
                .stream()
                .map(offerMapper::offerToOfferDto)
                .collect(Collectors.toList());
    }
}
