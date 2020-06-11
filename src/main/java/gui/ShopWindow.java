package gui;

import Config.*;
import Controller.Controller;
import Logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class ShopWindow extends JPanel{
    private JComboBox shopCombo;
    private JTextField playerDiamondLabel;
    private JTextField prize;
    private JButton show;
    private MyButton exitButton;

    private Controller controller;
    private Player player;
    private Board board;
    private Shop shop;
    private List<Card> buyAbleCards;
    private List<Card> sellAbleCards;

    private boolean check = false;

    //updateAble Component
    private List<Card> frameCard;
    private List<List<MyButton>> frameCardButtonList;
    private List<MyButton> frameCardButton;
    private List<JButton> frameCardNumberButton;

    private boolean showButtonCheck = false;
    //
    private Config conf = Config.getInstance();
    private ImageLoader imageLoader = ImageLoader.getInstance();

    public ShopWindow(Controller controller,MyButton exitButton) {
        this.controller = controller;
        this.exitButton = exitButton;
        shop = new Shop();
    }

    public void setData(Board board, Player player){
        this.board = board;
        this.player = player;
        initFrame();
        initComponent();

        update();
    }

    public void setShowButtonCheck(boolean showButtonCheck) {
        this.showButtonCheck = showButtonCheck;
    }

    private void initFrame(){
        setSize(new Dimension(1200,700));
        setLayout(null);
        setVisible(true);
    }

    private void initComponent(){
        buyAbleCards = new ArrayList<>();
        sellAbleCards = new ArrayList<>();
        frameCardButtonList = new ArrayList<>();
        frameCardNumberButton = new ArrayList<>();
        frameCard = new ArrayList<>();
        frameCardButton = new ArrayList<>();

        //
        updateCardList();
        //

        prize = new JTextField("Prize: 11 Diamonds");
        prize.setBounds(0,50,90,30);
        prize.setEditable(false);

        playerDiamondLabel = new JTextField(String.valueOf(player.getDiomonds()));
        playerDiamondLabel.setBounds(0,0,40,40);
        playerDiamondLabel.setEditable(false);

        shopCombo = new JComboBox();
        shopCombo.setBounds(550,0,120,30);

        //set up Combo box
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        empModel.addElement("Buy Card");
        empModel.addElement("Sell Card");

        shopCombo.setModel(empModel);
        shopCombo.setEditable(false);

        //inam mitone hazf she
        show = new JButton("show Card");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (shopCombo.getSelectedItem().toString()){
                    case "Buy Card":
                        showButtonCheck = true;
                        updateCardList();
                        updateFrameCardList(buyAbleCards);
                        update();
                        controller.log("check buyable cards");
                        break;
                    case "Sell Card":
                        showButtonCheck = true;
                        updateCardList();
                        updateFrameCardList(sellAbleCards);
                        update();
                        controller.log("check sellable card");
                        break;
                }
            }
        });
        show.setBounds(700,0,110,30);
    }

    public void update(){

        updateCardList();
        updateFrameCardButtonList();
        updateFrameCardButton();
        updateNumberButtons();
        updateDiamondLabel();
        checkLockCards();

        addMainFrameComponent();
        addAction(this);

        //update
        repaint();
        revalidate();
    }

    void updateCardList(){
        // ino check kon bug nade
        buyAbleCards.clear();
        sellAbleCards.clear();

        sellAbleCards.addAll(shop.checkSellableCard(player));
        buyAbleCards.addAll(shop.checkBuyableCard(player,board));
    }

    private void checkLockCards() {
        for (Card card:frameCard){
            if (card.isLock()){
                for (JButton button:frameCardButton){
                    if (button.getText().equals(card.getCardName())){
                        button.setBackground(Color.BLACK);
                    }
                }
            }
        }
    }

    private void addAction(ShopWindow shopWindow){
        for (MyButton button:frameCardButton){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (Card card:buyAbleCards){
                        if (button.getText().equals(card.getCardName())) {
                            new ShopWindowError().buy(controller,board,player,card,shopWindow);
                            break;
                        }
                    }

                    for (Card card:sellAbleCards){
                        if (button.getText().equals(card.getCardName())) {
                            new ShopWindowError().sell(controller,player,card,shopWindow);
                            break;
                        }
                    }
                }
            });
        }

        for (JButton button:frameCardNumberButton){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateFrameCardButton(frameCardButtonList.get(Integer.valueOf(button.getText()) - 1));
                    update();
                }
            });
        }
    }

    private void updateDiamondLabel(){
        playerDiamondLabel.setText(String.valueOf(player.getDiomonds()));
    }

    private void updateFrameCardButton(List<MyButton> buttons){
        frameCardButton.clear();
        frameCardButton.addAll(buttons);
    }

    private void updateFrameCardButton(){
        if (showButtonCheck && !frameCardButtonList.isEmpty()) {
            showButtonCheck = false;
            updateFrameCardButton(frameCardButtonList.get(0));
        }
        else if (frameCardButtonList.isEmpty()) frameCardButton.clear();



        new Bounds().table4o4(frameCardButton);
        new Bounds().numberButtons(frameCardNumberButton);
    }

    private void updateNumberButtons() {
        frameCardNumberButton.clear();

        if (frameCardButtonList.size() == 1) return;

        List<JButton> buttons = new ArrayList<>();
        JButton button = null;
        for (int i=0 ; i < frameCardButtonList.size() ; i++){
            button = new JButton(String.valueOf(i+1));
            int ii = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateFrameCardButton(frameCardButtonList.get(ii));
                }
            });
            buttons.add(button);
        }
        new Bounds().numberButtons(buttons);
        frameCardNumberButton.addAll(buttons);

    }

    void updateFrameCardList(List<Card> cards){
        frameCard.clear();
        frameCard.addAll(cards);
    }

    private void updateFrameCardButtonList(){
        frameCardButtonList.clear();
        frameCardButtonList.addAll(new CardMaker().generate(frameCard));
    }

    private void addMainFrameComponent(){
        removeAll();

        add(shopCombo);
        add(playerDiamondLabel);
        add(show);
        add(prize);
        add(exitButton);

        for (JButton button: frameCardButton){
            add(button);
        }

        for (JButton button: frameCardNumberButton){
            add(button);
        }
    }

    public String checkState(){
        if (check) return "success";
        return "null";
    }

    public void trueCheck() {
        this.check = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageLoader.getBackGroundImage(), 0, 0, getWidth(), getHeight(), imageLoader.getBackGroundImageObserver());
    }
}
