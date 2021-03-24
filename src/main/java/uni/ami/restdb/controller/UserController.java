package uni.ami.restdb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uni.ami.restdb.ServiseImpl.UserServiceImpl;
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

    @PostMapping("/users/password/{userLogin}")
    public boolean checkCorrectUser(@PathVariable String userLogin,
                                    @Valid @RequestBody Map<String, String> password) {
        System.out.println(password);
        return userService.checkCorrectUser(userLogin, password);
    }
}
