package com.sqli.suisse.rest.controller;

import com.sqli.suisse.dto.UserCreateDTO;
import com.sqli.suisse.dto.UserUpdateDTO;
import com.sqli.suisse.dto.UserViewDTO;
import com.sqli.suisse.rest.shared.GenericResponse;
import com.sqli.suisse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 
 * @author ABDELWADOUD BOUDEFFAR
 * @since 1.0
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@GetMapping("v1/user/{id}")
	public ResponseEntity<UserViewDTO> getUserById(@PathVariable("id") Long id) {
		final UserViewDTO user = userService.getUserById(id);
		return ResponseEntity.ok(user);
	}

	@GetMapping("v1/user")
	public ResponseEntity<List<UserViewDTO>> getUsers() {
		final List<UserViewDTO> users = userService.getUsers();
		return ResponseEntity.ok(users);
	}

	@PostMapping("v1/user")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserCreateDTO userCreateDTO) {
		userService.createUser(userCreateDTO);
		return ResponseEntity.ok(new GenericResponse("User Created."));
	}

	@PutMapping("v1/user/{id}")
	public ResponseEntity<UserViewDTO> updateUser(@Valid @PathVariable("id") Long id,
			@RequestBody UserUpdateDTO userUpdateDTO) {
		final UserViewDTO user = userService.updateUser(id, userUpdateDTO);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("v1/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok(new GenericResponse("User Deleted !"));
	}

	// More performance
	@GetMapping("v1/user/slice")
	public ResponseEntity<List<UserViewDTO>> slice(Pageable pageable) {
		final List<UserViewDTO> users = userService.slice(pageable);
		return ResponseEntity.ok(users);
	}
	
}
