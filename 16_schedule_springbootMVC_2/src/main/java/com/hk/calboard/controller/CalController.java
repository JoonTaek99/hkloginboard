package com.hk.calboard.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hk.calboard.service.ICalService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/schedule")
public class CalController {
 
	@Autowired
	private ICalService calService;
	
	@GetMapping(value="/calendar")
	public String calendar(Model model, HttpServletRequest request) {
		
		Map<String, Integer>map=calService.makeCalendar(request);
		model.addAttribute("calMap", map);
		return "thymeleaf/calboard/calendar";
	}
	
}



