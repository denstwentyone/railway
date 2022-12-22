package db.dao;

import java.sql.SQLException;

public interface UserDAO {
    
    public long signUp(String email, String password) throws SQLException;

    public boolean logIn(String email, String password) throws SQLException;
}
