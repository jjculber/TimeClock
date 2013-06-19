package com.example.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String email;
    
    private String passHash;
    
    @OneToMany(fetch = FetchType.EAGER)
    private Set<TimeClock> times;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassHash() {
        return passHash;
    }

    public void setPassHash(String passHash) {
        this.passHash = passHash;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(Set<TimeClock> times) {
        this.times = times;
    }

    public boolean isWorking() {
        return times.size() % 2 == 0;
    }
    
    public int getMinutesWorked() {
        return 0;
    }
    
    public int getMinutesLeft() {
        return (8 * 60) - getHoursWorked();
    }
    
    public String getTimeOff() {
        TimeZone timezone = TimeZone.getTimeZone("America/Los_Angeles");
        SimpleDateFormat format = new SimpleDateFormat("h:mm a");
        format.setTimeZone(timezone);
        
        Calendar cal = Calendar.getInstance(timezone);
        cal.add(Calendar.MINUTE, getMinutesLeft());
        
        return format.format(cal.getTime());
    }
}
