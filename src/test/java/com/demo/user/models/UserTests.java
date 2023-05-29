package com.demo.user.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserTests {

	@Test
	void testConstructorAndGetters() {
		Long id = 1L;
		String firstName = "John";
		String surName = "Doe";
		String position = "Developer";
		String githubProfileUrl = "https://github.com/johndoe";

		User user = new User(id, firstName, surName, position, githubProfileUrl);

		assertEquals(id, user.getId());
		assertEquals(firstName, user.getFirstName());
		assertEquals(surName, user.getSurName());
		assertEquals(position, user.getPosition());
		assertEquals(githubProfileUrl, user.getGithubProfileUrl());
	}

	@Test
	void testDefaultConstructor() {
		User user = new User();

		assertNull(user.getId());
		assertNull(user.getFirstName());
		assertNull(user.getSurName());
		assertNull(user.getPosition());
		assertNull(user.getGithubProfileUrl());
	}

	@Test
	void testSetters() {
		User user = new User();

		Long id = 1L;
		String firstName = "John";
		String surName = "Doe";
		String position = "Developer";
		String githubProfileUrl = "https://github.com/johndoe";

		user.setId(id);
		user.setFirstName(firstName);
		user.setSurName(surName);
		user.setPosition(position);
		user.setGithubProfileUrl(githubProfileUrl);

		assertEquals(id, user.getId());
		assertEquals(firstName, user.getFirstName());
		assertEquals(surName, user.getSurName());
		assertEquals(position, user.getPosition());
		assertEquals(githubProfileUrl, user.getGithubProfileUrl());
	}

}
