package Logic;

import java.util.ArrayList;
import java.util.List;

public abstract class Hero{
    private String heroName = "";
    private int HP = 30;
    private int defense=0;
    private boolean freedom=false;
    private boolean heroPowerBool=false;
    private int manaHeroPower = 2;
    private int timeHeroPowerUse = 1;
    private List<Card> specialCard;


    public Hero(String name){
        specialCard = new ArrayList();
        heroName = name;
    }


    //
    public void setHeroPowerBool(boolean bool) {
        heroPowerBool = bool;
    }

    public void setHP(int hp) {
        HP+=hp;
    }

  //  ino bokon add card
    public void setCard(Card card) {
        specialCard.add(card);
    }

    public void setCard(List<Card> card) {
        specialCard.addAll(card);
    }

    public List<Card> getSpecialCard() {
        return specialCard;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void addDefense(int defense){ this.defense +=defense;}

    public void addHeroPower() {
        HP ++;
    }

    public int getHeroPower(){return HP;}

    public void specialPower() {
        // Hazine 2 mana kamtr baray spell pardakht mikonn
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String name) {
        heroName = name;
    }

    boolean isFreedom() {
        return freedom;
    }

    void setFreedom(boolean Freedom) {
        freedom = Freedom;
    }

    public String heroSerial() {
        String seri="name*"+heroName+"*"+"HP*"+String.valueOf(HP)+"*"+"freedom*"+String.valueOf(freedom)+"*"+"heropower*"+String.valueOf(heroPowerBool)+"**";
        return seri;
    }

    public void heroDesrial(String name, int hp, boolean free, boolean pow) {
        heroName = name;
        HP=hp;
        freedom=free;
        heroPowerBool=pow;
    }

    public int getManaHeroPower() {
        return manaHeroPower;
    }

    public void setManaHeroPower(int manaHeroPower) {
        this.manaHeroPower = manaHeroPower;
    }

    public int getTimeHeroPowerUse() {
        return timeHeroPowerUse;
    }

    public void setTimeHeroPowerUse(int timeHeroPowerUse) {
        this.timeHeroPowerUse = timeHeroPowerUse;
    }
}


class Mage extends Hero {
    public Mage(String name) {
        super(name);
    }
}

class Warlock extends Hero{
    public Warlock(String name) {
        super(name);
    }
}

class Rouge extends Hero{
    public Rouge(String name) {
        super(name);
    }
}

class Priest extends Hero {
    public Priest(String name) {
        super(name);
    }
}

class Hunter extends Hero {
    public Hunter (String name) {
        super(name);
    }

}
