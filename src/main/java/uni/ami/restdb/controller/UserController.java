package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import uni.ami.restdb.ServiseImpl.UserServiceImpl;
import uni.ami.restdb.model.User;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/users")
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.getAll(pageable);
    }

    @GetMapping("/users/password/{userId}")
    public String getPassword(@PathVariable Long userId) {
        return userService.getPasswordByUsersId(userId);
    }
}
