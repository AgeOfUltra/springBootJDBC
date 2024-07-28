package com.example.springbootjdbc.controller;

import com.example.springbootjdbc.data.Holidays;
import com.example.springbootjdbc.repo.HolidayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {

    @Autowired
    private HolidayRepo repo;
    @Autowired
    private HolidayRepo holidayRepo;

    @RequestMapping("/holidays/{display}")
    public String holidays(@PathVariable String display, Model model) {
        if(display!=null && display.equals("all")){
            model.addAttribute("festival",true);
            model.addAttribute("federal",true);
        }
        else if(display!=null && display.equals("federal")){
            model.addAttribute("federal",true);
        } else if (display!=null && display.equals("festival")) {
            model.addAttribute("festival",true);
        }

        List<Holidays> holidaysList = holidayRepo.findAllHolidays();
        Holidays.Type[] types = Holidays.Type.values();
        for (Holidays.Type type : types) {
            model.addAttribute(type.toString(),
                    holidaysList.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList()));

        }
        return "holidays.html";
    }
}
