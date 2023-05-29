package com.demo.user.services;

import com.demo.user.dtos.User_WriteDTO;
import com.demo.user.models.User;
import com.demo.user.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTests {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetUsers() {
		User user1 = new User(1L, "John", "Doe", "Developer", "https://github.com/johndoe");
		User user2 = new User(2L, "Jane", "Smith", "Tester", "https://github.com/janesmith");
		List<User> users = new ArrayList<>();
		users.add(user1);
		users.add(user2);

		when(userRepository.findAll()).thenReturn(users);

		List<User> result = userService.getUsers();

		verify(userRepository, times(1)).findAll();
		assertEquals(2, result.size());
		assertEquals("John", result.get(0).getFirstName());
		assertEquals("Jane", result.get(1).getFirstName());
	}

	@Test
	public void testGetUserById() {
		User user = new User(1L, "John", "Doe", "Developer", "https://github.com/johndoe");

		when(userRepository.findById(1L)).thenReturn(Optional.of(user));

		User result = userService.getUserById(1L);

		verify(userRepository, times(1)).findById(1L);
		assertEquals("John", result.getFirstName());
		assertEquals("Doe", result.getSurName());
	}

	@Test
	public void testAddUser() {
		User_WriteDTO user_writeDTO = new User_WriteDTO();
		user_writeDTO.setFirstName("John");
		user_writeDTO.setSurName("Doe");
		user_writeDTO.setPosition("Developer");
		user_writeDTO.setGithubProfileUrl("https://github.com/johndoe");

		when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
			User savedUser = invocation.getArgument(0);
			savedUser.setId(1L);
			return savedUser;
		});

		User result = userService.addUser(user_writeDTO);

		verify(userRepository, times(1)).save(any(User.class));
		assertEquals(1L, result.getId());
		assertEquals("John", result.getFirstName());
		assertEquals("Doe", result.getSurName());
	}

	@Test
	public void testUpdateUser() {
		User_WriteDTO user_writeDTO = new User_WriteDTO();
		user_writeDTO.setFirstName("John");
		user_writeDTO.setSurName("Doe");
		user_writeDTO.setPosition("Developer");
		user_writeDTO.setGithubProfileUrl("https://github.com/johndoe");

		User existingUser = new User();
		existingUser.setId(1L);
		existingUser.setFirstName("Jane");
		existingUser.setSurName("Smith");
		existingUser.setPosition("Tester");
		existingUser.setGithubProfileUrl("https://github.com/janesmith");

		when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
		when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

		User result = userService.updateUser(user_writeDTO, 1L);

		verify(userRepository, times(1)).findById(1L);
		verify(userRepository, times(1)).save(any(User.class));
		assertEquals(1L, result.getId());
		assertEquals("John", result.getFirstName());
		assertEquals("Doe", result.getSurName());
	}

	@Test
	public void testDeleteUser() {
		String result = userService.deleteUser(1L);

		verify(userRepository, times(1)).deleteById(1L);
		assertEquals("User 1 deleted successfully!", result);
	}
}






