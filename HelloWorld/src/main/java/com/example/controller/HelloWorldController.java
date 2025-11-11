package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.producer.EventProducer;

@RestController
@RequestMapping(path = "/v1")
public class HelloWorldController {
	
	@Autowired
	EventProducer eventProducer;
	
	@GetMapping("/home")
	public ResponseEntity<String> home(){
		System.out.println("Home!");
		return ResponseEntity.ok("Home!");
		
	}
	@GetMapping("/hello")
	public ResponseEntity<String> hello(){
		eventProducer.sendKafkaMessage("Hello");
		return ResponseEntity.ok("Hello There!");
		
	}

}
