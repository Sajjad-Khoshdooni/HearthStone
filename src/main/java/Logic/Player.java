package Logic;

import com.google.gson.Gson;
import gui.PassiveEnum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Player extends PlayerStatistic{
    private String username;
    private String password;

    private int diomonds=50;
    private Hero playerHero;
    private List<Card> entireCardList;    //whole player card
    private PlatoonCard gamePlatoonCard = null;
    private List<PlatoonCard> platoonCardList; // list of players platoon
    private PassiveEnum passiveEnum;

    public Player(){
        entireCardList = new ArrayList<Card>();
        platoonCardList = new ArrayList<>();
    }

    public Player(String username, String password){
        entireCardList = new ArrayList<Card>();
        platoonCardList = new ArrayList<>();

        this.username = username;
        this.password = hashPassword(password);
    }

    private String hashPassword(String pass){
        String string = "";
        for (int i=0 ; i<pass.length() ; i ++){
            string += pass.charAt(i) +"*";
        }
        return string;
    }

    private String onHashpass(String string){
        String password ="";
        for (int i=0 ; i<string.length() ; i+=2){
            password +=string.charAt(i);
        }
        return password;
    }

    public PlatoonCard getGamePlatoonCard() {
        return gamePlatoonCard;
    }

    public void setGamePlatoonCard(PlatoonCard gamePlatoonCard) {
        this.gamePlatoonCard = gamePlatoonCard;
    }

    public PlatoonCard initPlatoonCard(String platoonName, Hero hero){
        PlatoonCard pl = new PlatoonCard(platoonName, hero);
        platoonCardList.add(pl);
        return pl;
    }

    public void removePlatoonCards(PlatoonCard platoonCard){platoonCardList.remove(platoonCard);}

    public String getPlatoonCardInfo(PlatoonCard platoonCard){
        return new Gson().newBuilder().setPrettyPrinting().create().toJson(platoonCard);
    }

    public List<PlatoonCard> getTop10PlatoonCards(){
        List<PlatoonCard> list = new ArrayList<>();

        if (platoonCardList.size()<=10) return platoonCardList;

        platoonCardList.sort(getPlatoonCardComparator());

        for (int i=1 ; i<=10 ; i++){
            if (platoonCardList.size()-i < 0) break;
            list.add(platoonCardList.get(platoonCardList.size()-i));
        }

        return list;
    }

    private Comparator<PlatoonCard> getPlatoonCardComparator() {

        Comparator<PlatoonCard> comparator = new Comparator<PlatoonCard>() {
            @Override
            public int compare(PlatoonCard o1, PlatoonCard o2) {
                if (o1.getPlatoonWinBesideGameNumber() > o2.getPlatoonWinBesideGameNumber()) return 1;
                else if (o1.getPlatoonWinBesideGameNumber() < o2.getPlatoonWinBesideGameNumber()) return -1;
                else if (o1.getPlatoonWinNumber() > o2.getPlatoonWinNumber() ) return 1;
                else if (o1.getPlatoonWinNumber() < o2.getPlatoonWinNumber()) return -1;
                else if (o1.getPlatoonGameNumber() > o2.getPlatoonGameNumber()) return 1;
                else if (o1.getPlatoonGameNumber() < o2.getPlatoonGameNumber()) return -1;
                else if (o1.getPlatoonPayedPrize() > o2.getPlatoonPayedPrize()) return 1;
                else if ((o1.getPlatoonPayedPrize() < o2.getPlatoonPayedPrize())) return -1;

                return (int) Math.pow(-1.00,new Random().nextInt(2));
            }
        };
        return comparator ;
    }

    // getter and setter
    public PassiveEnum getPassiveEnum() {
        return passiveEnum;
    }

    public void setPassiveEnum(PassiveEnum passiveEnum) {
        this.passiveEnum = passiveEnum;
    }

    public List<PlatoonCard> getPlatoonCardList() {
        return platoonCardList;
    }

    public void setPlatoonCardList(List<PlatoonCard> platoonCardList) {
        this.platoonCardList = platoonCardList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return onHashpass(password);
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
    }

    public Hero getPlayerHero() { return playerHero; }

    public void setPlayerHero(Hero playerHero) { this.playerHero = playerHero; }

    public int getDiomonds() {
        return diomonds;
    }

    public void addDiomonds(int diomonds) {
        this.diomonds += diomonds;
    }

    public List<Card> getEntireCardList() {
        return entireCardList;
    }

    public void addEntireCardList(Card entirecardList) {
        entireCardList.add(entirecardList) ;
    }

    public void setEntireCardList(List<Card> entirecardList){entireCardList=entirecardList;}

    public void removeEntireCard(Card card){this.entireCardList.remove(card);}

    public String playerSerialiaze(){
        String s="";
        s="name+"+username+"+"+"Password+"+password+"+"+"Diamond+"+String.valueOf(diomonds)+"++";
        return s;
    }

    public void playerDeserialize(String name,String pass,int di){
        username=name;
        password=pass;
        diomonds=di;
    }
}
