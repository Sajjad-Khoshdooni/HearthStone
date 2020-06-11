package Logic;

public class Status {
    private String platoonName = "";
    private Hero platoonHero;
    private float platoonWinBesideGameNumber = 0;
    private int platoonWinNumber = 0;
    private int platoonGameNumber = 0;
    private float manaAverage = 0;
    private String mostUsedCard = "";

    public Status(PlatoonCard platoonCard){
        this.platoonName = platoonCard.getPlatoonName();
        this.platoonHero = platoonCard.getHero();
        this.platoonWinBesideGameNumber = platoonCard.getPlatoonWinBesideGameNumber();
        this.platoonWinNumber = platoonCard.getPlatoonWinNumber();
        this.platoonGameNumber = platoonCard.getPlatoonGameNumber();
        this.manaAverage = 0;
        this.mostUsedCard = null;
    }

    public String getInfo(){
        String s="";
        s += "Platoon name: "+platoonName+"\n";
        s += "Platoon Hero: "+platoonHero.getHeroName()+"\n";
        s += "Platoon win beside game: "+platoonWinBesideGameNumber+"\n";
        s += "Platoon win number: "+platoonWinNumber+"\n";
        s += "Platoon game number: "+platoonGameNumber+"\n";
        s += "Mana average: "+manaAverage+"\n";
        s += "Most used card: "+mostUsedCard;
        return s;
    }

    //setters and getters
    public String getPlatoonName() {
        return platoonName;
    }

    public void setPlatoonName(String platoonName) {
        this.platoonName = platoonName;
    }

    public Hero getPlatoonHero() {
        return platoonHero;
    }

    public void setPlatoonHero(Hero platoonHero) {
        this.platoonHero = platoonHero;
    }

    public float getPlatoonWinBesideGameNumber() {
        return platoonWinBesideGameNumber;
    }

    public void setPlatoonWinBesideGameNumber(int platoonWinBesideGameNumber) {
        this.platoonWinBesideGameNumber = platoonWinBesideGameNumber;
    }

    public int getPlatoonWinNumber() {
        return platoonWinNumber;
    }

    public void setPlatoonWinNumber(int platoonWinNumber) {
        this.platoonWinNumber = platoonWinNumber;
    }

    public int getPlatoonGameNumber() {
        return platoonGameNumber;
    }

    public void setPlatoonGameNumber(int platoonGameNumber) {
        this.platoonGameNumber = platoonGameNumber;
    }

    public float getManaAverage() {
        return manaAverage;
    }

    public void setManaAverage(int manaAverage) {
        this.manaAverage = manaAverage;
    }

    public String getMostUsedCard() {
        return mostUsedCard;
    }

    public void setMostUsedCard(String mostUsedCard) {
        this.mostUsedCard = mostUsedCard;
    }
}
