package com.demo.user.models;

import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "first_name", nullable = false, length = 30, unique = true)
	private String firstName;
	@Column(name = "sur_name", nullable = false, length = 30, unique = true)
	private String surName;
	@Column(name = "position", nullable = false, length = 30, unique = true)
	private String position;
	@Column(name = "github_profile_url", nullable = false, length = 30, unique = true)
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
