package Logic;

public class Passive {
    private BattleGround battle;
    private Player player;
    private Board board;

    private boolean check = false;

    public Passive(BattleGround battle, Player player) {
        this.battle = battle;
        this.player = player;
        this.board = new Board();
    }

    public void update(){
        if (check) return;

        switch (player.getPassiveEnum()){
            case twiceDraw:
                twiceDraw();
                return;

            case offCards:
                offCards();
                return;

            case warriors:
                warriors();
                return;

            case nurse:
                nurse();
                return;

            case freePower:
                freePower();
                return;

            case manaJump:
                manaJump();
                return;

            case zombies:
                zombies();
                return;
        }
    }

    private void twiceDraw(){
        battle.addDeckCard();
    }

    private void offCards(){
        for (Card card:battle.getDeckCard()) {
            for (Card card1:board.getCards()){
                if (card.getCardName().equals(card1.getCardName())){
                    card.setCardMana(card1.getCardMana()-1);
                }
            }
        }
    }

    private void warriors(){
        if (battle.getDiedCard().getCardClass().toLowerCase().equals("minion")){
            player.getGamePlatoonCard().getHero().setDefense(player.getGamePlatoonCard().getHero().getDefense()+2);
            battle.setDiedCardNull();
        }
    }

    private void nurse(){
        for (Card card:battle.getOnBoard()){
            if (card.getCardClass().equals("Minion")){
                for (Card card1:board.getCards()){
                    if (card.getCardName().equals(card1.getCardName())){
                        card.setCardHP(card1.getCardHP());
                        break;
                    }
                }
                break;
            }
        }
    }

    private void freePower(){
        player.getGamePlatoonCard().getHero().setManaHeroPower(player.getGamePlatoonCard().getHero().getHeroPower()-1);
        player.getGamePlatoonCard().getHero().setTimeHeroPowerUse(player.getGamePlatoonCard().getHero().getTimeHeroPowerUse()+1);
    }

    private void manaJump(){
        battle.addBattleGroundMana();
        check = true;
    }

    private void zombies(){
        player.getGamePlatoonCard().getHero().setManaHeroPower(0);
        Card cd = null;
        for (Card card:battle.getDiedCardList()){
            if (card.getCardClass().equals("Minion") && (cd==null || card.getCardMana()>cd.getCardMana())){
                cd = card;
            }
        }
        if (cd != null) battle.addOnBoard(cd);
    }
}

