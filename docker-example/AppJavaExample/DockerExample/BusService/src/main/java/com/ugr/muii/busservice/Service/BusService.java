package com.ugr.muii.busservice.Service;

import com.ugr.muii.busservice.Dto.BusDto;

import java.util.List;
import java.util.Optional;

public interface BusService {
    List<BusDto> findAll();
    Optional<BusDto> findById(Long id);
    BusDto saveBus(BusDto busDto);
    void deleteByID(Long id);
    List<BusDto> saveAllBuses(List<BusDto> buses);
    //TODO Optional<User> findByUsername(String username);
    //TODO boolean existsByUsername(String username);
    BusDto updateBus(BusDto busDto, Long id);

}
