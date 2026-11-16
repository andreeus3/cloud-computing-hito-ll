package com.ugr.muii.busservice.Entity;

import com.ugr.muii.busservice.Enums.BusStatus;
import jakarta.persistence.*;

@Entity
@Table(name="buses")
public class VehicleBus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String plate;
    private Integer capacity;
    private String model;

    @Enumerated(EnumType.STRING)
    private BusStatus status;

    public VehicleBus(Long id, String plate, Integer capacity, String model, BusStatus status) {
        this.id = id;
        this.plate = plate;
        this.capacity = capacity;
        this.model = model;
        this.status = status;
    }

    public VehicleBus() {

    }

    public BusStatus getStatus() {
        return status;
    }

    public void setStatus(BusStatus status) {
        this.status = status;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


