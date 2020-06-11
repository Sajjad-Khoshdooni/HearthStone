package gui;

import Logic.PlatoonCard;
import Logic.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *info bug dre
 * say kon table konish
 */

public class PlatoonCardInfoWindow extends JFrame {
    private JTextArea info;
    private JButton choosePlatoon;
    private JButton exit;

    private Player player;
    private PlatoonCard platoonCard;

    public PlatoonCardInfoWindow(Player player, PlatoonCard platoonCard)  {
        this.player = player;
        this.platoonCard = platoonCard;

        initFrame();
        initComponent();
    }

    private void initFrame(){
        setLayout(null);
        setSize(new Dimension(600,600));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponent(){
        info = new JTextArea();
        info.append(player.getPlatoonCardInfo(platoonCard));
        info.setBounds(100,50,400,400);
        info.setEditable(false);
        add(info);

        choosePlatoon = new JButton("Choose Platoon");
        choosePlatoon.setBounds(200,500,90,30);
        add(choosePlatoon);
        choosePlatoon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.setGamePlatoonCard(platoonCard);
            }
        });

        exit = new JButton("Exit");
        exit.setBounds(300,500,90,30);
        add(exit);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


    }

}
