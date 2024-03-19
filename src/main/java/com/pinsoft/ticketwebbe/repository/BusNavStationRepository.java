package com.pinsoft.ticketwebbe.repository;

import com.pinsoft.ticketwebbe.entity.BusNavStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BusNavStationRepository extends JpaRepository<BusNavStation, Long> {
    Optional<BusNavStation> findById(Long id);
}
