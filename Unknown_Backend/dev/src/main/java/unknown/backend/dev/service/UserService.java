package unknown.backend.dev.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.dto.*;
import unknown.backend.dev.exception.*;
import unknown.backend.dev.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class UserService{

    private UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final EntityManager em;
    @Autowired
    public UserService(UserRepository userRepository,EntityManager em, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.em = em;
        this.encoder = encoder;
    }

    // 유저 전체 조회
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(User::isActive)
                .collect(Collectors.toList());
    }


    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        if(!user.isActive()){
            throw new NotAllowedAccessException();
        }
        return user;
    }


    public void registerUser(RegisterDTO registerDTO) {
        // 이메일 중복 확인
        userRepository.save(registerDTO.toEntity(encoder.encode(registerDTO.getPassword())));
    }

    public User updateUser(String email, UserUpdateDTO userUpdateDTO){
        User originalUser = findByEmail(email);
        User updatedUser = em.find(User.class,originalUser.getId());
        updatedUser.setUsername(userUpdateDTO.getName());
        updatedUser.setPassword(userUpdateDTO.getPassword());
        updatedUser.setEmail(userUpdateDTO.getEmail());
        return updatedUser;
    }

    public User reportUser(String email){
        log.info("신고자: " + email);
        User user = findByEmail(email);
        User reportedUser = em.find(User.class,user.getId());
        reportedUser.setReportCount(reportedUser.getReportCount() + 1);
        return reportedUser;
    }

    public User deleteUser(String email){
        User user = findByEmail(email);
        user.setActive(false);
        user.preRemove();
        saveUser(user);
        return user;
    }

    public boolean checkEmailDuplicate(String email) {
        return userRepository.existsUserByEmail(email);
    }

    public User login(LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequest.getLoginEmail());
        if(optionalUser.isEmpty()) {
            return null;
        }
        User user = optionalUser.get();
        if(!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return null;
        }
        return user;
    }
    public User getLoginUserById(Long userId) {
        if(userId == null) return null;

        Optional<User> optionalUser = userRepository.findById(userId);
        return optionalUser.orElse(null);

    }
    public User getLoginUserByEmail(String Email) {
        if(Email == null) return null;
        System.out.println("Email = " + Email);
        return findByEmail(Email);

    }
}
