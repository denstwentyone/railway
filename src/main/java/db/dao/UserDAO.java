package db.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import db.entities.Train;
import db.entities.User;

public interface UserDAO {
    
    /**
     * @param email
     * @param password
     * @return
     * @throws SQLException
     */
    public long signUp(String email, String password) throws SQLException;

    /**
     * @param email
     * @param password
     * @return
     * @throws SQLException
     */
    public Optional<User> logIn(String email, String password) throws SQLException;

    /**
     * @param email
     * @return
     * @throws SQLException
     */
    public Optional<User> getUser(String email) throws SQLException;

    /**
     * @param user
     * @param page
     * @return
     * @throws SQLException
     */
    public List<Train> getTrainsForUser(User user, Integer page) throws SQLException;

}
