package unknown.backend.dev.repository;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import unknown.backend.dev.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByPhoneNumber(String phoneNumber);
    List<User> findAll();

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
