package db.derby;

import db.DBManager;

public class DerbyDBManager extends DBManager {

    private static DBManager instance;

    private DerbyDBManager() {
        
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DerbyDBManager();
            configDManager("derby");
        }
        return instance;
    }
}
