package db.dao;

import java.sql.SQLException;

import db.entities.User;

public interface UserDAO {
    
    public long signUp(String email, String password) throws SQLException;

    public long logIn(String email, String password) throws SQLException;

    public User getUser(String email) throws SQLException;

}
