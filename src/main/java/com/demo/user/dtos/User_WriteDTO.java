package com.demo.user.dtos;

import javax.persistence.Column;

public class User_WriteDTO {
	private String firstName;
	private String surName;
	private String position;
	private String githubProfileUrl;

	public User_WriteDTO() {
	}

	public User_WriteDTO(String firstName, String surName, String position, String githubProfileUrl) {
		this.firstName = firstName;
		this.surName = surName;
		this.position = position;
		this.githubProfileUrl = githubProfileUrl;
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