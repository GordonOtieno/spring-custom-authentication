package com.gordon.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gordon.client.entity.User;
import com.gordon.client.event.RegistrationCompleteEvent;
import com.gordon.client.model.UserModel;
import com.gordon.client.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class RegistrationController {
	@Autowired
	private UserService userService;
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping("/hello")
	public String welcome() {
		return "Hello this is welcome page";
	}

	@PostMapping("/register")
	public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
		User user = userService.registerUser(userModel);
		publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
		return "user registered successfully";
	}

	private String applicationUrl(HttpServletRequest request) {
		return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	}

}
