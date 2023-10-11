package com.gordon.client.event.listener;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.gordon.client.entity.User;
import com.gordon.client.event.RegistrationCompleteEvent;
import com.gordon.client.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j

public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
	@Autowired
	private UserService userService;

	@Override
	public void onApplicationEvent(RegistrationCompleteEvent event) {
		// Create the Verification Token for the User with Link
		User user = event.getUser();
		String token = UUID.randomUUID().toString();
		userService.saveVerificationTokenForUser(token, user);

		// send mail
		String url = event.getApplicationUrl() + "/verifyRegistration?token=" + token;

		// send VerificationEmail()
		log.info("Click the link to verify your account: {}", url);
	}

}
