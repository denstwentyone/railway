package db.derby;

import db.DBManager;

public class DerbyDBManager extends DBManager {

    private static DerbyDBManager instance;

    private DerbyDBManager() {
        super.driverClassName = "org.apache.derby.jdbc";
    }
    
    public static synchronized DerbyDBManager getInstance() {
        if (instance == null) {
            instance = new DerbyDBManager();
        }
        return instance;
    }
}
