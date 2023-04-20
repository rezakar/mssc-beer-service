package com.msscbeerservice.service.brewing;

import com.msscbeerservice.config.JmsConfig;
import com.msscbeerservice.domain.Beer;
import com.msscbeerservice.events.BrewBeerEvent;
import com.msscbeerservice.mappers.BeerMapper;
import com.msscbeerservice.repository.BeerRepository;
import com.msscbeerservice.service.inventory.BeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BrewingService {

//    private final BeerRepository beerRepository;
//    private final BeerInventoryService beerInventoryService;
//    private final JmsTemplate jmsTemplate;
//    private final BeerMapper beerMapper;
//
//    @Scheduled(fixedRate = 5000) // every 5 seconds
//    public void checkForLowInventory() {
//        List<Beer> beers = beerRepository.findAll();
//
//        beers.forEach(beer -> {
//            Integer inventoryQOH = beerInventoryService.getOnhandInventoryByUpc(beer.getUpc());
//        log.debug("min Onhand is : " + beer.getMinOnHand());
//            log.debug("Inventory is : " + inventoryQOH);
//
//        if(beer.getMinOnHand() >= inventoryQOH) {
//            jmsTemplate.convertAndSend(JmsConfig.BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
//        }
//        });
//
//    }
}
