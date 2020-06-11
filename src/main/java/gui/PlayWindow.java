package gui;

import Config.*;
import Controller.Controller;
import Logic.BattleGround;
import Logic.Card;
import Logic.Passive;
import Logic.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *try and catch ha bekhtr ine vagti list khalie bug nade
 */

public class PlayWindow extends JPanel {
    private boolean endTurnBoolean = false;
    private boolean endGame = false;

    private List<List<MyButton>> handCardsButton;
    private List<List<MyButton>> onBoardCardsButton;
    private JTextArea eventArea;

    private JTextField manaLabel;
    private JButton endButton;
    private MyButton exitButton;
    private MyButton heroPower;

    //////////////////////////////////////////////
    private Controller controller;
    private BattleGround battle;
    private Player player;
    private Passive passive;
    private Config conf = Config.getInstance();
    private ImageLoader imageLoader = ImageLoader.getInstance();

    public PlayWindow(Controller controller,MyButton exitButton) {
        this.controller = controller;
        this.exitButton = exitButton;
        battle = new BattleGround();
    }

    public void setPlayer(Player player) {
        new PassiveWindow(player);

        this.player = player;

        passive = new Passive(battle,player);

        battle.setHomePlayer(player);

        initFrame();
        initComponent();
    }

    private void initFrame(){
        setLayout(null);
        setSize(new Dimension(1200,700));
        setVisible(true);
    }

    private void initComponent(){
        handCardsButton = new ArrayList<>();
        onBoardCardsButton = new ArrayList<>();
        eventArea = new JTextArea();
        manaLabel = new JTextField("0");
        endButton = new JButton("End turn");
        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.log(player.getUsername(),"Pressed End button");
                //passive
                passive.update();

                battle.addBattleGroundMana();
                manaLabel.setText(String.valueOf(battle.getHomePlayerMana())+"//"+String.valueOf(battle.getBattleGroundMana()));
                battle.addDeckCard();
                update();
            }
        });
        heroPower = new MyButton(player.getGamePlatoonCard().getHero().getHeroName()+"hp");
        setupComponent();
    }

    private void setupComponent(){
        eventArea.setBounds(0,380,180,320);

        manaLabel.setBounds(1140, 330, 40,40);
        manaLabel.setEditable(false);
        endButton.setBounds(conf.getData("battleendx"+battle.getBattleGroundNumber()),
                conf.getData("battleendy"+battle.getBattleGroundNumber()),
                conf.getData("battleendxsize"+battle.getBattleGroundNumber()),
                conf.getData("battleendysize"+battle.getBattleGroundNumber()));
        heroPower.setBounds(1120,560,conf.getData("cardx"),conf.getData("cardy"));

        add(eventArea);
        add(manaLabel);
        add(endButton);
        add(heroPower);
    }

    private void updateHandCardsButton(){
        /** chon tedad kamtar az 30tas moshkel ijad nemikone
         * fgt havaset b {List<List<JButton>>} bashe
         */
        handCardsButton = new CardMaker().generate(battle.getDeckCard());
    }

    private void updateOnBoardCardsButton(){
        /** chon tedad kamtar az 30tas moshkel ijad nemikone
         * fgt havaset b {List<List<JButton>>} bashe
         */
        onBoardCardsButton = new CardMaker().generate(battle.getOnBoard());
    }

    public void update(){
        updateHandCardsButton();
        updateOnBoardCardsButton();
        updateBounds();
        updateAction(this);
        eventArea.setText(battle.getEvents());

        manaLabel.setText(String.valueOf(battle.getHomePlayerMana())+"/"+String.valueOf(battle.getBattleGroundMana()));

        addComponent();

        // update
        repaint();
        revalidate();
    }

    private void addComponent(){
        removeAll();

        add(eventArea);
        add(manaLabel);
        add(endButton);
        add(exitButton);
        add(heroPower);

        try {
            for (JButton button : handCardsButton.get(0)) {
                add(button);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            for (JButton button : onBoardCardsButton.get(0)) {
                add(button);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateBounds(){
        try{
            if (onBoardCardsButton.size() > 0) new Bounds().homePlayerOnBoard(onBoardCardsButton.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (handCardsButton.size() > 0) new Bounds().handCard12(handCardsButton.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateAction(PlayWindow pl){
        try {
            for (MyButton button : handCardsButton.get(0)) {
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (Card card : battle.getHandsCard()) {
                            if (card.getCardName().equals(button.getText())) {
                                // ino kml kon
                                new PlayGroundErrorWindow().generate(controller,card, battle, player,pl);
                                update();
                            }
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            for (MyButton button : onBoardCardsButton.get(0)) {
                //ino dar faz hay badi miznim
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("EmptyONBoardCard");
        }
    }

    public String checkState(){
        //update frame
        revalidate();

        if (endGame){
            return "success";
        }
        return "null";
    }

    public void trueCheck() {
        this.endGame = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageLoader.getBattleGroundImage(battle.getBattleGroundNumber()),
                197,0,1003,700,
                imageLoader.getBattleGroundImageObserver(battle.getBattleGroundNumber()));

        //hero image
        g.drawImage(imageLoader.getImage(player.getGamePlatoonCard().getHero().getHeroName().toLowerCase()),
                conf.getData("battleherox"+battle.getBattleGroundNumber()),
                conf.getData("battleheroy"+battle.getBattleGroundNumber()),
                conf.getData("battleheroxsize"+battle.getBattleGroundNumber()),
                conf.getData("battleheroysize"+battle.getBattleGroundNumber()),
                imageLoader.getImageObserver(player.getGamePlatoonCard().getHero().getHeroName().toLowerCase()));

        //skin
        g.drawImage(imageLoader.getImage(battle.getCardSkinPath()),
                670,210,65,90,
                imageLoader.getImageObserver(battle.getCardSkinPath()));
    }
}
