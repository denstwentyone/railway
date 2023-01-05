package db.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import db.entities.Train;
import db.entities.User;

public interface UserDAO {
    
    public long signUp(String email, String password) throws SQLException;

    public Optional<User> logIn(String email, String password) throws SQLException;

    public Optional<User> getUser(String email) throws SQLException;

    public List<Train> getTrainsForUser(User user) throws SQLException;

}
