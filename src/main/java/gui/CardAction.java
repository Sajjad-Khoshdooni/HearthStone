package gui;

public class CardAction {
    private String actionName;
    private String Action;

    public CardAction(String actionName, String action) {
        this.actionName = actionName;
        Action = action;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getAction() {
        return Action;
    }

    public void setAction(String action) {
        Action = action;
    }
}
