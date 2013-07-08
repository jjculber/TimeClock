package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.User;
import com.example.model.TimeClock;
import com.example.service.UserService;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Collections;
import java.util.Calendar;

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

    @RequestMapping("/{userId}/mobile")
    public String mobileStatus(@PathVariable("userId") Integer userId, Map<String, Object> map) {

	map.put("userId", userId);
        map.putAll(userStatus(userId));

        return "user";
    }

    @RequestMapping("/{userId}/clock")
    public String clockUser(@PathVariable("userId") Integer userId) {

        TimeClock clock = new TimeClock();

        // add service call to add new timeclock and associate it with this userid
        userService.clockTime(userId, clock);

        return "redirect:/users/" + userId + "/mobile";
    }
    
    @RequestMapping("/{userId}/status")
    public @ResponseBody Map<String, Object> userStatus(@PathVariable("userId") Integer userId) {

        Map<String, Object> json = new HashMap<String, Object>();
        // make service call to only get timestamps today
        TimeZone timezone = TimeZone.getTimeZone("America/Los_Angeles");
        Calendar cal = Calendar.getInstance(timezone);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        long timeMin = cal.getTimeInMillis();
        cal.add(Calendar.DATE, 1);
        long timeMax = cal.getTimeInMillis();

        List<TimeClock> times = userService.getUser(userId, timeMin, timeMax);
	Collections.sort(times);
	
    	boolean isClockedIn = times.size() % 2 == 0;
        int minutesWorked = minutesWorkedToday(times);
        long estTimeOff = estTimeOff(minutesWorked);

	json.put("times", times);
        json.put("status", isClockedIn ? "Clocked out" : "Clocked in");
        json.put("minutesWorkedToday", minutesWorked);
        json.put("estTimeOff", estTimeOff);

        return json;
    }

    private long estTimeOff(int worked) {
        int minsLeft = 480 - worked;

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, minsLeft);

        return cal.getTimeInMillis();
    }

    private int minutesWorkedToday(List<TimeClock> times) {
        int minutesWorked = 0;

        int i = 0;
        while (i < times.size()) {
            long in = times.get(i).getTime();
            i++;
            long out = (i == times.size() ? new TimeClock() : times.get(i)).getTime();
            i++;
            int minutes = (int)((out - in) / 60000);
            minutesWorked += minutes;
        }

        return minutesWorked;

    }
}
