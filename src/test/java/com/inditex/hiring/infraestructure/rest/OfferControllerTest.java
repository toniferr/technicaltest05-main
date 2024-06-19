package com.inditex.hiring.infraestructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inditex.hiring.domain.usecase.*;
import com.inditex.hiring.infraestructure.dto.OfferDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OfferController.class)
class OfferControllerTest {

    @MockBean
    CreateOfferUseCase createOfferUseCase;

    @MockBean
    DeleteByIdUserCase deleteByIdUserCase;

    @MockBean
    DeleteAllUserCase deleteAllUserCase;

    @MockBean
    GetAllUseCase getAllUseCase;

    @MockBean
    GetOfferByIdUserCase getOfferByIdUserCase;

    @Autowired
    MockMvc mockMvc;

    List<OfferDto> offerDtos;

    OfferDto offerDto1;

    OfferDto offerDto2;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        offerDto1 = OfferDto.builder()
                .offerId(1L)
                .brandId(1)
                .startDate(new Timestamp(System.currentTimeMillis()))
                .endDate(new Timestamp(System.currentTimeMillis()))
                .priority(1)
                .productPartnumber("000100233")
                .build();

        offerDto2 = OfferDto.builder()
                .offerId(2L)
                .brandId(1)
                .startDate(new Timestamp(System.currentTimeMillis()))
                .endDate(new Timestamp(System.currentTimeMillis()))
                .priority(0)
                .productPartnumber("000101233")
                .build();

        OfferDto offerDto3 = OfferDto.builder()
                .offerId(3L)
                .brandId(1)
                .startDate(new Timestamp(System.currentTimeMillis()))
                .endDate(new Timestamp(System.currentTimeMillis()))
                .priority(2)
                .productPartnumber("000103233")
                .build();
        offerDtos = List.of(offerDto1,offerDto2,offerDto3);
    }

    @Test
    void createOffer() throws Exception {
        given(createOfferUseCase.createOffer(any(OfferDto.class))).willReturn(offerDto1);

        offerDto2.setOfferId(null);

        mockMvc.perform(post("/offer").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(offerDto2)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteOfferById() throws Exception {

        given(deleteByIdUserCase.deleteById(any())).willReturn(true);

        mockMvc.perform(
                delete("/offer/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        verify(deleteByIdUserCase).deleteById(1L);
    }

    @Test
    void getOfferById() throws Exception {

        given(getOfferByIdUserCase.getById(1L)).willReturn(Optional.of(offerDto1));

        mockMvc.perform(get("/offer/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());


        verify(getOfferByIdUserCase).getById(1L);
    }

    @Test
    void deleteAllOffers() throws Exception {
        given(deleteAllUserCase.deleteAll()).willReturn(true);

        mockMvc.perform(delete("/offer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        verify(deleteAllUserCase).deleteAll();
    }

    @Test
    void getAllOffers() throws Exception {
        given(getAllUseCase.getAll()).willReturn(offerDtos);

        mockMvc.perform(get("/offer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        verify(getAllUseCase).getAll();
    }
}