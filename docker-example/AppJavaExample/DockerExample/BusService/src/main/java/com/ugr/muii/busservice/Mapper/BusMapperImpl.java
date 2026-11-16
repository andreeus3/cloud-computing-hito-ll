package com.ugr.muii.busservice.Mapper;

import com.ugr.muii.busservice.Dto.BusDto;
import com.ugr.muii.busservice.Entity.VehicleBus;
import org.springframework.stereotype.Component;

@Component
public class BusMapperImpl implements BusMapper {

    public BusMapperImpl() {
    }

    @Override
    public BusDto toDTO(VehicleBus vehicleBus) {
        if(vehicleBus==null){
            return null;
        }

        BusDto busDto = new BusDto();
        busDto.setId(vehicleBus.getId());
        busDto.setPlate(vehicleBus.getPlate());
        busDto.setModel(vehicleBus.getModel());
        busDto.setCapacity(vehicleBus.getCapacity());
        busDto.setStatus(vehicleBus.getStatus());

        return busDto;

    }

    @Override
    public VehicleBus toEntity(BusDto busDTO) {
        if(busDTO==null){
            return null;
        }

        VehicleBus vehicleBus = new VehicleBus();
        vehicleBus.setId(busDTO.getId());
        vehicleBus.setPlate(busDTO.getPlate());
        vehicleBus.setModel(busDTO.getModel());
        vehicleBus.setCapacity(busDTO.getCapacity());
        vehicleBus.setStatus(busDTO.getStatus());

        return vehicleBus;
    }
}
