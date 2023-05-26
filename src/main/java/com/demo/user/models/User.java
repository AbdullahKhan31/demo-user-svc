package com.demo.user.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false, length = 30)
	private String firstName;
	@Column(length = 30)
	private String surName;
	@Column(length = 30)
	private String position;
	@Column(length = 30)
	private String githubProfileUrl;

	public User() {
	}

	public User(Long id, String firstName, String surName, String position, String githubProfileUrl) {
		this.id = id;
		this.firstName = firstName;
		this.surName = surName;
		this.position = position;
		this.githubProfileUrl = githubProfileUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return this.surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getGithubProfileUrl() {
		return this.githubProfileUrl;
	}

	public void setGithubProfileUrl(String githubProfileUrl) {
		this.githubProfileUrl = githubProfileUrl;
	}

}
