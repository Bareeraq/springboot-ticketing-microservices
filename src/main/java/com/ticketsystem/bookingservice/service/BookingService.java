package com.ticketsystem.bookingservice.service;

import com.ticketsystem.bookingservice.client.InventoryServiceClient;
import com.ticketsystem.bookingservice.entity.Customer;
import com.ticketsystem.bookingservice.repository.CustomerRepository;
import com.ticketsystem.bookingservice.request.BookingRequest;
import com.ticketsystem.bookingservice.response.BookingResponse;
import com.ticketsystem.bookingservice.response.InventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private final CustomerRepository customerRepository;
    private final InventoryServiceClient inventoryServiceClient;

    @Autowired
    public BookingService(final CustomerRepository customerRepository,
                          final InventoryServiceClient inventoryServiceClient) {
        this.customerRepository = customerRepository;
        this.inventoryServiceClient = inventoryServiceClient;
    }

    public BookingResponse createBooking(final BookingRequest request){
        //check if user exists
        final Customer customer = customerRepository.findById(request.getUserId()).orElse(null);
        if(customer == null){
            throw new RuntimeException("User not found");
        }
        //check if there's enough inventory
        final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(request.getEventId());
        System.out.println("Inventory Service Response" + inventoryResponse);

        //get event & venue info

        //create booking
        //send booking to Order service on a Kafka topic
        return BookingResponse.builder().build();
    }
}
