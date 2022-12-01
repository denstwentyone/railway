package db.derby;

import db.DBManager;

public class DerbyDBManager extends DBManager {

    public DerbyDBManager() {
        super.driverClassName = "org.apache.derby.jdbc";
    }
    
}
