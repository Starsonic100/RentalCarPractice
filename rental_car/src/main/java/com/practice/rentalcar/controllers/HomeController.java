package com.practice.rentalcar.controllers;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.TimeZone;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"person","car","rental"})

public class HomeController {
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model) {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC));				
        return "home";
	}
}
