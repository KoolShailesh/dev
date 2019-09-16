package com.shailesh.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shailesh.service.ProKabaddiService;

import lombok.extern.slf4j.Slf4j;

@RestController("ProKabaddiController")
@RequestMapping("/")
@Slf4j
public class ProKabaddiController {
	@Autowired
	@Qualifier("proKabaddiService")
	ProKabaddiService proKabaddiService;

	@GetMapping("/welcome")
	public String welcome() {
		log.info("I am in Welcome");
		return proKabaddiService.greet();
	}

}
