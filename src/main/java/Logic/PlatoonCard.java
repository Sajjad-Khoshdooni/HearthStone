package Logic;

import java.util.ArrayList;
import java.util.List;

public class PlatoonCard {
    //ridi check kon bbin chish kame

    private String platoonName="";
    private Hero hero;
    private List<Card> cards = new ArrayList<>();

    private float platoonWinBesideGameNumber;
    private int platoonWinNumber = 0;
    private int platoonGameNumber = 0;
    private float platoonPayedPrize = 0;

    public PlatoonCard(String platoonName, Hero hero) {
        this.platoonName = platoonName;
        this.hero = hero;
    }

    public  PlatoonCard(){}

    // setters and getters
    public void setPlatoonWinBesideGameNumber(float platoonWinBesideGameNumber) {
        this.platoonWinBesideGameNumber = platoonWinBesideGameNumber;
    }

    public void removeCards(Card card){
        cards.remove(card);
    }

    public float getPlatoonPayedPrize() {
        return platoonPayedPrize;
    }

    private void addPlatoonPayedPrize(float platoonPayedPrize) {
        this.platoonPayedPrize += platoonPayedPrize;
    }

    public String getPlatoonName() {
        return platoonName;
    }

    public void setPlatoonName(String platoonName) {
        this.platoonName = platoonName;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void addCards(Card card) {
        this.cards.add(card);
        addPlatoonPayedPrize((float) card.getPrize());
    }

    public void setCards(List<Card> cards){
        this.cards.addAll(cards);
    }

    public float getPlatoonWinBesideGameNumber() {
        return platoonWinBesideGameNumber;
    }

    private void setPlatoonWinBesideGameNumber() {
        this.platoonWinBesideGameNumber = (platoonWinNumber / platoonGameNumber);
    }

    public int getPlatoonWinNumber() {
        return platoonWinNumber;
    }

    public void addPlatoonWinNumber() {
        this.platoonWinNumber ++;
        setPlatoonWinBesideGameNumber();
    }

    public int getPlatoonGameNumber() {
        return platoonGameNumber;
    }

    public void addPlatoonGameNumber() {
        this.platoonGameNumber ++;
        setPlatoonWinBesideGameNumber();
    }

    public void deserialize(String platoonName,float platoonWinBesideGameNumber, int platoonWinNumber,
                            int platoonGameNumber, float platoonPayedPrize){
        this.platoonName = platoonName;
        this.platoonWinBesideGameNumber = platoonWinBesideGameNumber;
        this.platoonWinNumber = platoonWinNumber;
        this.platoonGameNumber = platoonGameNumber;
        this.platoonPayedPrize = platoonPayedPrize;
    }

    public String serialize(){
        String serial="";

        serial +="name&"+platoonName+"&pwbg&"+String.valueOf(platoonWinBesideGameNumber)+"&pwn&"+
                String.valueOf(platoonWinNumber)+"&pgn&"+String.valueOf(platoonGameNumber)+"&ppp&"+
                String.valueOf(platoonPayedPrize)+"&&";

        return serial;
    }
}
