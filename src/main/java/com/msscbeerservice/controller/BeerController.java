package com.msscbeerservice.controller;

import com.msscbeerservice.model.Beer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<Beer> getBeerById(@PathVariable("beerId")UUID beerId) {
        // todo impll
        return new ResponseEntity<>(Beer.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public  ResponseEntity CreateBeer(@RequestBody Beer beer) {
        //todo impl
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody Beer beer) {
        //todo impl
        return new ResponseEntity((HttpStatus.NO_CONTENT));
    }
}
