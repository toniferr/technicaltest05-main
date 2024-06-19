package com.inditex.hiring.application.usecase;

import com.inditex.hiring.domain.repository.OfferRepository;
import com.inditex.hiring.domain.usecase.DeleteAllUserCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteAllUserCaseImpl implements DeleteAllUserCase {

    private final OfferRepository offerRepository;

    @Override
    public boolean deleteAll() {
        if (offerRepository.count() != 0) {
            this.offerRepository.deleteAll();
            return true;
        }
        return false;
    }
}
