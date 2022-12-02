package db.postgres;

import db.DBManager;

public class PostgresDBManager extends DBManager {
    
    private static PostgresDBManager instance;

    public PostgresDBManager() {
        super.driverClassName = "org.postgresql.Driver";
    }

    public static synchronized PostgresDBManager getInstance() {
        if (instance == null) {
            instance = new PostgresDBManager();
        }
        return instance;
    }
}