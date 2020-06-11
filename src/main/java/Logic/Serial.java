package Logic;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Serial{

    Gson gs=new Gson();

    public String cardSerialize(List<Card> cards){return gs.toJson(cards);}

    public String platoonSerialize(List<PlatoonCard> platoons){
        String serial = "";
        for (PlatoonCard pl:platoons){
            serial += pl.serialize();
            List<Hero> heros = new ArrayList<>();
            heros.add(pl.getHero());
            serial += "&hero&"+heroSerialize(heros);
            serial += "&cards&"+cardSerialize(pl.getCards());
            serial +="&&nextPlatoon&";
        }
        return serial;
    }

    // ino check kon
    public String heroSerialize(List<Hero> heros){
        String str="";
        for(Hero hero:heros){
            str+=hero.heroSerial();
            str+=cardSerialize(hero.getSpecialCard());
            str+="**nexthero*";
        }
        return str;
    }

    public String playerSeralize(Player player){
        List<Hero> lp=new ArrayList<>();
        lp.add(player.getPlayerHero());
        String str = "";
        str += player.playerSerialiaze();
        try {
            str += "+Entirecard+" + cardSerialize(player.getEntireCardList()) ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            str += "++"+ platoonSerialize(player.getPlatoonCardList())+"+";
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            str += "+hero+" + heroSerialize(lp) + "+";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public List<PlatoonCard> platoonDeserialize(String serial){
        List<PlatoonCard> platoons = new ArrayList<PlatoonCard>();


        String word="";
        List<String> ary=new ArrayList();
        boolean check=false;
        for(int i=0;i<serial.length();i++){
            if(check || serial.charAt(i)=='&'){
                if(serial.charAt(i)=='&'){
                    check= !check;
                    if(!word.equals("") && !word.equals("nextPlatoon")){
                        ary.add(word);
                    }
                    if(word.equals("nextPlatoon")){
                        PlatoonCard pl = new PlatoonCard();
                        pl.deserialize(ary.get(0), Float.valueOf(ary.get(1)),Integer.valueOf(ary.get(2)),
                                Integer.valueOf(ary.get(3)),Float.valueOf(ary.get(4)));
                        pl.setHero(heroDeserialize(ary.get(5)).get(0));
                        pl.setCards(cardDeserialize(ary.get(6)));
                        platoons.add(pl);

                        ary.clear();

                    }
                    word = "";
                    continue;
                }
                word += serial.charAt(i);
            }
        }

        return platoons;
    }

    public Player playerDeserialize (String serial){
        String word="";
        List<String> ary=new ArrayList<>();
        boolean check=false;

        for (int i=0;i<serial.length();i++){
            if(check || serial.charAt(i)=='+') {
                if (serial.charAt(i) == '+') {
                    check = !check;
                    if(!word.equals("")){
                        ary.add(word);
                    }
                    word="";
                    continue;
                }
                word+=serial.charAt(i);
            }
        }


        Player player=new Player();
        try {
            player.playerDeserialize(ary.get(0), ary.get(1), Integer.valueOf(ary.get(2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            player.setEntireCardList(cardDeserialize(ary.get(3)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            player.setPlatoonCardList(platoonDeserialize(ary.get(4)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            player.setPlayerHero(heroDeserialize(ary.get(5)).get(0));
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return player;
    }

    public List<Card> cardDeserialize(String card){
        CardDeserializer cd=new CardDeserializer("cardType");
        cd.registerBarnType("Minion",Minion.class);
        cd.registerBarnType("Spell",Spell.class);
        cd.registerBarnType("Weapon",Weapon.class);

        Gson gson=new GsonBuilder().registerTypeAdapter(Card.class,cd).create();
        return gson.fromJson(card, new TypeToken<List<Card>>(){}.getType());
    }


    public List<Hero> heroDeserialize(String serial){
        // dar sorat afzayesh hero ha in bakhsh tagir mikonad
        List<Hero> hero=new ArrayList<Hero>();
        String word="";
        List<String> ary=new ArrayList();
        boolean check=false;
        for(int i=0;i<serial.length();i++){
            if(check || serial.charAt(i)=='*'){
                if(serial.charAt(i)=='*'){
                    check= !check;
                    if(!word.equals("") && !word.equals("nexthero")){
                        ary.add(word);
                    }
                    if(word.equals("nexthero")){
                        if(ary.get(0).equals("Mage")){
                            Hero mg=new Mage("Mage");
                            mg.heroDesrial(ary.get(0),Integer.valueOf(ary.get(1)),Boolean.valueOf(ary.get(2)),Boolean.valueOf(ary.get(3)));
                            mg.setCard(cardDeserialize(ary.get(4)));
                            hero.add(mg);
                            ary.clear();
                        }
                        else if(ary.get(0).equals("Rouge")){
                            Hero rg=new Rouge("Rouge");
                            rg.heroDesrial(ary.get(0),Integer.valueOf(ary.get(1)),Boolean.valueOf(ary.get(2)),Boolean.valueOf(ary.get(3)));
                            rg.setCard(cardDeserialize(ary.get(4)));
                            hero.add(rg);
                            ary.clear();
                        }
                        else if(ary.get(0).equals("Warlock")){
                            Hero wr=new Warlock("Warlock");
                            wr.heroDesrial(ary.get(0),Integer.valueOf(ary.get(1)),Boolean.valueOf(ary.get(2)),Boolean.valueOf(ary.get(3)));
                            wr.setCard(cardDeserialize(ary.get(4)));
                            hero.add(wr);
                            ary.clear();
                        }
                        else if(ary.get(0).equals("Priest")){
                            Hero wr=new Warlock("Priest");
                            wr.heroDesrial(ary.get(0),Integer.valueOf(ary.get(1)),Boolean.valueOf(ary.get(2)),Boolean.valueOf(ary.get(3)));
                            wr.setCard(cardDeserialize(ary.get(4)));
                            hero.add(wr);
                            ary.clear();
                        }
                        else if(ary.get(0).equals("Hunter")){
                            Hero wr=new Warlock("Hunter");
                            wr.heroDesrial(ary.get(0),Integer.valueOf(ary.get(1)),Boolean.valueOf(ary.get(2)),Boolean.valueOf(ary.get(3)));
                            wr.setCard(cardDeserialize(ary.get(4)));
                            hero.add(wr);
                            ary.clear();
                        }
                    }
                    word="";
                    continue;
                }
                word+=serial.charAt(i);
            }
        }

        return hero;
    }


    public class CardDeserializer implements JsonDeserializer<Card> {
        private String cardTypeElementName;
        private Gson gson;
        private Map<String, Class<? extends Card>> cardTypeRegistry;

        public CardDeserializer(String cardTypeElementName) {
            this.cardTypeElementName = cardTypeElementName;
            this.gson = new Gson();
            this.cardTypeRegistry = new HashMap<>();
        }

        public void registerBarnType(String cardTypeName, Class<? extends Card> cardType) {
            cardTypeRegistry.put(cardTypeName, cardType);
        }

        public Card deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
            JsonObject cardObject = json.getAsJsonObject();
            JsonElement cardTypeElement = cardObject.get(cardTypeElementName);

            Class<? extends Card> cardType = cardTypeRegistry.get(cardTypeElement.getAsString());
            return gson.fromJson(cardObject, cardType);
        }

    }
}
