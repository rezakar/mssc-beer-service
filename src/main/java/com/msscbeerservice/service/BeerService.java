package com.msscbeerservice.service;

import com.msscbeerservice.model.BeerDto;
import com.msscbeerservice.model.BeerPagedList;
import com.msscbeerservice.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {

    BeerDto getById(UUID beerId);

    BeerDto createBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest);

    BeerDto getBeerByUpc(String upc);
}
