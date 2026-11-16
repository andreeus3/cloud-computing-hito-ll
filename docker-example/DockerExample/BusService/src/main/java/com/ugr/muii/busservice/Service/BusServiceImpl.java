package com.ugr.muii.busservice.Service;

import com.ugr.muii.busservice.Dao.BusDao;
import com.ugr.muii.busservice.Dto.BusDto;
import com.ugr.muii.busservice.Entity.VehicleBus;
import com.ugr.muii.busservice.Mapper.BusMapperImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusServiceImpl implements BusService{

    private final BusDao busDao;
    private final BusMapperImpl busMapperImpl;

    public BusServiceImpl(BusDao busDao, BusMapperImpl busMapperImpl) {
        this.busDao = busDao;
        this.busMapperImpl = busMapperImpl;
    }

    @Override
    public List<BusDto> findAll() {
        List<VehicleBus> vehicleBuses = busDao.findAll();
        List<BusDto> busDto = new ArrayList<>();

        for(VehicleBus vehicleBus : vehicleBuses){
            BusDto dto = new BusDto();
            dto.setId(vehicleBus.getId());
            dto.setCapacity(vehicleBus.getCapacity());
            dto.setModel(vehicleBus.getModel());
            dto.setPlate(vehicleBus.getPlate());
            dto.setStatus(vehicleBus.getStatus());

            busDto.add(dto);
        }
        return busDto;
    }

    @Override
    public Optional<BusDto> findById(Long id){
        Optional<VehicleBus> busOptional = busDao.findById(id);

        if(busOptional.isEmpty()){
            return Optional.empty();
        }

        BusDto busDto = new BusDto();
        VehicleBus vehicleBus = busOptional.get();

        busDto.setId(vehicleBus.getId());
        busDto.setCapacity(vehicleBus.getCapacity());
        busDto.setModel(vehicleBus.getModel());
        busDto.setPlate(vehicleBus.getPlate());
        busDto.setStatus(vehicleBus.getStatus());


        return Optional.of(busDto);
    }

    @Override
    public BusDto saveBus(BusDto busDto) {
        VehicleBus vehicleBus = new VehicleBus();

        vehicleBus.setId(busDto.getId());
        vehicleBus.setCapacity(busDto.getCapacity());
        vehicleBus.setModel(busDto.getModel());
        vehicleBus.setPlate(busDto.getPlate());
        vehicleBus.setStatus(busDto.getStatus());

        VehicleBus vehicleBusSaved = busDao.save(vehicleBus);

        BusDto response = new BusDto();

        response.setId(vehicleBusSaved.getId());
        response.setCapacity(vehicleBusSaved.getCapacity());
        response.setModel(vehicleBusSaved.getModel());
        response.setPlate(vehicleBusSaved.getPlate());
        response.setStatus(vehicleBusSaved.getStatus());

        return response;
    }

    @Override
    public List<BusDto> saveAllBuses(List<BusDto> buses) {
        List<VehicleBus> vehicleBuses = buses.stream()
                .map(busMapperImpl::toEntity)
                .toList();

        List<VehicleBus> saveAllBuses = busDao.saveAll(vehicleBuses);


        return saveAllBuses.stream()
                .map(busMapperImpl::toDTO)
                .toList();
    }

    @Override
    public void deleteByID(Long id) {
        busDao.deleteById(id);
    }

    @Override
    public BusDto updateBus(BusDto busDto, Long id) {
        VehicleBus existingVehicleBus = busDao.findById(id)
                .orElseThrow(()
                        ->new RuntimeException("User not found"));

        if (busDto.getCapacity() != null) {
            existingVehicleBus.setCapacity(busDto.getCapacity());
        }

        if (busDto.getModel() != null) {
            existingVehicleBus.setModel(busDto.getModel());
        }

        if (busDto.getPlate() != null) {
            existingVehicleBus.setPlate(busDto.getPlate());
        }

        if (busDto.getStatus() != null) {
            existingVehicleBus.setStatus(busDto.getStatus());
        }

        VehicleBus vehicleBusSaved = busDao.save(existingVehicleBus);


        BusDto busDtoUpdated = new BusDto();

        busDtoUpdated.setId(vehicleBusSaved.getId());
        busDtoUpdated.setCapacity(vehicleBusSaved.getCapacity());
        busDtoUpdated.setModel(vehicleBusSaved.getModel());
        busDtoUpdated.setPlate(vehicleBusSaved.getPlate());
        busDtoUpdated.setStatus(vehicleBusSaved.getStatus());

        return busDtoUpdated;
    }


}
