package com.ugr.muii.busservice.Service;

import com.ugr.muii.busservice.Dao.BusDao;
import com.ugr.muii.busservice.Dto.BusDto;
import com.ugr.muii.busservice.Entity.VehicleBus;
import com.ugr.muii.busservice.Enums.BusStatus;
import com.ugr.muii.busservice.Mapper.BusMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BusServiceImpTest {

    @Mock
    private BusDao busDao;

    @Mock
    private BusMapper busMapper;

    @InjectMocks
    private BusServiceImpl busService;

    @Test
    void findAll(){

        Long id = 1L;
        VehicleBus vehicleBus = new VehicleBus();

        vehicleBus.setId(id);
        vehicleBus.setCapacity(30);
        vehicleBus.setModel("2005");
        vehicleBus.setPlate("AVBC123");
        vehicleBus.setStatus(BusStatus.AVAILABLE);

        List<VehicleBus> vehicleBusList = List.of(vehicleBus);

        when(busDao.findAll()).thenReturn(vehicleBusList);

        List<BusDto> result = busService.findAll();
        assertNotNull(result);

        assertEquals(1, result.size());

        assertEquals("AVBC123", result.getFirst().getPlate());

        verify(busDao, times(1)).findAll();

    }

    @Test
    void findById() throws Exception{

        Long id = 1L;
        VehicleBus vehicleBus = new VehicleBus();

        vehicleBus.setId(id);
        vehicleBus.setCapacity(30);
        vehicleBus.setModel("2005");
        vehicleBus.setPlate("AVBC123");
        vehicleBus.setStatus(BusStatus.AVAILABLE);


        when(busDao.findById(id)).thenReturn(Optional.of(vehicleBus));

        Optional<BusDto> result = busService.findById(id);

        assertTrue(result.isPresent());

        assertEquals(id, result.get().getId());

        assertEquals("AVBC123", result.get().getPlate());
        assertEquals(30, result.get().getCapacity());

        verify(busDao, times(1)).findById(id);
    }

    @Test
    void addBus() throws Exception{

        Long id = 1L;
        VehicleBus vehicleBus = new VehicleBus();

        vehicleBus.setId(id);
        vehicleBus.setCapacity(30);
        vehicleBus.setModel("2005");
        vehicleBus.setPlate("AVBC123");
        vehicleBus.setStatus(BusStatus.AVAILABLE);

        BusDto response = new BusDto();

        response.setId(id);
        response.setCapacity(30);
        response.setModel("2005");
        response.setPlate("AVBC123");
        response.setStatus(BusStatus.AVAILABLE);


        when(busDao.save(any(VehicleBus.class))).thenAnswer(invocation -> invocation.getArgument(0));

        BusDto result = busService.saveBus(response);

        assertNotNull(result);

    }

    @Test
    void deleteBus() throws Exception{
        Long id = 99L;

        busService.deleteByID(id);

        verify(busDao, times(1)).deleteById(id);

    }

}
