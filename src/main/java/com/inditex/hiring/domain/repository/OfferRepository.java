package com.inditex.hiring.domain.repository;

import com.inditex.hiring.domain.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OfferRepository extends JpaRepository<Offer, Integer> {
}
