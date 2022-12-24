package actions;

import java.util.HashMap;
import java.util.Map;

import actions.implementation.DefaultAction;
import actions.implementation.LogInAction;

public final class ActionFactory {
    
    private static final ActionFactory ACTION_FACTORY = new ActionFactory();
    private static final Map<String, Action> ACTION_MAP = new HashMap<>();

    static {
        // ACTION_MAP.put("default",  new LogInAction());
        ACTION_MAP.put("log_in",  new LogInAction());
    }

    private ActionFactory() {}

    public static ActionFactory geActionFactory() { return ACTION_FACTORY;}

    public Action createAction(String actionName) {
        return ACTION_MAP.getOrDefault(actionName, new DefaultAction());
    }
}
