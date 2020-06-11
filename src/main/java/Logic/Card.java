package Logic;

public abstract class Card{
    protected  String cardName;
    private int cardMana;
    private String cardRarity;
    protected String cardClass;
    private String cardType;
    private String description;
    protected int prize;
    private boolean lock = false;

    // Cards Except Spells
    private int cardHP;
    private int cardAttack;

    // Cards constructor Except Minions
    public Card(String cardName, int cardMana, String cardRarity, String cardClass, String cardType, String description,int prize) {
        this.cardName = cardName;
        this.cardMana = cardMana;
        this.cardRarity = cardRarity;
        this.cardClass = cardClass;
        this.cardType = cardType;
        this.description = description;
        this.prize = prize;
    }

    //Minions Constructor
    public Card(String cardName, int cardMana, String cardRarity, String cardClass, String cardType, String description, int cardHP, int cardAttack,int prize) {
        this.cardName = cardName;
        this.cardMana = cardMana;
        this.cardRarity = cardRarity;
        this.cardClass = cardClass;
        this.cardType = cardType;
        this.description = description;
        this.cardHP = cardHP;
        this.cardAttack = cardAttack;
        this.prize = prize;
    }


    //they exrta work
    abstract public void CardWork();

    public String getCardName() {
        return cardName;
    }

    public int getPrize() {
        return prize;
    }

    public String getCardClass() {
        return cardClass;
    }

    public int getCardMana() { return cardMana; }

    public void setCardMana(int cardMana) {
        this.cardMana = cardMana;
    }

    public void addCardMana(int mana){ this.cardMana += mana;}

    public int getCardHP() {
        return cardHP;
    }

    public void addCardHP(int cardHP) {
        this.cardHP += cardHP;
    }

    public void setCardHP(int cardHP){this.cardHP = cardHP;}

    public int getCardAttack() {
        return cardAttack;
    }

    public void addCardAttack(int cardAttack) {
        this.cardAttack += cardAttack;
    }

    public boolean isLock() {
        return lock;
    }

    public void lock() {
        this.lock = true;
    }

    public String getCardType() {
        return cardType;
    }
}

class Spell extends Card{

    // constructor
    public Spell(String cardName, int cardMana, String cardRarity, String cardClass, String cardType, String description,int prize) {
        super(cardName, cardMana, cardRarity, cardClass, cardType, description,prize);
    }

    public void CardWork() {
        //Anonymous inner Class Definition
    }

//    Quest and reward
}

class Minion extends Card{

    public Minion(String cardName, int cardMana, String cardRarity, String cardClass, String cardType, String description, int cardHP, int cardAttack, int prize) {
        super(cardName, cardMana, cardRarity, cardClass, cardType, description, cardHP, cardAttack, prize);
    }

    public void CardWork() {
        //Anonymous inner Class Definition
    }



}

class Weapon extends Card{

    public Weapon(String cardName, int cardMana, String cardRarity, String cardClass, String cardType, String description, int WeaponDurablity, int cardAttack, int prize) {
        super(cardName, cardMana, cardRarity, cardClass, cardType, description, WeaponDurablity, cardAttack, prize);
    }

    public void CardWork() {
        //Anonymous inner Class Definition
    }
}
