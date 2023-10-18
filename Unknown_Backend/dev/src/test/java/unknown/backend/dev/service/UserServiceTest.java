package unknown.backend.dev.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import unknown.backend.dev.domain.User;
import unknown.backend.dev.dto.UserDTO;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    UserDTO userDTO = null;

    @BeforeEach
    public void setUp() {
        userDTO = new UserDTO(
                "John1",
                "1234",
                "tees3360123@gmail.com",
                "010-1894-5608",
                "https://avatars.githubusercontent.com/u/50101690?v=4",
                "IT",
                "Hello, I'm John",
                0);

    }
    @AfterEach
    public void tearDown() {
        userService.deleteAll();
    }
    @Test
    @DisplayName("1. Test User Service - Create User")
    void createUserTest() {
        userService.createUser(userDTO);
        User newUser = userService.findByMethodAndValue("name", userDTO.getUsername());
        assertThat(userDTO.getUsername()).isEqualTo(newUser.getUsername());
    }

}
