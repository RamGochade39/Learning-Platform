package build.c3.ai.services;

import org.springframework.stereotype.Service;

import build.c3.ai.entity.User;
import build.c3.ai.repository.UserRepository;

@Service
public class AdminService {

	private final UserRepository userRepository;

	public AdminService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User createUser(User admin, User newUser) {

		if (!admin.getRole().equals("ADMIN")) {
			throw new RuntimeException("Access denied");
		}

		return userRepository.save(newUser);
	}
}
