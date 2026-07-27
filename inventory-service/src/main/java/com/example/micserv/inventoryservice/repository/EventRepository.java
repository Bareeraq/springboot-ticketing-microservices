package com.example.micserv.inventoryservice.repository;

import com.example.micserv.inventoryservice.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Modifying
    @Query("UPDATE Event e SET e.left_capacity = e.left_capacity - :count " +
            "WHERE e.id = :eventId AND e.left_capacity >= :count")
    int reserveCapacity(@Param("eventId") Long eventId, @Param("count") Long count);
}
