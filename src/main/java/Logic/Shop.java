package Logic;

import java.util.ArrayList;
import java.util.List;

public class Shop{
    public List<Card> checkBuyableCard(Player player,Board board) {
        List<Card> buyableCard=new ArrayList<Card>();

        if(player.getEntireCardList()==null){buyableCard=board.getCards();}
        else {
            for (Card sdcard : board.getCards()) {
                if (!player.getEntireCardList().contains(sdcard)) {
                    buyableCard.add(sdcard);
                }
            }
        }
        return buyableCard;
    }

    public List<Card> checkSellableCard(Player player){
        return player.getEntireCardList();
    }

    public void buy(Player player,Card card){
        player.addEntireCardList(card);
        player.addDiomonds(-card.getPrize());
    }

    public void sell(Player player,Card card){
        player.removeEntireCard(card);
        player.addDiomonds(card.getPrize());
    }

    public boolean buyCheck(Player player,Card card){
        if (player.getDiomonds() < card.getPrize() ) return false;
        else if (card.isLock()) return false;
        return true;
    }
}
