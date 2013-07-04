package com.example.service;


import java.util.List;

import com.example.model.User;

public interface UserService {
    
    public void addUser(User user);
    public List<User> listUsers();
    public void removeUser(Integer id);
}
