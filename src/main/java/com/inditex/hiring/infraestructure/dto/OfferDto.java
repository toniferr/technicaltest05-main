package com.inditex.hiring.infraestructure.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * Use this POJO for offer service end point responses.
 */
@Data
@Builder
public class OfferDto implements Serializable {

	private static final long serialVersionUID = 448171649369562796L;

	private Long offerId;

	@NotNull(message = "Mandatory value brandId")
	private Integer brandId;

	@NotNull
	private Timestamp startDate;

	@NotNull
	private Timestamp endDate;

	private Long priceListId;
	private String productPartnumber;
	private Integer priority;
	private BigDecimal price;
	private String currencyIso;

}