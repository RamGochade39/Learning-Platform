package build.c3.ai.authservice;

import org.springframework.stereotype.Service;

import build.c3.ai.entity.User;
import build.c3.ai.repository.UserRepository;

@Service
public class AuthService {

	private final UserRepository userRepository;

	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User login(String username, String password) {
		User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));

		if (!user.isActive()) {
			throw new RuntimeException("User is inactive");
		}

		if (!user.getPassword().equals(password)) {
			throw new RuntimeException("Invalid password");
		}

		return user;
	}
}
