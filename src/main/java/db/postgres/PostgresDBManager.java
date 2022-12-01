package db.postgres;

import db.DBManager;

public class PostgresDBManager extends DBManager {

    public PostgresDBManager() {
        super.driverClassName = "org.postgresql.Driver";
    }

}
