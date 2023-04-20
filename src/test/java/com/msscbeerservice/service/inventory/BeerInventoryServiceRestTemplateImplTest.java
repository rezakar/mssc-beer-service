package com.msscbeerservice.service.inventory;

import com.msscbeerservice.bootstrap.BeerLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@Disabled
@SpringBootTest
public class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getOnHandInventoryById() {
        Integer goh = beerInventoryService.getOnhandInventory(UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08"));

        System.out.println(goh);
    }

    @Test
    void getOnHandInventoryByUpc() {
        Integer goh = beerInventoryService.getOnhandInventoryByUpc("0631234200036");

        System.out.println(goh);
    }
}
