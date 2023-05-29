package com.demo.user.controllers;

import com.demo.user.dtos.GithubRepository_ReadDTO;
import com.demo.user.dtos.User_ReadDTO;
import com.demo.user.dtos.User_WriteDTO;
import com.demo.user.integrations.GithubClient;
import com.demo.user.models.User;
import com.demo.user.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class UserControllerTests {
	private MockMvc mockMvc;

	@Mock
	private UserService userService;

	@Mock
	private GithubClient githubClient;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserController userController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testGetUsers() throws Exception {
		User user1 = new User(1L, "John", "Doe", "Developer", "https://github.com/johndoe");
		User user2 = new User(2L, "Jane", "Smith", "Tester", "https://github.com/janesmith");
		List<User> userList = Arrays.asList(user1, user2);

		when(userService.getUsers()).thenReturn(userList);

		mockMvc.perform(get("/users/"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(userList.size()))
				.andExpect(jsonPath("$[0].id").value(user1.getId()))
				.andExpect(jsonPath("$[0].firstName").value(user1.getFirstName()))
				.andExpect(jsonPath("$[0].surName").value(user1.getSurName()))
				.andExpect(jsonPath("$[0].position").value(user1.getPosition()))
				.andExpect(jsonPath("$[0].githubProfileUrl").value(user1.getGithubProfileUrl()))
				.andExpect(jsonPath("$[1].id").value(user2.getId()))
				.andExpect(jsonPath("$[1].firstName").value(user2.getFirstName()))
				.andExpect(jsonPath("$[1].surName").value(user2.getSurName()))
				.andExpect(jsonPath("$[1].position").value(user2.getPosition()))
				.andExpect(jsonPath("$[1].githubProfileUrl").value(user2.getGithubProfileUrl()));

		verify(userService, times(1)).getUsers();
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testGetUserById() throws Exception {
		User user = new User(1L, "John", "Doe", "Developer", "https://github.com/johndoe");

		when(userService.getUserById(anyLong())).thenReturn(user);

		mockMvc.perform(get("/users/{id}", 1L))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(user.getId()))
				.andExpect(jsonPath("$.firstName").value(user.getFirstName()))
				.andExpect(jsonPath("$.surName").value(user.getSurName()))
				.andExpect(jsonPath("$.position").value(user.getPosition()))
				.andExpect(jsonPath("$.githubProfileUrl").value(user.getGithubProfileUrl()));

		verify(userService, times(1)).getUserById(anyLong());
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testAddUser() throws Exception {
		User_WriteDTO userDTO = new User_WriteDTO("John", "Doe", "Developer", "https://github.com/johndoe");
		User user = new User(1L, userDTO.getFirstName(), userDTO.getSurName(), userDTO.getPosition(), userDTO.getGithubProfileUrl());

		when(userService.addUser(any(User_WriteDTO.class))).thenReturn(user);

		mockMvc.perform(post("/users/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"firstName\":\"John\",\"surName\":\"Doe\",\"position\":\"Developer\",\"githubProfileUrl\":\"https://github.com/johndoe\"}"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(user.getId()))
				.andExpect(jsonPath("$.firstName").value(user.getFirstName()))
				.andExpect(jsonPath("$.surName").value(user.getSurName()))
				.andExpect(jsonPath("$.position").value(user.getPosition()))
				.andExpect(jsonPath("$.githubProfileUrl").value(user.getGithubProfileUrl()));

		verify(userService, times(1)).addUser(any(User_WriteDTO.class));
		verifyNoMoreInteractions(userService);
	}

	@Test
	public void testUpdateUser() throws Exception {
		User_WriteDTO userDTO = new User_WriteDTO();
		userDTO.setFirstName("John");
		userDTO.setSurName("Doe");
		userDTO.setPosition("Developer");
		userDTO.setGithubProfileUrl("https://github.com/johndoe");

		User updatedUser = new User();
		updatedUser.setId(1L);
		updatedUser.setFirstName("John");
		updatedUser.setSurName("Doe");
		updatedUser.setPosition("Developer");
		updatedUser.setGithubProfileUrl("https://github.com/johndoe");

		User_ReadDTO expectedResponse = new User_ReadDTO();
		expectedResponse.setId(1L);
		expectedResponse.setFirstName("John");
		expectedResponse.setSurName("Doe");
		expectedResponse.setPosition("Developer");
		expectedResponse.setGithubProfileUrl("https://github.com/johndoe");

		when(userService.updateUser(any(User_WriteDTO.class), anyLong())).thenReturn(updatedUser);
		when(modelMapper.map(any(User.class), eq(User_ReadDTO.class))).thenReturn(expectedResponse);

		mockMvc.perform(put("/users/1")
				.contentType("application/json")
				.content("{\"firstName\":\"John\",\"surName\":\"Doe\",\"position\":\"Developer\",\"githubProfileUrl\":\"https://github.com/johndoe\"}"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.firstName").value("John"))
				.andExpect(jsonPath("$.surName").value("Doe"))
				.andExpect(jsonPath("$.position").value("Developer"))
				.andExpect(jsonPath("$.githubProfileUrl").value("https://github.com/johndoe"));

		verify(userService, times(1)).updateUser(any(User_WriteDTO.class), anyLong());
		verify(modelMapper, times(1)).map(any(User.class), eq(User_ReadDTO.class));
	}

	@Test
	public void testDeleteUser() throws Exception {
		when(userService.deleteUser(1L)).thenReturn("User 1 deleted successfully!");

		mockMvc.perform(delete("/users/1"))
				.andExpect(status().isOk())
				.andExpect(content().string("User 1 deleted successfully!"));

		verify(userService, times(1)).deleteUser(anyLong());
	}

	@Test
	public void testGetGithubRepositoriesById() throws Exception {
		User user = new User(1L, "John", "Doe", "Developer", "https://github.com/johndoe");
		List<GithubRepository_ReadDTO> repositories = Arrays.asList(
				new GithubRepository_ReadDTO("repo1", "https://github.com/johndoe/repo1", "JAVA"),
				new GithubRepository_ReadDTO("repo2", "https://github.com/johndoe/repo2", "JAVA")
		);

		when(userService.getUserById(anyLong())).thenReturn(user);
		when(githubClient.getUserRepositories(anyString())).thenReturn(repositories);

		mockMvc.perform(get("/users/{id}/repositories", 1L))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.length()").value(repositories.size()))
				.andExpect(jsonPath("$[0].name").value(repositories.get(0).getName()))
				.andExpect(jsonPath("$[0].url").value(repositories.get(0).getUrl()))
				.andExpect(jsonPath("$[1].name").value(repositories.get(1).getName()))
				.andExpect(jsonPath("$[1].url").value(repositories.get(1).getUrl()));

		verify(userService, times(1)).getUserById(anyLong());
		verify(githubClient, times(1)).getUserRepositories(anyString());
		verifyNoMoreInteractions(userService, githubClient);
	}

}

