package services;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import db.AbstractDAO;
import db.entities.Train;
import db.entities.User;
import static util.Validation.*;

public class UserService implements Service {
    
    private static AbstractDAO dao;

    /**
     * @param dao
     */
    public UserService(AbstractDAO dao) {
        UserService.dao = dao;
    }

    /**
     * @param email
     * @param password
     * @return
     * @throws Exception
     */
    public boolean signUp(String email, String password) throws Exception {
        if (isPasswordValid(password) && isEmailValid(email)) {
            try {
                dao.signUp(email, password);
            } catch (Exception e) {
                e.printStackTrace();
                throw new SQLException("user already exist");
            }
            return true;
        }
        else {
            throw new Exception("invalid password or email");
        }
    }

    /**
     * @param email
     * @param password
     * @return
     * @throws SQLException
     */
    public Optional<User> login(String email, String password) throws SQLException {
        try {
            return dao.logIn(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("invalid password or email");
        }
    }

    /**
     * @param userEmail
     * @param page
     * @return
     * @throws SQLException
     */
    public List<Train> getTickets(String userEmail, Integer page) throws SQLException {
        if (isEmailValid(userEmail)) {
            return dao.getTrainsForUser(dao.getUser(userEmail).get(), page);
            
        }
        return null;
    }
}
