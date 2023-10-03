package unknown.backend.dev.user.repository;

import unknown.backend.dev.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByEmailAndDeletedAtIsNull(String email);
    Optional<User> findByName(String name);
    boolean existsByEmail(String email);
}
