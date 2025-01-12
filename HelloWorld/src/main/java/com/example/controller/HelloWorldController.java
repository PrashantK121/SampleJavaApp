package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1")
public class HelloWorldController {
	
	@GetMapping("")
	public ResponseEntity<String> home(){
		return ResponseEntity.ok("Home!");
		
	}
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		return ResponseEntity.ok("Hello There!");
		
	}

}
