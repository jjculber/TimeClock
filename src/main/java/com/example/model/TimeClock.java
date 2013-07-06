package com.example.model;

public class TimeClock {

    private Integer id;

    private long time;

    public TimeClock() {
        time = System.currentTimeMillis();
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

}
