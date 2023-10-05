package unknown.backend.dev.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import unknown.backend.dev.domain.User;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService{
    private List<User> Users;

    public List<User> findAllUser(){
        return Users;
    }

    public User findUserById(String id) return Users.Values();








}
