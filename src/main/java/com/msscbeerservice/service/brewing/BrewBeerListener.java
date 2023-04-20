package com.msscbeerservice.service.brewing;

import com.msscbeerservice.config.JmsConfig;
import com.msscbeerservice.domain.Beer;
import com.msscbeerservice.events.BrewBeerEvent;
import com.msscbeerservice.events.NewInventoryEvent;
import com.msscbeerservice.model.BeerDto;
import com.msscbeerservice.repository.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@ComponentScan
public class BrewBeerListener {

//    private final BeerRepository beerRepository;
//    private final JmsTemplate jmsTemplate;
//
//    @Transactional
//    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
//    public void listen(BrewBeerEvent brewBeerEvent) {
//        BeerDto beerDto = brewBeerEvent.getBeerDto();
//
//        Optional<Beer> beer = beerRepository.findById(beerDto.getId());
//        beerDto.setQuantityOnHand(beer.map(Beer::getQuantityToBrew).orElse(null));
//
//        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);
//
//        log.debug("Brewed beer " + beer.map(Beer::getMinOnHand) + " : QOH: " + beerDto.getQuantityOnHand());
//
//        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
//
//    }
}
