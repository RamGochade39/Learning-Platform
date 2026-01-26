package build.c3.ai.controller;

import org.springframework.web.bind.annotation.*;

import build.c3.ai.entity.User;
import build.c3.ai.jwtutil.JwtUtil;
import build.c3.ai.services.AuthService;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

	private final AuthService authService;
	private final JwtUtil jwtUtil;

	public AuthController(AuthService authService, JwtUtil jwtUtil) {
		this.authService = authService;
		this.jwtUtil = jwtUtil;
	}

	@PostMapping("/login")
	public Map<String, String> login(@RequestBody Map<String, String> request) {

		User user = authService.login(request.get("username"), request.get("password"));

		String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

		return Map.of("token", token, "role", user.getRole());
	}
}
