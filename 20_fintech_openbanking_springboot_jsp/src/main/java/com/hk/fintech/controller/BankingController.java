package com.hk.fintech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/banking")
public class BankingController {

	@GetMapping("/main")
	public String main() {
		return "main";
	}
}

