package services;

import java.sql.SQLException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import db.AbstractDAO;
import db.entities.User;

public class UserService implements Service {
    
    private static AbstractDAO dao;

    public UserService(AbstractDAO dao) {
        UserService.dao = dao;
    }

    public boolean signUp(String email, String password) throws Exception {
        if (isValidPassword(password)) {
            try {
                dao.signUp(email, password);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                throw new SQLException("user already exist");
            }
            return true;
        }
        else {
            throw new Exception("invalid password");
        }
    }

    public Optional<User> login(String email, String password) throws SQLException {
        try {
            return dao.logIn(email, password);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            throw new SQLException("invalid input");
        }
    }

    private static boolean isValidPassword(String password) {
  
        // Regex to check valid password.
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$";
  
        // Compile the ReGex
        Pattern p = Pattern.compile(regex);
  
        // If the password is empty
        // return false
        if (password == null) {
            return false;
        }
  
        // Pattern class contains matcher() method
        // to find matching between given password
        // and regular expression.
        Matcher m = p.matcher(password);
  
        // Return if the password
        // matched the ReGex
        return m.matches();
    }
}
