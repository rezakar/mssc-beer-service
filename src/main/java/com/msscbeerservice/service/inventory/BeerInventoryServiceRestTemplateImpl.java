package com.msscbeerservice.service.inventory;


import com.msscbeerservice.service.inventory.model.BeerInventoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Profile;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Profile("!local-discovery")
@Slf4j
@ConfigurationProperties(prefix = "sfg.brewery", ignoreUnknownFields = true)
@Component
public class BeerInventoryServiceRestTemplateImpl implements BeerInventoryService{

    public static final String INVENTORY_PATH = "/api/v1/beer/inventory/{beerId}";
    public static final String INVENTORY_PATH_UPC = "/api/v1/beer/inventory/{upc}/upc";
    private final RestTemplate restTemplate;

    private String beerInventoryServiceHost;

    public void setBeerInventoryServiceHost(String beerInventoryServiceHost) {
        this.beerInventoryServiceHost = beerInventoryServiceHost;
    }

//    public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder,
//                                                @Value("${sfg.brewery.inventory-user}") String inventoryUser,
//                                                @Value("${sfg.brewery.inventory-password}")String inventoryPassword) {
//        this.restTemplate = restTemplateBuilder
//                .basicAuthentication(inventoryUser, inventoryPassword)
//                .build();
//    }

    public BeerInventoryServiceRestTemplateImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public Integer getOnhandInventory(UUID beerId) {

        log.debug("Calling Inventory Service");

        ResponseEntity<BeerInventoryDto> responseEntity = restTemplate
                .exchange(beerInventoryServiceHost + INVENTORY_PATH, HttpMethod.GET, null,
                        new ParameterizedTypeReference<BeerInventoryDto>(){}, (Object) beerId);

        //sum from inventory list
        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .getQuantityOnHand();

        return onHand;
    }

    @Override
    public Integer getOnhandInventoryByUpc(String upc) {

        log.debug("Calling Inventory Service by upc");

        ResponseEntity<List<BeerInventoryDto>> responseEntity = restTemplate
                .exchange(beerInventoryServiceHost + INVENTORY_PATH_UPC, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<BeerInventoryDto>>(){}, (Object) upc);

        //sum from inventory list
        Integer onHand = Objects.requireNonNull(responseEntity.getBody())
                .stream()
                .mapToInt(BeerInventoryDto::getQuantityOnHand)
                .sum();

        return onHand;
    }
}
