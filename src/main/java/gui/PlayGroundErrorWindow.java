package gui;

import Controller.Controller;
import Logic.BattleGround;
import Logic.Card;
import Logic.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayGroundErrorWindow extends JFrame{
    private MyButton cardButton;
    private JButton button1;
    private JButton button2;


    public PlayGroundErrorWindow(){
        initFrame();
        initComponent();
    }

    private void initFrame(){
        setSize(new Dimension(600,400));
        setLayout(null);
        setTitle("Play");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initComponent(){
        button1 = new JButton("play");
        button2 = new JButton("cancel");

        //errorLabel.setBounds();
        button1.setBounds(230,280,60,30);
        button2.setBounds(310,280,60,30);


        add(button1);
        add(button2);

    }

    public void generate(Controller controller, Card card, BattleGround battle, Player player, PlayWindow pl){
        cardButton = new MyButton(card.getCardName());
        cardButton.setBounds(240,50,120,190);
        add(cardButton);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                battle.addOnBoard(card);
                player.addTimeCardUsed(card);
                setVisible(false);
                pl.update();
                controller.log(player.getUsername(),"played "+card.getCardName());
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}
