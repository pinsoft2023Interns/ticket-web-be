package com.pinsoft.ticketwebbe.repository;

import com.pinsoft.ticketwebbe.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface BusRepository extends JpaRepository<Bus, Long> {
    Optional<Bus> findById(Long id);
}
