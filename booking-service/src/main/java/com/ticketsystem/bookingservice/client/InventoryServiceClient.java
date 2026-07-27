package com.ticketsystem.bookingservice.client;

import com.ticketsystem.bookingservice.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class InventoryServiceClient {

    @Value("${inventory.service.url}")
    private String inventoryServiceUrl;

    public InventoryResponse getInventory(final Long eventId){
        final RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(inventoryServiceUrl + "/event/" + eventId, InventoryResponse.class);
    }

    public boolean reserveCapacity(final Long eventId, final Long ticketCount) {
        final RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.put(inventoryServiceUrl + "/event/" + eventId + "/reserve/" + ticketCount, null);
            return true;
        } catch (HttpClientErrorException.Conflict e) {
            return false; // inventory service returned 409 — not enough seats
        }
    }
}
