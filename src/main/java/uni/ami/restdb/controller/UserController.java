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

/**
 * Контроллер пользователей
 * @author damir
 */
@RestController
public class UserController {

    @Autowired
    UserServiceImpl userService;

    /**
     * Контроллер GET запроса по ссылке "/users"
     * для получения информации о всех пользователях
     * @param pageable параметр заглушка
     * @return возвращает страницу со всеми пользователями из базы данных
     */
    @GetMapping("/users")
    public Page<User> getAllUsers(Pageable pageable) {
        return userService.getAll(pageable);
    }

    /**
     * Контроллер GET запроса по ссылке "/users/{login}"
     * для получения информации о пользователе по его логину
     * @param login параметр логина
     * @return возвращает класс пользователя {@link User}
     */
    @GetMapping("/users/{login}")
    public User getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }

    /**
     * Контроллер POST запроса по ссылке "/users/password/{userLogin}"
     * получает в теле запроса пароль и сверяет его правильность с тем, что в базе данных
     * @param userLogin параметр логина пользователя
     * @param password JSON данные с паролем
     * @return возвращает true, если пароли верны, в противном случае false
     */
    @PostMapping("/users/password/{userLogin}")
    public boolean checkCorrectUser(@PathVariable String userLogin,
                                    @Valid @RequestBody Map<String, String> password) {
        return userService.checkUserPassword(userLogin, password);
    }

    /**
     * Контроллер POST запроса по ссылке "/users"
     * для добавления нового пользователя в базу данных
     * @param user JSON данные о пользователе
     * @return возвращает сохраненный класс пользователя
     */
    @PostMapping("/users")
    public User addNewUser(@Valid @RequestBody User user) {
        return userService.save(user);
    }

    /**
     * Контроллер DELETE запроса по ссылке "/users/{userId}"
     * для удаления пользователя из базы данных
     * @param userId параметр id пользователя
     * @return возвращает код статуса
     */
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.delete(userId);
    }
}
