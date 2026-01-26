package build.c3.ai.controller;

import org.springframework.web.bind.annotation.*;

import build.c3.ai.entity.User;
import build.c3.ai.repository.UserRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	private final UserRepository userRepository;

	public AdminController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
}
