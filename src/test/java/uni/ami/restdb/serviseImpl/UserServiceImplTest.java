package uni.ami.restdb.serviseImpl;

import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceImplTest {
    public static void main(String[] args) {
        String password = "12";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

        if (BCrypt.checkpw("12", hashed))
            System.out.println("It matches");
        else
            System.out.println("It does not match");
    }

}