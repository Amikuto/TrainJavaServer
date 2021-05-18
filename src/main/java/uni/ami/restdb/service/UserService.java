package uni.ami.restdb.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import uni.ami.restdb.model.Train;
import uni.ami.restdb.model.User;

public interface UserService {

    User save(User user);

    ResponseEntity<?> delete(Long id);

    User update(Long id, User user);

    User getUserById(Long id);

    Page<User> getAll(Pageable pageable);
}
