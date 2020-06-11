package gui;

import Config.ImageLoader;
import Controller.Controller;
import Logic.Board;
import Logic.Card;
import Logic.Player;
import Logic.Shop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopWindowError extends JFrame {
    private Shop shop;
    private JButton button1;
    private JButton button2;
    private MyButton cardBtn;

    public ShopWindowError(){
        initFrame();
        shop = new Shop();
        button2 = new JButton("Cancel");
        button2.setBounds(310,300,60,30);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(button2);
    }

    private void initFrame(){
        setSize(600,400);
        setLayout(null);
        setTitle("HearthStone");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void buy(Controller controller, Board board, Player player, Card supCard, ShopWindow shopWindow){
        cardBtnGenerator(supCard);
        button1 = new JButton("Buy");
        button1.setBounds(230,300,60,30);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (supCard.isLock()){
                    new ErrorWindow("this card is lock","Ok");
                    setVisible(false);
                    controller.log(supCard.getCardName(),"lock card");
                    return;
                }
                if (!shop.buyCheck(player,supCard)) {
                    new ErrorWindow("you dont have enough money","ok");
                    return;
                }
                shop.buy(player, supCard);
                controller.log("buy " , supCard.getCardName());

                if (shopWindow != null) {
                    shopWindow.update();

                    shopWindow.setShowButtonCheck(true);
                    shopWindow.updateCardList();
                    shopWindow.updateFrameCardList(shop.checkBuyableCard(player,board));
                    shopWindow.update();
                }
                setVisible(false);
            }
        });
        add(button1);
    }

    public void sell(Controller controller,Player player, Card supCard,ShopWindow shopWindow){
        cardBtnGenerator(supCard);
        button1 = new JButton("Sell");
        button1.setBounds(230,300,60,30);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shop.sell(player,supCard);
                controller.log("sell ",supCard.getCardName());

                if (shopWindow != null) {
                    shopWindow.update();

                    shopWindow.setShowButtonCheck(true);
                    shopWindow.updateCardList();
                    shopWindow.updateFrameCardList(shop.checkSellableCard(player));
                    shopWindow.update();
                }
                setVisible(false);
            }
        });
        add(button1);
    }

    private void cardBtnGenerator(Card card){
        cardBtn = new MyButton(card.getCardName());
        cardBtn.setBounds(235,50,130,180);
        add(cardBtn);
    }
}
