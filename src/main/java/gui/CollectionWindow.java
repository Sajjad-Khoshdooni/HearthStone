package gui;


import Config.Config;
import Config.ImageLoader;
import Controller.Controller;
import Logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static Controller.GameState.menu;

//reached hasho siah kon

public class CollectionWindow extends JPanel {
    private List<JButton> heroButtons;
    private JLabel manaLabel;
    private JComboBox manaCombo;
    private JButton manaButton;
    private JTextField searchField;
    private JButton searchButton;
    private JCheckBox existCheckBox;
    private MyButton exitButton;

    private JButton newPlatoonCardButton;
    private JLabel platoonLabel;
    private JComboBox platoonCombo;
    private JButton platoonButton;
    private JButton platoonEditButton;

    private Controller controller;
    private Board board;
    private Player player;
    private Shop shop;
    private boolean numberButtonAction = false;
    //
    private Config conf = Config.getInstance();
    private ImageLoader imageLoader = ImageLoader.getInstance();

    //updateable
    private List<Card> frameCard;
    private List<List<MyButton>> frameCardButtonList;
    private List<MyButton> frameCardButton;
    private List<JButton> numberButtonList;

    private boolean check = false;

    public CollectionWindow(Controller controller,MyButton exitButton){
        this.controller = controller;
        this.exitButton = exitButton;
        shop = new Shop();
    }

    public void setData(Board board, Player player){
        this.board = board;
        this.player = player;

        initComponent();
        initPanel();
    }

    private void initPanel(){
        setSize(new Dimension(conf.getData("framex"),conf.getData("framey")));
        setLayout(null);
        setVisible(true);
    }

    private void initComponent(){
        frameCard = new ArrayList<>();
        frameCardButtonList = new ArrayList<>();
        frameCardButton = new ArrayList<>();
        numberButtonList = new ArrayList<>();

        heroButtons = new ArrayList<>();
        JButton button = null;
        for(Hero hero: board.getHeros()){
            button = new JButton(hero.getHeroName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    controller.log("checking hero card",hero.getHeroName());
                    updateFrameCard(hero.getSpecialCard());
                    update();
                }
            });
            heroButtons.add(button);
        }

        MyButton neutral = new MyButton("Neutral");
        neutral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.log("checking neutral cards");
                List<Card> cards = new ArrayList<>();
                for (Card card:board.getCards()){
                    if (card.getCardClass().toLowerCase().equals("neutral")){
                        cards.add(card);
                    }
                }
                updateFrameCard(cards);
                update();
            }
        });
        heroButtons.add(neutral);

        manaLabel = new JLabel("Mana Filter:");
        manaCombo = new JComboBox();
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        for (int i=1 ; i<11 ; i++){
            empModel.addElement(String.valueOf(i));
        }
        manaCombo.setModel(empModel);
        manaCombo.setEditable(false);

        manaButton = new JButton("mana");
        manaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.log("mana filter"+String.valueOf(manaCombo.getSelectedIndex()));
                List<Card> cards = new ArrayList<>();
                for (Card card: frameCard){
                    if (card.getCardMana() == (manaCombo.getSelectedIndex()+1)){
                        cards.add(card);
                    }
                }
                updateFrameCard(cards);
                update();
            }
        });

        searchField = new JTextField();
        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.log("search card",searchField.getText());
                frameCard.clear();
                boolean check = true;
                if (searchField.getText().isEmpty()) return;
                for (Card card:board.getCards()){
                    if (card.getCardName().toLowerCase().contains(searchField.getText().toLowerCase())){
                        ArrayList cards = new ArrayList();
                        cards.add(card);
                        updateFrameCard(cards);
                        check = false;
                        break;
                    }
                }
                if (check) new ErrorWindow("No-Existing-Card!!!","Ok");
                update();
            }
        });

        newPlatoonCardButton = new JButton("New PlatoonCard");
        //passing collectionWindow
        CollectionWindow collectionWindow = this;
        newPlatoonCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.log("creating platoon");
                PlatoonMaker pl = new PlatoonMaker();
                pl.setData(board, player,collectionWindow);

                new ErrorWindow("choose card from your EntireCardList","ok");
            }
        });

        platoonLabel = new JLabel("Platoons:");
        platoonCombo = new JComboBox();
        DefaultComboBoxModel plModel = new DefaultComboBoxModel();
        for (PlatoonCard pl:player.getPlatoonCardList()){
            plModel.addElement(pl.getPlatoonName());
        }
        platoonCombo.setModel(plModel);
        platoonCombo.setEditable(false);
        platoonButton = new JButton("Show Platoon");
        platoonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.log("checking platoon");
                frameCard.clear();
                for (PlatoonCard pl: player.getPlatoonCardList()) {
                    if (pl.getPlatoonName().equals(platoonCombo.getSelectedItem().toString())) {
                        updateFrameCard(pl.getCards());
                        update();
                    }
                }
            }
        });

        platoonEditButton = new JButton("Edit");
        platoonEditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.log("Editing platoon");
                for (PlatoonCard pl:player.getPlatoonCardList()) {
                    if (pl.getPlatoonName().equals(platoonCombo.getSelectedItem().toString())) {
                     new PlatoonMaker().setData(board, player, pl,collectionWindow);

                     new ErrorWindow("choose card from your EntireCardList","ok");
                     update();
                    }
                }
            }
        });

        existCheckBox = new JCheckBox("Have");
        existCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.log("checking exist card");
                if (existCheckBox.isSelected()){
                    List<Card> cards = new ArrayList<>();
                    int i=0;
                    for (Card card: frameCard){
                        if (i == 12) break;
                        for (Card cd: player.getEntireCardList()){
                            if (card.getCardName().equals(cd.getCardName()) ){
                                cards.add(card);
                            }
                        }
                        i++;
                    }
                    updateFrameCard(cards);
                    update();
                }
                else{
                    int i=0;
                    List<Card> cards = new ArrayList<>();
                    cards.addAll(frameCard);
                    for (Card card: frameCard){
                        if (i == 12) break;
                        for (Card cd: player.getEntireCardList()){
                            if (cd.getCardName().equals(card.getCardName())){
                                cards.remove(card);
                                break;
                            }
                        }
                        i++;
                    }
                    updateFrameCard(cards);
                    update();
                }
            }
        });

        setBounds();
    }

    private void setBounds(){
        platoonLabel.setBounds(30, 50,70,30);
        platoonCombo.setBounds(100, 50,conf.getData("buttonx"),conf.getData("buttony"));
        platoonButton.setBounds(200, 50,conf.getData("buttonx"),conf.getData("buttony"));
        platoonEditButton.setBounds(300,50,conf.getData("buttonx"),conf.getData("buttony"));
        manaLabel.setBounds(400, 50,conf.getData("buttonx"),conf.getData("buttony"));
        manaCombo.setBounds(500,50,conf.getData("buttonx"),conf.getData("buttony"));
        manaButton.setBounds(600 , 50, conf.getData("buttonx"),conf.getData("buttony"));
        searchField.setBounds(700 , 50 ,conf.getData("buttonx"),conf.getData("buttony"));
        searchButton.setBounds(800, 50,conf.getData("buttonx"),conf.getData("buttony"));
        existCheckBox.setBounds(900,50,conf.getData("buttonx"),conf.getData("buttony"));
        newPlatoonCardButton.setBounds(1000,50,120,30);

        int i=0;
        for (JButton button: heroButtons){
            button.setBounds(1050,200 + i*50,conf.getData("buttonx"),conf.getData("buttony"));
            add(button);
            i++;
        }

        add(manaLabel);
        add(manaCombo);
        add(manaButton);
        add(searchField);
        add(searchButton);
        add(existCheckBox);
        add(newPlatoonCardButton);
        add(platoonLabel);
        add(platoonCombo);
        add(platoonButton);
        add(platoonEditButton);
        add(exitButton);
    }

    public void update(){
        updateFrameCardButtonList();
        updateNumberButtonList();
        checkLockCards();
        updatePlatoonCombo();

        updateFrameCardButton();

        addComponent();
        addAction();

        //update frame
        repaint();
        revalidate();
    }

    /**
     * background cartaye lock siahe
     */
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

    private void addAction(){
        for (MyButton button:frameCardButton){
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    boolean check = false;
                    for (Card card:shop.checkBuyableCard(player,board)){
                        if (button.getText().equals(card.getCardName())){
                            new ShopWindowError().buy(controller,board,player,card,null);
                            check = true;
                            break;
                        }
                    }
                    if (check) return;
                    for (Card card:shop.checkSellableCard(player)){
                        if (button.getText().equals(card.getCardName())){
                            new ShopWindowError().sell(controller,player,card,null);
                            break;
                        }
                    }
                }
            });
        }
    }

    private void updateFrameCard(List<Card> cards){
        frameCard.clear();
        frameCard.addAll(cards);
    }

    private void updateFrameCardButtonList(){
        frameCardButtonList.clear();
        frameCardButtonList.addAll(new CardMaker().generate(frameCard));
    }

    private void updatePlatoonCombo(){
        platoonCombo.removeAllItems();
        DefaultComboBoxModel plModel = new DefaultComboBoxModel();
        for (PlatoonCard pl:player.getPlatoonCardList()){
            plModel.addElement(pl.getPlatoonName());
        }
        platoonCombo.setModel(plModel);
        platoonCombo.setEditable(false);
    }

    private void updateFrameCardButton(List<MyButton> buttons) {
        frameCardButton.clear();
        frameCardButton.addAll(buttons);
        new Bounds().table4o4(frameCardButton);
    }

    private void updateFrameCardButton(){
        if (frameCardButton.isEmpty() && !frameCardButtonList.isEmpty()) updateFrameCardButton(frameCardButtonList.get(0));

        else if (frameCardButtonList.isEmpty()) frameCardButton.clear();

        else if (!frameCardButton.isEmpty() && numberButtonList.size() == 0){
            updateFrameCardButton(frameCardButtonList.get(0));
        }

        else if (!frameCardButton.isEmpty() && !numberButtonAction ) {
            numberButtonAction = false;
            updateFrameCardButton(frameCardButtonList.get(0));
        }

    }

    private void updateNumberButtonList(){
        numberButtonList.clear();

        if (frameCardButtonList.size() == 1) return;

        JButton button = null;
        for (int i=0 ; i<frameCardButtonList.size(); i++){
            button = new JButton(String.valueOf(i+1));
            int ii = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    numberButtonAction = true;
                    updateFrameCardButton(frameCardButtonList.get(ii));
                    update();
                }
            });
            numberButtonList.add(button);
        }
        new Bounds().numberButtons(numberButtonList);
    }

    private void addComponent(){
        removeAll();

        add(manaLabel);
        add(manaCombo);
        add(manaButton);
        add(searchField);
        add(searchButton);
        add(existCheckBox);
        add(newPlatoonCardButton);
        add(platoonLabel);
        add(platoonCombo);
        add(platoonButton);
        add(platoonEditButton);
        add(exitButton);

        for (JButton button:heroButtons){
            add(button);
        }

        for (MyButton button:frameCardButton){
            add(button);
        }

        for (JButton button:numberButtonList){
            add(button);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageLoader.getBackGroundImage(),
                0, 0,
                getWidth(), getHeight(), imageLoader.getBackGroundImageObserver());
    }

    public String checkState(){

        if (check){
            return "success";
        }
        return "null";
    }

    public void trueCheck() {
        this.check = false;
    }
}