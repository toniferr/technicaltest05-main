package com.inditex.hiring.infraestructure.rest;

import com.inditex.hiring.application.mapper.OfferMapper;
import com.inditex.hiring.domain.entities.Offer;
import com.inditex.hiring.domain.repository.OfferRepository;
import com.inditex.hiring.infraestructure.dto.OfferDto;
import com.inditex.hiring.infraestructure.exception.NoSuchResourceFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class OfferControllerIT {

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    OfferController offerController;

    @Autowired
    OfferMapper offerMapper;

    @Test
    void createOffer() {
        OfferDto offerDto = OfferDto.builder()
                .offerId(5L)
                .brandId(1)
                .startDate(new Timestamp(System.currentTimeMillis()))
                .endDate(new Timestamp(System.currentTimeMillis()))
                .priority(1)
                .productPartnumber("000100233")
                .build();

        ResponseEntity responseEntity = offerController.createOffer(offerDto);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(201));
    }

    @Test
    void deleteOfferByIdFound() {
        Offer offer = Offer.builder()
                .offerId(1L)
                .brandId(1)
                .startDate(new Timestamp(System.currentTimeMillis()))
                .endDate(new Timestamp(System.currentTimeMillis()))
                .priority(1)
                .productPartnumber("000100233")
                .price(new BigDecimal("35.5"))
                .currencyIso("EUR")
                .build();
        offerRepository.save(offer);

        ResponseEntity responseEntity = offerController.deleteOfferById(offer.getOfferId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));
    }

    @Test
    void deleteOfferByIdNotFound() {
        assertThrows(NoSuchResourceFoundException.class, () -> {
            offerController.deleteOfferById(233L);
        });
    }

    @Test
    void getOfferByIdNotFound() {
        assertThrows(NoSuchResourceFoundException.class , () -> {
            offerController.getOfferById(235L);
        });
    }

    @Test
    void getOfferByIdFound() {
        Offer offer = Offer.builder()
                .offerId(1L)
                .brandId(1)
                .startDate(new Timestamp(System.currentTimeMillis()))
                .endDate(new Timestamp(System.currentTimeMillis()))
                .priority(1)
                .productPartnumber("000100233")
                .price(new BigDecimal("35.5"))
                .currencyIso("EUR")
                .build();
        offerRepository.save(offer);

        OfferDto offerDto = offerController.getOfferById(1L);

        assertEquals(offerDto.getOfferId(), offer.getOfferId());
    }

    @Test
    void deleteAllOffers() {
        Offer offer = Offer.builder()
                .offerId(1L)
                .brandId(1)
                .startDate(new Timestamp(System.currentTimeMillis()))
                .endDate(new Timestamp(System.currentTimeMillis()))
                .priority(1)
                .productPartnumber("000100233")
                .price(new BigDecimal("35.5"))
                .currencyIso("EUR")
                .build();
        offerRepository.save(offer);

        offerController.deleteAllOffers();

        List<OfferDto> dtos = offerController.getAllOffers();

        assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    void getAllNoneOffers() {
        offerRepository.deleteAll();
        List<OfferDto> dtos = offerController.getAllOffers();

        assertThat(dtos.size()).isEqualTo(0);
    }

    @Test
    void getAllOffers() {
        Offer offer = Offer.builder()
                .offerId(1L)
                .brandId(1)
                .startDate(new Timestamp(System.currentTimeMillis()))
                .endDate(new Timestamp(System.currentTimeMillis()))
                .priority(1)
                .productPartnumber("000100233")
                .price(new BigDecimal("35.5"))
                .currencyIso("EUR")
                .build();
        offerRepository.save(offer);

        List<OfferDto> dtos = offerController.getAllOffers();

        assertThat(dtos.size()).isEqualTo(1);
    }
}