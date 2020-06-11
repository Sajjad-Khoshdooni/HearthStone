package gui;

import Config.ImageLoader;
import Controller.Controller;
import Logic.BattleGround;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JPanel {
    private JButton skinCard;
    private JButton theme;
    private JCheckBox announcer;
    private JButton sound;
    private MyButton exitButton;

    //
    private boolean check = false;

    private BattleGround battleGround;
    private Controller controller;

    //
    private ImageLoader imageLoader = ImageLoader.getInstance();

    public SettingWindow(Controller controller, MyButton exitButton) {
        this.controller = controller;
        this.exitButton = exitButton;
    }

    public void setData(BattleGround battleGround){
        this.battleGround = battleGround;

        initFrame();
        initComponent();
    }

    private void initFrame(){
        setSize(new Dimension(1200,700));
        setLayout(null);
        setVisible(true);
    }

    private void initComponent(){
        skinCard = new JButton("Skin Card");
        skinCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingSkinWindow(battleGround);
                controller.log("choosing card skin");
            }
        });

        theme = new JButton("Theme");
        theme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingThemeWindow(battleGround);
                controller.log("choosing battleField");
            }
        });

        announcer = new JCheckBox("Announcer");
        announcer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.log("changing announcer");
            }
        });

        sound = new JButton("Sound");
        sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SettingSoundWindow();
                controller.log("Changing sound volume");
            }
        });

        setComponent();
    }

    private void setComponent(){
        skinCard.setBounds((600)-75, 210,150,45);
        theme.setBounds((600)-75, 280,150,45);
        announcer.setBounds((600)-75, 350,150,45);
        sound.setBounds((600)-75, 420,150,45);

        add(skinCard);
        add(theme);
        add(announcer);
        add(sound);
        add(exitButton);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageLoader.getBackGroundImage(), 0, 0, getWidth(), getHeight(), imageLoader.getBackGroundImageObserver());
    }
}
