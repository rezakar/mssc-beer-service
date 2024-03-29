package com.msscbeerservice.service.inventory;

import java.util.UUID;

public interface BeerInventoryService {
    Integer getOnhandInventory(UUID beerId);

    Integer getOnhandInventoryByUpc(String upc);

}
