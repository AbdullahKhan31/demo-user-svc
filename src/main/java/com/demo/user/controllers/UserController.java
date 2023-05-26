package com.demo.user.controllers;

import com.demo.user.dtos.User_ReadDTO;
import com.demo.user.dtos.User_WriteDTO;
import com.demo.user.models.User;
import com.demo.user.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ModelMapper modelMapper;

	@GetMapping("/")
	public List<User_ReadDTO> getUsers(){
		return userService.getUsers().stream()
				.map(n -> convertToReadDto(n))
				.collect(Collectors.toList());
	}

	@GetMapping("/{id}")
	public User_ReadDTO getUserById(@PathVariable Long id){
		return convertToReadDto(userService.getUserById(id));
	}

	@PostMapping("/")
	public User_ReadDTO addUser(@RequestBody User_WriteDTO userDTO){
		return convertToReadDto(userService.addUser(userDTO));
	}

	@PutMapping("/{id}")
	public User_ReadDTO updateUser(@RequestBody User_WriteDTO customer, @PathVariable Long id){
		return convertToReadDto(userService.updateUser(customer, id));
	}

	@DeleteMapping("/{id}")
	public String deleteUser(@PathVariable Long id){
		return userService.deleteUser(id);
	}

	private User_ReadDTO convertToReadDto(User user) {
		User_ReadDTO userDto = modelMapper.map(user, User_ReadDTO.class);
		return userDto;
	}
}
