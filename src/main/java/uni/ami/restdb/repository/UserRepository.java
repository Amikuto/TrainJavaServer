package uni.ami.restdb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uni.ami.restdb.model.User;

/**
 * Репозиторий пользователей
 * @author damir
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

    User findByLoginEquals(String login);
}
