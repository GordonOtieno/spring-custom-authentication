package com.gordon.client.event;

import org.springframework.context.ApplicationEvent;

import com.gordon.client.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegistrationCompleteEvent extends ApplicationEvent {

	private final User user;
	private final String applicationUrl;

	public RegistrationCompleteEvent(User user, String applicationUrl) {
		super(user);
		this.user = user;
		this.applicationUrl = applicationUrl;
	}

}
