package br.com.billingreport.billingreport.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloWorldController {

	@CrossOrigin
	@GetMapping
	public String helloWorld() {
		return "Olá Mundo Doiso";
	}
	
	
}
