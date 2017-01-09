package com.shailesh.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shailesh.service.ProKabaddiService;

@RestController
@RequestMapping("/")
public class ProKabaddiController {
	@Autowired
	ProKabaddiService proKabaddiService;

	@RequestMapping("welcome")
	public String welcome() {
		return proKabaddiService.greet();
	}

}
