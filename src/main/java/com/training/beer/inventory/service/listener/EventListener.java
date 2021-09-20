package com.training.beer.inventory.service.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.training.beer.inventory.service.domain.BeerInventory;
import com.training.beer.inventory.service.events.BeerDTO;
import com.training.beer.inventory.service.repositories.BeerInventoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class EventListener {

    private final BeerInventoryRepository inventoryRepository;
    private final ObjectMapper mapper;

    @JmsListener(destination = "beer-inventory-update-event")
    public void handleNewInventoryEvent(String event) throws JsonProcessingException {
        final BeerDTO beer = mapper.readValue(event, BeerDTO.class);
        log.info("Received inventory update for "+beer.getId());
        inventoryRepository.save(BeerInventory.builder()
                .beerId(beer.getId())
                .quantityOnHand(beer.getQuantityOnHand())
                .upc(beer.getUpc())
                .build());
    }
}
