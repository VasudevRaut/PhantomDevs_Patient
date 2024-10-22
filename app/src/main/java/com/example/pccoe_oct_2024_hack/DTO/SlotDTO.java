package com.example.pccoe_oct_2024_hack.DTO;

import java.io.Serializable;

public class SlotDTO implements Serializable {

    private String timeRange;
    private boolean isBooked;

    public SlotDTO(){}
    public SlotDTO(String timeRange, boolean isBooked) {
        this.timeRange = timeRange;
        this.isBooked = isBooked;
    }

    public String getTimeRange() {
        return timeRange;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}

