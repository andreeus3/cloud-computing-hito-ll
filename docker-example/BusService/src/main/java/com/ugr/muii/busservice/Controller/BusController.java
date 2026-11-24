package com.ugr.muii.busservice.Controller;

import com.ugr.muii.busservice.Dto.BusDto;
import com.ugr.muii.busservice.Service.BusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bus")
public class BusController {

    private final BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @GetMapping("/all-buses")
    public ResponseEntity<List<BusDto>> findAll(){
        return ResponseEntity.ok(busService.findAll());
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<BusDto> findById(@PathVariable("id") Long id){
        Optional<BusDto> userDTOOptional = busService.findById(id);
        return userDTOOptional.map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity
                        .notFound()
                        .build());
    }

    @PatchMapping("update-bus/{id}")
    public ResponseEntity<BusDto> updateUser(
            @PathVariable Long id,
            @RequestBody BusDto busDto){

        BusDto updatedUser = busService.updateBus(busDto, id);

        return ResponseEntity.ok(updatedUser);
    }

    @PostMapping({"add-bus"})
    public ResponseEntity<BusDto> newBusDto(@RequestBody BusDto createBusDto){
        BusDto busDto = busService.saveBus(createBusDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(busDto);
    }

    @PostMapping("/add-many-buses")
    public ResponseEntity<List<BusDto>> saveAllBuses(
            @RequestBody List<BusDto> buses) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(busService.saveAllBuses(buses));
    }

    @DeleteMapping("delete-bus/{id}")
    public ResponseEntity<Void> deleteById (@PathVariable("id") Long id){
        busService.deleteByID(id);
        return ResponseEntity.noContent().build();
    }

}
