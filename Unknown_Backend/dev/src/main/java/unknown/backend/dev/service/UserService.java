package unknown.backend.dev.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.exception.UserNotFoundException;
import unknown.backend.dev.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@NoArgsConstructor
public class UserService{
    private UserRepository userRepository;

    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(user -> user.isActive())
                .collect(Collectors.toList());
    }

    public User findByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));
    }

    public User findByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));
    }

    public User findByPhoneNumber(String phoneNumber) {
        return userRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));
    }
}
