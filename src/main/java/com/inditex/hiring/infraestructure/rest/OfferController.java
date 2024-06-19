package com.inditex.hiring.infraestructure.rest;

import java.util.List;
import java.util.Optional;

import com.inditex.hiring.domain.usecase.*;
import com.inditex.hiring.infraestructure.exception.NoSuchResourceFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.inditex.hiring.infraestructure.dto.OfferDto;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OfferController {

	private final CreateOfferUseCase createOfferUseCase;

	private final GetAllUseCase getAllUseCase;

	private final GetOfferByIdUserCase getOfferByIdUserCase;

	private final DeleteByIdUserCase deleteByIdUserCase;

	private final DeleteAllUserCase deleteAllUserCase;

    //Crear
	@RequestMapping(value="/offer", method = RequestMethod.POST)
	public ResponseEntity createOffer(@RequestBody @Valid OfferDto offerdto){
		OfferDto offerDto = this.createOfferUseCase.createOffer(offerdto);
		log.debug("createOffer: offerDto: "+offerDto);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	//Borrar por id
	@RequestMapping(value="/offer/{id}", method = RequestMethod.DELETE)
	public ResponseEntity deleteOfferById(@PathVariable("id") Long id){

		if(!this.deleteByIdUserCase.deleteById(id)){
			log.debug("deleteOfferById: no hay registros para borrar");
			throw new NoSuchResourceFoundException("No existen registros para borrar");
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	//Obtener por id
	@RequestMapping(value="/offer/{id}", method = RequestMethod.GET)
	public OfferDto getOfferById(@PathVariable Long id){
		return this.getOfferByIdUserCase.getById(id).orElseThrow(() -> new NoSuchResourceFoundException("No existe oferta con id "+id));
	}

	//Eliminar todas las ofertas
	@RequestMapping(value = "/offer", method = RequestMethod.DELETE)
	public ResponseEntity deleteAllOffers() {
		if (!this.deleteAllUserCase.deleteAll()) {
			log.debug("deleteOfferById: no hay registros para borrar");
			throw new NoSuchResourceFoundException("No hay registros para borrar");
		}
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	//Endopint para optener todas las ofertas
	@RequestMapping(value = "/offer", method = RequestMethod.GET)
	public List<OfferDto> getAllOffers() {
		return this.getAllUseCase.getAll();
	}
}