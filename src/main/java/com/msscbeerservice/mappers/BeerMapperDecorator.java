package com.msscbeerservice.mappers;

import com.msscbeerservice.domain.Beer;
import com.msscbeerservice.model.BeerDto;
import com.msscbeerservice.service.inventory.BeerInventoryService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BeerMapperDecorator implements BeerMapper{

    private BeerInventoryService beerInventoryService;
    private BeerMapper beerMapper;

    @Autowired
    public void setBeerInventoryService(BeerInventoryService beerInventoryService) {
        this.beerInventoryService = beerInventoryService;
    }

    @Autowired
    public void setBeerMapper(BeerMapper beerMapper) {
        this.beerMapper = beerMapper;
    }

    @Override
    public BeerDto beerToBeerDto(Beer beer) {
        BeerDto dto = beerMapper.beerToBeerDto(beer);
//        dto.setQuantityOnHand(beerInventoryService.getOnhandInventory(beer.getId()));
//        System.out.println(dto);
        return dto;
    }

    @Override
    public Beer beerDtoToBeer(BeerDto beerDto) {
        return beerMapper.beerDtoToBeer(beerDto);
    }
}
