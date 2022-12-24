package db.derby;

import db.DAOFactory;

public class DerbyDAO extends DAOFactory{

    private static DAOFactory instance;

    private DerbyDAO() {
        
    }

    public static synchronized DAOFactory getInstance() {
        if (instance == null) {
            instance = new DerbyDAO();
            configDManager("derby");
        }
        return instance;
    }
}
