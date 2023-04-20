package com.msscbeerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msscbeerservice.bootstrap.BeerLoader;
import com.msscbeerservice.model.BeerDto;
import com.msscbeerservice.model.BeerStyleEnum;
import com.msscbeerservice.service.BeerService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.OffsetDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerService beerService;

    BeerDto validBeer;

//    @Before
//    public void setUp() {
//        validBeer = BeerDto.builder()
//                .beerName("My Beer")
//                .beerStyle(BeerStyleEnum.ALE)
//                .price(new BigDecimal("2.99"))
//                .upc(BeerLoader.BEER_1_UPC)
//                .build();
//    }

    @Test
    public void getBeerById() throws Exception {

        given(beerService.getById(any())).willReturn(getValidBeerDto());

        mockMvc.perform(get("/api/v1/beer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void createBeer() throws Exception {

        BeerDto beerDto = getValidBeerDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    BeerDto getValidBeerDto() {

        OffsetDateTime offsetDT1 = OffsetDateTime.now();
        System.out.println("OffsetDateTime1: " + offsetDT1);

        OffsetDateTime offsetDT2 = OffsetDateTime.now(Clock.systemUTC());
        System.out.println("OffsetDateTime2: " + offsetDT2);

        return BeerDto.builder()
//                .id(UUID.randomUUID())
//                .version(12)
//                .createDate(offsetDT1)
//                .lastModifiedDate(offsetDT2)
                .beerName("Beer1")
                .beerStyle(BeerStyleEnum.PALE_ALE)
                .upc(BeerLoader.BEER_1_UPC)
                .price(new BigDecimal("12.5"))
                .build();
    }

    @Test
    public void updateBeerById() throws Exception {

        BeerDto beerDto = getValidBeerDto();

        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());
    }
}