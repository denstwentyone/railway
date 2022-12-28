package db.postgres;

import db.AbstractDAO;

public class PostgresDAO extends AbstractDAO {
    
    private static AbstractDAO instance;

    private PostgresDAO() {
        configDManager("postgres");
    }

    public static synchronized AbstractDAO getInstance() {
        if (instance == null) {
            instance = new PostgresDAO();
        }
        return instance;
    }
}