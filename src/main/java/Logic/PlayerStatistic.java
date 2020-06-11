package Logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * in bakhsh baray bakhsh status hast ba tavajoh b doc chon parametr haye status 0 ast
 * dar faz badi in bakhsh amel va mored estefade garar migirad
 */

public class PlayerStatistic {
    HashMap<String,Integer> cardMap;
    private int timeCardUsed;
    private int mana;

    public PlayerStatistic(){
        cardMap = new HashMap();
        timeCardUsed = 0;
        mana = 0;
    }

    public void addTimeCardUsed(Card card) {
        try {
            if (!cardMap.containsKey(card.getCardName())) cardMap.put(card.getCardName(), 1);
            else {
                cardMap.put(card.getCardName(), cardMap.get (card.getCardName()) + 1);
            }

            addMana(card.getCardMana());
            addTimeCardUsed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addTimeCardUsed(){
        this.timeCardUsed++;
    }

    private void addMana(int mana){ this.mana += mana ;}

    public float getManaAverage() {
        return (float) mana/timeCardUsed;
    }

    public String getMostUsedCard() {
        int max = 0;
        String firstCardname = "";
        String secondCardname = "";
        for(Map.Entry<String, Integer> entry : cardMap.entrySet()) {
            if (entry.getValue() > max) {
                firstCardname = entry.getKey();
                max = entry.getValue();
            }
            else if(entry.getValue() == max){
                secondCardname = entry.getKey();
            }
        }
        if (!secondCardname.equals("")){
            Card a = null , b=null;
            for (Card card:new Board().getCards()){
                if (card.getCardName().equals(firstCardname)){
                    a = card;
                }
                else if (card.getCardName().equals(secondCardname)){
                    b = card;
                }
            }
            if (a.getCardClass().equals("Legendary") && !b.getCardClass().equals("Legendary")) return a.getCardName();
            else if (b.getCardClass().equals("Legendary") && !a.getCardClass().equals("Legendary")) return b.getCardName();
            else if (a.getCardClass().equals("Epic") && !b.getCardClass().equals("Epic")) return a.getCardName();
            else if (b.getCardClass().equals("Epic") && !a.getCardClass().equals("Epic")) return b.getCardName();
            else if (a.getCardClass().equals("Rare") && !b.getCardClass().equals("Rare")) return a.getCardName();
            else if (b.getCardClass().equals("Rare") && !a.getCardClass().equals("Rare")) return b.getCardName();
            else if (a.getCardClass().equals("Common") && !b.getCardClass().equals("Common")) return a.getCardName();
            else if (b.getCardClass().equals("Common") && !a.getCardClass().equals("Common")) return b.getCardName();
            else if( a.getCardMana() > b.getCardMana()) return a.getCardName();
            else if (a.getCardMana() > b.getCardMana()) return b.getCardName();
            else if (a.getCardClass().equals("Minion") && !b.getCardClass().equals("Minion")) return a.getCardName();
            else if (b.getCardClass().equals("Minion") && !a.getCardClass().equals("Minion")) return b.getCardName();
        }
        return firstCardname;
    }
}
