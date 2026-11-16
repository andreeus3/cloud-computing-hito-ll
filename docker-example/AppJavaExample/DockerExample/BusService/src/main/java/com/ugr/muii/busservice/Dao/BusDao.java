package com.ugr.muii.busservice.Dao;

import com.ugr.muii.busservice.Entity.VehicleBus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusDao extends JpaRepository<VehicleBus, Long> {
    //TODO Optional<User> findByUsername(String username);
    //TODO boolean existsByUsername(String username);

}
