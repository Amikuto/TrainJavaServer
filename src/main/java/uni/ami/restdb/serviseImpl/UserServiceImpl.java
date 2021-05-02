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


@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
        String hashPasswd = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashPasswd);
        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> delete(Long id) {
        User train = userRepository.findById(id).orElseThrow(FindException::new);
        userRepository.delete(train);
        return new ResponseEntity<>(HttpStatus.OK);
    }

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

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Пользователя с заданным id не найдено!"));
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

//    @Override
//    public String getPasswordByUsersId(Long id) {
//        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Пользователя с заданным id не найдено!")).getPassword();
//    }

    public boolean checkUserPassword(String userLogin, Map<String, String> givenPassword) {
        String userPassword = userRepository.findByLogin(userLogin).getPassword();
        return BCrypt.checkpw(givenPassword.get("password"), userPassword);
    }
}
