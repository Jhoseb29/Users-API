package com.sabrositoz.usersapi.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

	@Id
	private String id;

	@Column
	private String name;

	@Column
	private String login;

	@Column
	private String password;

	public User() {
		id = UUID.randomUUID().toString();
	}
}
