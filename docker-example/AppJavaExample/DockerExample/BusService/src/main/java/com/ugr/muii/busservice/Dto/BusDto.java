package com.ugr.muii.busservice.Dto;

import com.ugr.muii.busservice.Enums.BusStatus;

public class BusDto {
    private Long id;

    private String plate;
    private String model;
    private Integer capacity;
    private BusStatus status;

    public BusDto(Long id, String plate, String model, Integer capacity, BusStatus status) {
        this.id = id;
        this.plate = plate;
        this.model = model;
        this.capacity = capacity;
        this.status = status;
    }

    public BusDto() {
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
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

    public BusStatus getStatus() {
        return status;
    }

    public void setStatus(BusStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
