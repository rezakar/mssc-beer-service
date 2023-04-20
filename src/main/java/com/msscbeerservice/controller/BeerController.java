package com.msscbeerservice.controller;

import com.msscbeerservice.model.BeerDto;
import com.msscbeerservice.model.BeerPagedList;
import com.msscbeerservice.model.BeerStyleEnum;
import com.msscbeerservice.service.BeerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1")
@RestController
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;
    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping(produces = {"application/json"}, path = "/beer")
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value= "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false)BeerStyleEnum beerStyle) {
        if (pageNumber == null || pageNumber < 0) {
            pageNumber= DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 0) {
            pageSize= DEFAULT_PAGE_SIZE;
        }

        BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize));
        return new ResponseEntity<>(beerList, HttpStatus.OK);
    }
    @GetMapping("/beer/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@NotNull @PathVariable("beerId")UUID beerId) {

        return new ResponseEntity<>(beerService.getById(beerId), HttpStatus.OK);
    }

    @GetMapping("/beerUpc/{upc}")
    public ResponseEntity<BeerDto> getBeerByUpc(@NotNull @PathVariable("upc")String upc) {

        return new ResponseEntity<>(beerService.getBeerByUpc(upc), HttpStatus.OK);
    }

    @PostMapping(path = "beer")
    public  ResponseEntity CreateBeer(@Valid @RequestBody BeerDto beerDto) {

        return new ResponseEntity(beerService.createBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("/beer/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId,@Validated @RequestBody BeerDto beerDto) {

        return new ResponseEntity(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);
    }

}
