package db.postgres;

import db.DBManager;

public class PostgresDBManager extends DBManager {
    
    private static DBManager instance;

    private PostgresDBManager() {
        
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new PostgresDBManager();
            configDManager("postgres");
        }
        return instance;
    }
}