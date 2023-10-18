package unknown.backend.dev.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unknown.backend.dev.domain.Report;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.dto.ReportDTO;
import unknown.backend.dev.dto.UserDTO;
import unknown.backend.dev.exception.UserNotFoundException;
import unknown.backend.dev.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService{
    @Autowired
    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 유저 전체 조회
    public List<User> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .filter(user -> user.isActive())
                .collect(Collectors.toList());
    }

    public User findByMethodAndValue(String method, String value) {
        switch (method) {
            case "name":
                return userRepository.findByUsername(value).orElseThrow(() ->
                        new UserNotFoundException("해당 유저가 존재하지 않습니다."));
            case "email":
                return userRepository.findByEmail(value).orElseThrow(() ->
                        new UserNotFoundException("해당 유저가 존재하지 않습니다."));
            case "phoneNumber":
                return userRepository.findByPhoneNumber(value).orElseThrow(() ->
                        new UserNotFoundException("해당 유저가 존재하지 않습니다."));
            default:
                throw new UserNotFoundException("해당 유저가 존재하지 않습니다.");
        }

    }

    public boolean isExist(String method, String value) {
        switch (method) {
            case "name":
                return userRepository.existsByUsername(value);
            case "email":
                return userRepository.existsByEmail(value);
            case "phoneNumber":
                return userRepository.existsByPhoneNumber(value);
            default:
                throw new UserNotFoundException("해당 유저가 존재하지 않습니다.");
        }
    }
    public User saveUser(User user) {
        userRepository.save(user);
        return user;
    }
    @Transactional
    public User createUser(UserDTO userDTO) {
        if (isExist("name", userDTO.getUsername())) {
            throw new UserNotFoundException("이미 존재하는 유저입니다.");
        }
        return saveUser(User.toEntity(userDTO));
    }

    @Transactional
    public User updateUser(String username, UserDTO userDTO){
        // username 과 UserDTO username 일치하는지 확인
        if(!username.equals(userDTO.getUsername())){
            throw new UserNotFoundException("권한이 존재하지 않습니다.");
        }
        User user = findByMethodAndValue("name", username);

        user.setUsername(userDTO.getUsername());

        if (!Objects.equals(userDTO.getPassword(), user.getPassword())) {
            user.setPassword(userDTO.getPassword());
        }
        if (!Objects.equals(userDTO.getEmail(), user.getEmail())) {
            user.setEmail(userDTO.getEmail());
        }
        if (!Objects.equals(userDTO.getPhoneNumber(), user.getPhoneNumber())) {
            user.setPhoneNumber(userDTO.getPhoneNumber());
        }
        if (!Objects.equals(userDTO.getProfileImage(), user.getProfileImage())) {
            user.setProfileImage(userDTO.getProfileImage());
        }
        if (!Objects.equals(userDTO.getInterest(), user.getInterest())) {
            user.setInterest(userDTO.getInterest());
        }
        if (!Objects.equals(userDTO.getIntroduction(), user.getIntroduction())) {
            user.setIntroduction(userDTO.getIntroduction());
        }
        userRepository.save(user);
        return user;
    }

    @Transactional
    public User deleteUser(String username){
        User user = findByMethodAndValue("name", username);
        user.setActive(false);
        user.preRemove();
        userRepository.save(user);
        return user;
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}
