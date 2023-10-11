package com.gordon.client.entity;

import java.util.Calendar;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class VerificationToken {
	// expiration time 10 minutes
	private static final int EXPIRATION_TIME = 10;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String token;
	private Date expirationTime;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_USER_VERIFY_TOKEN"))
	private User user;

	public VerificationToken(User user, String token) {
		super();
		this.token = token;
		this.user = user;
		this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);

	}

	public VerificationToken(String token) {
		super();
		this.token = token;
		this.expirationTime = calculateExpirationDate(EXPIRATION_TIME);
	}

	private Date calculateExpirationDate(int expirationTime) {
		Calendar calender = Calendar.getInstance();
		calender.setTimeInMillis(new Date().getTime());
		calender.add(Calendar.MINUTE, expirationTime);
		return new Date(calender.getTime().getTime());
	}
}