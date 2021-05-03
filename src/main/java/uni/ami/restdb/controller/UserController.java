package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.serviseImpl.UserServiceImpl;
import uni.ami.restdb.model.User;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/users")
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.getAll(pageable);
    }

    @GetMapping("/users/{login}")
    public User getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }

    @PostMapping("/users/password/{userLogin}")
    public boolean checkCorrectUser(@PathVariable String userLogin,
                                    @Valid @RequestBody Map<String, String> password) {
        return userService.checkUserPassword(userLogin, password);
    }

    @PostMapping("/users")
    public User addNewUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.delete(userId);
    }
}
