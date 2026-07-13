package com.example.micserv.inventoryservice.repository;

import com.example.micserv.inventoryservice.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
