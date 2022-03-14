package com.enisfr.tokenbucket.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

	@GetMapping("/")
	public ResponseEntity<String> root(){
		return ResponseEntity.ok().body("OK");
	}
}
