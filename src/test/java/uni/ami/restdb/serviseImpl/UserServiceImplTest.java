package uni.ami.restdb.serviseImpl;

import org.mindrot.jbcrypt.BCrypt;

class UserServiceImplTest {
    public static void main(String[] args) {
        String password = "123";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt());

        if (BCrypt.checkpw("123", hashed))
            System.out.println("It matches");
        else
            System.out.println("It does not match");
    }

}