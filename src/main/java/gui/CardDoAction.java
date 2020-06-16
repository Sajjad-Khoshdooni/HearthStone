package gui;

import Logic.Board;
import Logic.Player;

public class CardDoAction {
    private Board board;
    private Player homePlayer;
    private Player enemyPlayer;

    public CardDoAction(Board board, Player homePlayer, Player enemyPlayer) {
        this.board = board;
        this.homePlayer = homePlayer;
        this.enemyPlayer = enemyPlayer;
    }

    public void action(CardAction cardAction){
        switch (cardAction.getActionName().toLowerCase()){
            case "discover":
                discover(cardAction.getAction());
                return;

            case "rush":
                rush(cardAction.getAction());
                return;

            case "charge":
                charge(cardAction.getAction());
                return;

            case "divineshield":
                divineShield(cardAction.getAction());
                return;

            case "taunt":
                taunt(cardAction.getAction());
                return;

            case "lifesteal":
                lifeSteal(cardAction.getAction());
                return;

            case "poisonous":
                poisonous(cardAction.getAction());
                return;

            case "overkill":
                overkill(cardAction.getAction());
                return;

            case "twinspell":
                twinSpell(cardAction.getAction());
                return;

            case "reborn":
                reborn(cardAction.getAction());
                return;

            case "outcast":
                outcast(cardAction.getAction());
                return;

            case "dormant":
                dormant(cardAction.getAction());
                return;

            case "inspire":
                inspire(cardAction.getAction());
                return;

            case "windfury":
                windFury(cardAction.getAction());
                return;

            case "megawindfury":
                megaWindFury(cardAction.getAction());
                return;

            case "restore":
                restore(cardAction.getAction());
                return;

            case "echo":

            case "repeatable":
                echo(cardAction.getAction());
                return;

            case "stealth":
                stealth(cardAction.getAction());
                return;

        }
    }

    private void discover(String action){}
    private void rush(String action){}
    private void charge(String action){}
    private void divineShield(String action){}
    private void taunt(String action){}
    private void lifeSteal(String action){}
    private void poisonous(String action){}
    private void overkill(String action){}
    private void twinSpell(String action){}
    private void reborn(String action){}
    private void outcast(String action){}
    private void dormant(String action){}
    private void inspire(String action){}
    private void windFury(String action){}
    private void megaWindFury(String action){}
    private void restore(String action){}
    private void echo(String action){}
    private void stealth(String action){}
}
