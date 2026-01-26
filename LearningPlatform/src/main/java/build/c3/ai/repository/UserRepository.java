package build.c3.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import build.c3.ai.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);
}
