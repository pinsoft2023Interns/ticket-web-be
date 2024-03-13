package com.pinsoft.ticketwebbe.repository;

import com.pinsoft.ticketwebbe.entity.BusNavigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface BusNavigationRepository extends JpaRepository <BusNavigation, Long>{
    Optional<BusNavigation> findById(Long id);
}
