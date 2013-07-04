package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.model.User;
import com.example.service.UserService;

import java.util.Map;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String listUsers(Map<String, Object> map) {

        map.put("user", new User());
        map.put("usersList", userService.listUsers());

        return "users";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, BindingResult result) {

        userService.addUser(user);

        return "redirect:/users/";
    }

    @RequestMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {

        userService.removeUser(userId);

        return "redirect:/users/";
    }
    
    @RequestMapping("/{userId}/clock")
    public String clockUser(@PathVariable("userId") Integer userId) {

        TimeClock clock = new TimeClock();

	// add service call to add new timeclock and associate it with this userid
	userService.clockTime(userId, clock);

        return "redirect:/users/";
    }
    
    @RequestMapping("/{userId}/status")
    public String userStatus(@PathVariable("userId") Integer userId) {

	// make service call to only get timestamps today
        User user = userService.getUser(userId);

        return "redirect:/users/";
    }
}
