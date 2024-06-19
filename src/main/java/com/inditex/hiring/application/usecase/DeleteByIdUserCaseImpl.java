package com.inditex.hiring.application.usecase;

import com.inditex.hiring.domain.repository.OfferRepository;
import com.inditex.hiring.domain.usecase.DeleteByIdUserCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteByIdUserCaseImpl implements DeleteByIdUserCase {

    private final OfferRepository offerRepository;

    @Override
    public boolean deleteById(Long id) {
        if (offerRepository.existsById(id.intValue())) {
            offerRepository.deleteById(id.intValue());
            return true;
        } else {
            return false;
        }
    }
}
