package db.postgres;

import db.DAOFactory;

public class PostgresDAO extends DAOFactory {
    
    private static DAOFactory instance;

    private PostgresDAO() {
        
    }

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            instance = new PostgresDAO();
            configDManager("postgres");
        }
        return instance;
    }
}