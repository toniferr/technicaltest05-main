package com.inditex.hiring.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.Formula;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer {

	@Id
	@Column(name = "OFFER_ID")
	private Long offerId;

	@Column(name = "BRAND_ID")
	private Integer brandId;

	@Column(name = "START_DATE")
	private Timestamp startDate;

	@Column(name = "END_DATE")
	private Timestamp endDate;

	@Column(name = "PRICE_LIST")
	private Integer priceListId;

	@Transient
	private String productPartnumber;

	public String getProductPartnumber() {
		return this.size+this.model+this.quality;
	}

	@Column
	@Pattern(regexp = "^[0-9]{2}$")
	private String size;

	@Column
	@Pattern(regexp = "^[0-9]{4}$")
	private String model;

	@Column
	@Pattern(regexp = "^[0-9]{3}$")
	private String quality;

	@Column
	private Integer priority;

	@Column
	private BigDecimal price;

	@Column(name = "CURR")
	private String currencyIso;

}
