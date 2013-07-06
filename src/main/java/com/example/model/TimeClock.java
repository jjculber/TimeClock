package com.example.model;

import java.lang.Comparable;

public class TimeClock implements Comparable<TimeClock> {

    private Integer id;

    private long time;
    
    public TimeClock() {
        time = System.currentTimeMillis();
    }

    public TimeClock(long nTime) {
        time = nTime;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int compareTo(TimeClock other) {
        return (int) (this.getTime() - other.getTime());
    }

}
