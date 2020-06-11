package gui;

import Config.SoundPlayer;
import Controller.Controller;
import Controller.GameState;
import Logic.FileWorks;
import Logic.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameFrame extends JFrame {
    private JLabel label;
    private JButton exit;
    private JButton cancel;

    private Player player;
    private Controller controller;
    private SoundPlayer soundPlayer;

    public EndGameFrame(Player player, Controller controller, SoundPlayer soundPlayer){
        super("End");
        this.player = player;
        this.controller = controller;
        this.soundPlayer = soundPlayer;

        initFrame();
        initComponent();
    }

    private void initComponent(){
        label = new JLabel("Exit Game?");
        label.setBounds(112,50,90,30);
        add(label);

        exit = new JButton("Exit");
        exit.setBounds(50,100,90,30);
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new FileWorks().writePlayerAccInfo(player);
                new FileWorks().writeBoardInfo();

                soundPlayer.pause();
                controller.setVisible(false);
                setVisible(false);
            }
        });
        add(exit);

        cancel = new JButton("Cancel");
        cancel.setBounds(150,100,90,30);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(cancel);
    }

    private void initFrame(){
        setSize(300,200);
        setLayout(null);
        setTitle("HearthStone");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
