package deckderby.utils.misc;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String createSalt() {
        return BCrypt.gensalt();
    }

    public static String hashPassword(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }
}
