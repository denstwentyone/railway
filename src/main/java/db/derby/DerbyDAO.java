package db.derby;

import db.AbstractDAO;

public class DerbyDAO extends AbstractDAO{

    private static AbstractDAO instance;

    private DerbyDAO() {
        configDManager("derby");
    }

    public static synchronized AbstractDAO getInstance() {
        if (instance == null) {
            instance = new DerbyDAO();
        }
        return instance;
    }


}
