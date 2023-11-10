package unknown.backend.dev.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.dto.*;
import unknown.backend.dev.exception.*;
import unknown.backend.dev.repository.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService{

    private UserRepository userRepository;
    private final EntityManager em;
    @Autowired
    public UserService(UserRepository userRepository,EntityManager em) {
        this.userRepository = userRepository;
        this.em = em;
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

    @Transactional
    public User createUser(UserCreateDTO userCreateDTO) {
        // 이메일 중복 확인
        try{
            findByEmail(userCreateDTO.getEmail());
        }catch (UserNotFoundException e) {
            User newUser = User.builder()
                    .username(userCreateDTO.getUsername())
                    .password(userCreateDTO.getPassword())
                    .email(userCreateDTO.getEmail())
                    .build();
            saveUser(newUser);
            return newUser;
        }
        return null;
    }

    @Transactional
    public User updateUser(String email, UserUpdateDTO userUpdateDTO){
        User originalUser = findByEmail(email);
        User updatedUser = em.find(User.class,originalUser.getId());
        updatedUser.setUsername(userUpdateDTO.getName());
        updatedUser.setPassword(userUpdateDTO.getPassword());
        updatedUser.setEmail(userUpdateDTO.getEmail());
        return updatedUser;
    }

    @Transactional
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
}
