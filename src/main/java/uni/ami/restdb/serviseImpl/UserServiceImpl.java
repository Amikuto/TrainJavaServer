package uni.ami.restdb.serviseImpl;

import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.ami.restdb.exceptions.FindException;
import uni.ami.restdb.exceptions.ResourceNotFoundException;
import uni.ami.restdb.model.User;
import uni.ami.restdb.repository.UserRepository;
import uni.ami.restdb.service.UserService;

import java.util.Map;


/**
 * Класс сервиса пользователей
 * @author damir
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    /**
     * Функция сохранения пользователя в базе данных
     * @param user принимает класс пользователя для сохранения {@link User}
     * @return возвразает сохраненного пользователя {@link User}
     */
    @Override
    public User save(User user) {
        String hashPasswd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashPasswd);
        return userRepository.save(user);
    }

    /**
     * Функция удаления пользователя из базы данных
     * @param id принимет в качестве параметра id пользователя
     * @return возвращает HttpStatus.OK {@link ResponseEntity}
     */
    @Override
    public ResponseEntity<?> delete(Long id) {
        User train = userRepository.findById(id).orElseThrow(FindException::new);
        userRepository.delete(train);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Функция изменения информации о пользователе в базе данных
     * @param id принимет в качестве параметра id пользователя
     * @param user принимет в качестве параметра класс пользователя для изменения данных {@link User}
     * @return возвращает класс пользователя с измененной информацией {@link User}
     */
    @Override
    public User update(Long id, User user) {
        return userRepository.findById(id)
                .map(user_temp -> {
                    user_temp.setFullName(user.getFullName());
                    user_temp.setLogin(user.getLogin());
                    user_temp.setEmail(user.getEmail());
                    String hashPasswd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
                    user.setPassword(hashPasswd);

                    return save(user_temp);
                }).orElseThrow(() -> new ResourceNotFoundException("Пользователя с заданным id не найдено!"));
    }

    /**
     * Функция поиска пользователя по id
     * @param id принимет в качестве параметра id пользователя
     * @return возвращает класс пользователя {@link User}
     */
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Пользователя с заданным id не найдено!"));
    }

    /**
     * Функция поиска пользователя по логину
     * @param login принимет в качестве параметра логин пользователя
     * @return возвращает класс пользователя {@link User}
     */
    public User getUserByLogin(String login) {
        return userRepository.findByLoginEquals(login);
    }

    /**
     * Функция поиска всех пользователей в базе данных
     * @param pageable
     * @return возвращает список всех пользователей в формате Pageable {@link Pageable}
     */
    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

//    @Override
//    public String getPasswordByUsersId(Long id) {
//        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Пользователя с заданным id не найдено!")).getPassword();
//    }

    /**
     * Функция проверки переданного пользователем пароля с паролем в базе данных
     * @param userLogin параметр логина пользователя
     * @param givenPassword параметр введенного пароля пользователем
     * @return возвращает true при успешной проверке или false в обратном случае
     */
    public boolean checkUserPassword(String userLogin, Map<String, String> givenPassword) {
        String userPassword = userRepository.findByLogin(userLogin).getPassword();
        try {
            BCrypt.checkpw(givenPassword.get("password"), userPassword);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
