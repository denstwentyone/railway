package actions;

import java.util.HashMap;
import java.util.Map;

import actions.implementation.DefaultAction;
import actions.implementation.LogInAction;
import actions.implementation.LogOutAction;
import actions.implementation.SignUpAction;

public final class ActionFactory {
    
    private static final ActionFactory ACTION_FACTORY = new ActionFactory();
    private static final Map<String, Action> ACTION_MAP = new HashMap<>();

    static {
        // ACTION_MAP.put("default",  new LogInAction());
        ACTION_MAP.put("login",  new LogInAction());
        ACTION_MAP.put("logout",  new LogOutAction());
        ACTION_MAP.put("signup",  new SignUpAction());


    }

    private ActionFactory() {}

    public static ActionFactory geActionFactory() { return ACTION_FACTORY;}

    public Action createAction(String actionName) {
        return ACTION_MAP.getOrDefault(actionName, new DefaultAction());
    }
}
