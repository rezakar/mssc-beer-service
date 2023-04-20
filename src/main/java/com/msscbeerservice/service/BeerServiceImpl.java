package com.msscbeerservice.service;

import com.msscbeerservice.controller.NotFoundException;
import com.msscbeerservice.domain.Beer;
import com.msscbeerservice.mappers.BeerMapper;
import com.msscbeerservice.model.BeerDto;
import com.msscbeerservice.model.BeerPagedList;
import com.msscbeerservice.model.BeerStyleEnum;
import com.msscbeerservice.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private  final BeerRepository beerRepository;
    private  final BeerMapper beerMapper;
    @Override
    public BeerDto getById(UUID beerId) {
//        return beerMapper.beerToBeerDto(
//                beerRepository.findById(beerId).orElseThrow(NotFoundException::new)

        Optional<Beer> optionalBeer = beerRepository.findById(beerId);
        return beerMapper.beerToBeerDto(optionalBeer.get());
    }

    @Override
    public BeerDto createBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beer.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest) {

        BeerPagedList beerPagedList;
        Page<Beer> beerPage;
        // isEmpty() means: string == null || "".equal(string)
        // hasLength() means: str != null && !str.isEmpty()
        if (StringUtils.hasLength(beerName) && StringUtils.hasLength(String.valueOf(beerStyle))) {
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        }else if (StringUtils.hasLength(beerName) && !StringUtils.hasLength(String.valueOf(beerStyle))) {
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (!StringUtils.hasLength(beerName) && StringUtils.hasLength(String.valueOf(beerStyle))) {
            beerPage = beerRepository.findAllByBeerName(String.valueOf(beerStyle), pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

        beerPagedList = new BeerPagedList(beerPage
                .getContent()
                .stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList()),
                PageRequest
                        .of(beerPage.getPageable().getPageNumber(),
                                beerPage.getPageable().getPageSize()),
                beerPage.getTotalElements());

        return beerPagedList;
    }

    @Override
    public BeerDto getBeerByUpc(String upc) {

        return beerMapper.beerToBeerDto(beerRepository.findByUpc(upc));
    }
}
