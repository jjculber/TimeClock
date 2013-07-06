package com.example.service;


import java.util.List;

import com.example.model.User;
import com.example.model.TimeClock;

public interface UserService {
    
    public void addUser(User user);
    public List<User> listUsers();
    public void removeUser(Integer id);
    public List<TimeClock> getUser(Integer id, long timeMin, long timeMax);
    public void clockTime(Integer id, TimeClock clock);
}
