package uni.ami.restdb.ServiseImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uni.ami.restdb.exceptions.FindException;
import uni.ami.restdb.model.Train;
import uni.ami.restdb.model.User;
import uni.ami.restdb.repository.UserRepository;
import uni.ami.restdb.service.UserService;


@Slf4j //TODO: lombok
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User save(User user) {
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
                    user_temp.setPassword(user.getPassword());

                    return save(user_temp);
                }).orElseThrow(FindException::new);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(FindException::new);
    }

    @Override
    public String getPasswordByUsersId(Long id) {
        return userRepository.findById(id).orElseThrow(FindException::new).getPassword();
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


}
