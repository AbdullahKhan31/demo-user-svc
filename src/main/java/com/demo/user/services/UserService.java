package com.demo.user.services;

import com.demo.user.dtos.User_WriteDTO;
import com.demo.user.models.User;
import com.demo.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	public User addUser(User_WriteDTO user_writeDTO) {
		User user = new User();
		user.setFirstName(user_writeDTO.getFirstName());
		user.setSurName(user_writeDTO.getSurName());
		user.setPosition(user_writeDTO.getPosition());
		user.setGithubProfileUrl(user_writeDTO.getGithubProfileUrl());
		return userRepository.save(user);
	}

	public User updateUser(User_WriteDTO user_writeDTO, Long id) {
		User existingUser = userRepository.findById(id).orElse(null);
		existingUser.setFirstName(user_writeDTO.getFirstName());
		existingUser.setSurName(user_writeDTO.getSurName());
		existingUser.setPosition(user_writeDTO.getPosition());
		existingUser.setGithubProfileUrl(user_writeDTO.getGithubProfileUrl());
		return userRepository.save(existingUser);
	}

	public String deleteUser(Long id) {
		userRepository.deleteById(id);
		return "User " + id + " deleted successfully!";
	}
}