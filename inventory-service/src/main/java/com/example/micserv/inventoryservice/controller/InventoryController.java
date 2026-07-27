package com.example.micserv.inventoryservice.controller;

import com.example.micserv.inventoryservice.response.EventInventoryResponse;
import com.example.micserv.inventoryservice.response.VenueInventoryResponse;
import com.example.micserv.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {

    private InventoryService inventoryService;

    @Autowired
    public InventoryController(final InventoryService inventoryService){
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory/events")
    public @ResponseBody List<EventInventoryResponse> inventoryGetAllEvents() {
        return inventoryService.getAllEvents();
    }

    @GetMapping("/inventory/venue/{venueId}")
    public @ResponseBody VenueInventoryResponse inventoryByVenueId(@PathVariable("venueId") Long venueId) {
        return inventoryService.getVenueInformation(venueId);
    }

    @GetMapping("/inventory/event/{eventId}")
    public @ResponseBody EventInventoryResponse inventoryGetAllEvents(@PathVariable("eventId") Long eventId) {
        return inventoryService.getEventInventory(eventId);
    }

    @PutMapping("/inventory/event/{eventId}/reserve/{ticketCount}")
    public ResponseEntity<Void> reserveCapacity(@PathVariable("eventId") Long eventId,
                                                @PathVariable("ticketCount") Long ticketCount) {
        boolean reserved = inventoryService.reserveCapacity(eventId, ticketCount);
        if (!reserved) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 = not enough seats
        }
        return ResponseEntity.ok().build();
    }
}
