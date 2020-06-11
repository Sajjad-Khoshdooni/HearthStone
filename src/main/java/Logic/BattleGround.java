package Logic;

import gui.ErrorWindow;

import java.util.ArrayList;
import java.util.List;

public class BattleGround {
    private Player homePlayer;
    private List<String> events;
    private int battleGroundMana;
    private int homePlayerBGMana;
    private int manaJump = 0;
    private int round = 0;
    private FileWorks fileWorks;

    private List<Card> handsCard;
    private List<Card> deckCard;
    private List<Card> onBoard;

    //passive part
    private List<Card> diedCardList;
    private Card diedCard;

    //
    private static String cardSkinPath = "skin1";
    private int battleGroundNumber = 2;

    public BattleGround(){
        events = new ArrayList<>();
        handsCard = new ArrayList<>();
        deckCard = new ArrayList<>();
        onBoard = new ArrayList<>();
        diedCardList = new ArrayList<>();
        battleGroundMana = 0;
        fileWorks = new FileWorks();
    }

    public void setHomePlayer(Player homePlayer) {
        this.homePlayer = homePlayer;

        handsCard.addAll(homePlayer.getGamePlatoonCard().getCards());

    }

    //Event
    public String getEvents(){
        String event="";
        for (int i=events.size()-1 ; i>=0 ; i--){
            event += events.get(i);
            event += "\n";
        }
        return event;
    }

    public void addEvent(String event){
        events.add(event);
    }

    //MANA Manager
    public int getBattleGroundMana() {
        return battleGroundMana;
    }

    /**
    mana jump kos nanaro dorost kon
     */
    public void addBattleGroundMana() {
        round++;
        if (battleGroundMana < 10) battleGroundMana ++;
        homePlayerBGMana = battleGroundMana ;
    }

    public int getManaJump() {
        return manaJump;
    }

    public void setManaJump(int manaJump) {
        this.manaJump = manaJump;
    }

    public int getHomePlayerMana(){
        return homePlayerBGMana;
    }

    public int getRound() {
        return round;
    }

    // Card Manager

    public List<Card> getHandsCard() {
        return handsCard;
    }

    public List<Card> getDeckCard() {
        return deckCard;
    }

    public List<Card> getOnBoard() {
        return onBoard;
    }

    public void addDeckCard(){
        if (deckCard.size() < 12){
            deckCard.add(handsCard.get(round % handsCard.size()));
        }
    }

    public void removeDeckCard(Card card){deckCard.remove(card);}

    public void addOnBoard(Card card){
        if (onBoard.size() < 7) {
            if (homePlayerBGMana < card.getCardMana()){
                new ErrorWindow("MANA Error","Ok");
                return;
            }
            else if (card.getCardType().toLowerCase().equals("minion")) onBoard.add(card);

            homePlayerBGMana -= card.getCardMana();
            addEvent("homePlayer played: " + card.getCardName());
            removeDeckCard(card);

        }
    }

    public void removeOnBoard(Card card){
        diedCardList.add(card);
        diedCard = card;
        onBoard.remove(card);}

    public List<Card> getDiedCardList() {
        return diedCardList;
    }

    public void setDiedCardNull(){
        diedCard = null;
    }

    public Card getDiedCard(){
        return diedCard;
    }

    // in mitone private bashe;
    public void removeHomePlayerOnDeckCard(Card card){deckCard.remove(card);}

    // Hero Manager
    public void addHeroHp(){ homePlayer.getPlayerHero().addHeroPower(); }

    public int getHeroHp(){ return homePlayer.getPlayerHero().getHeroPower();}

    // Single Card Manager
    public void addCardHp(Card card, int hp){
        for (Card hCard: deckCard){
            if (hCard.getCardName().equals(card.getCardName())) hCard.addCardHP(hp);
        }
    }

    public int getCardHp(Card card){
        for (Card hCard: deckCard){
            if (hCard.getCardName().equals(card.getCardName())) return hCard.getCardHP();
        }
        return 0;
    }

    public void addCardAttack(Card card, int attack){
        for (Card hCard: deckCard){
            if (hCard.getCardName().equals(card.getCardName())) hCard.addCardAttack(attack);
        }
    }

    public int getCardAttack(Card card){
        for (Card hCard: deckCard){
            if (hCard.getCardName().equals(card.getCardName())) return hCard.getCardAttack();
        }
        return 0;
    }

    public boolean checkPlayCard(Card card){
        if (homePlayerBGMana >= card.getPrize()) return true;
        return false;
    }

    public void playCard(Card card){
        homePlayerBGMana -= card.getCardMana();
        deckCard.remove(card);
    }

    //

    public String getCardSkinPath() {
        return cardSkinPath;
    }

    public void setCardSkinPath(String cardSkinPath) {
        this.cardSkinPath = cardSkinPath;
    }

    public int getBattleGroundNumber() {
        return battleGroundNumber;
    }

    public void setBattleGroundNumber(int battleGroundNumber) {
        this.battleGroundNumber = battleGroundNumber;
    }
}
