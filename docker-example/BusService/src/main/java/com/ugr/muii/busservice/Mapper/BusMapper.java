package com.ugr.muii.busservice.Mapper;

import com.ugr.muii.busservice.Dto.BusDto;
import com.ugr.muii.busservice.Entity.VehicleBus;
import org.apache.catalina.User;

public interface BusMapper {

    BusDto toDTO(VehicleBus vehicleBus);

    VehicleBus toEntity(BusDto busDTO);

}
