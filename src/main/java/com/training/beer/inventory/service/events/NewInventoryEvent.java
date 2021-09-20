package com.training.beer.inventory.service.events;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent{

    public NewInventoryEvent(BeerDTO beerDTO){
        super(beerDTO);
    }

}
