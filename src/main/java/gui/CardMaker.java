package gui;

import Logic.Card;

import java.util.ArrayList;
import java.util.List;

public class CardMaker {

    public List<List<MyButton>> generate(List<Card> cards){
        List<List<MyButton>> buttonsList = new ArrayList<>();
        List<MyButton> buttons =null;
        MyButton button = null;

        for (int i=0 ; i <= cards.size() ; i++){
            if (i == 0) {buttons = new ArrayList<>();}
            else if (i%12 == 0){
                buttonsList.add(buttons);
                buttons = new ArrayList<>();
            }
            else if (i == cards.size()) {
                buttonsList.add(buttons);
                break;
            }

            if (i < cards.size()) {
                button = new MyButton( cards.get(i).getCardName());
                buttons.add(button);
            }
        }
        return buttonsList;
    }
}
