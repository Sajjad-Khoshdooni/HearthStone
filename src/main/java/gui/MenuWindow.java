package gui;

import Config.Config;
import Config.ImageLoader;
import Controller.Controller;
import Logic.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import static Controller.GameState.menu;

public class MenuWindow extends JPanel{
    private JButton play;
    private JButton status;
    private JButton collection;
    private JButton setting;
    private JButton shop;

    private ImageLoader imageLoader = ImageLoader.getInstance();
    private Config conf = Config.getInstance();
    private MyButton exitButton;

    private boolean check = false;
    private String nextPanel;


    public MenuWindow(MyButton exitButton){
        this.exitButton = exitButton;
        //
        initComponent();
        initPanel();
    }

    private void initComponent() {
        play = new JButton("Play");
        status = new JButton("Status");
        collection = new JButton("Collection");
        setting = new JButton("Setting");
        shop = new JButton("Shop");


        // Buttons Actions
        play.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check = true;
                nextPanel = "play";
            }
        });

        status.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check = true;
                nextPanel = "status";
            }
        });

        collection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check = true;
                nextPanel = "collection";
            }
        });

        setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check = true;
                nextPanel = "setting";
            }
        });

        shop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                check = true;
                nextPanel = "shop";
            }
        });
        setComponent();
    }

    private void initPanel() {
        setSize(1200,700);
        setLayout(null);
        setVisible(true);
    }

    private void setComponent() {
        play.setBounds((600)-75, 210,150,45);
        status.setBounds((600)-75, 280,150,45);
        collection.setBounds((600)-75, 350,150,45);
        setting.setBounds((600)-75, 420,150,45);
        shop.setBounds((600)-75, 490,150,45);


        add(play);
        add(status);
        add(collection);
        add(setting);
        add(shop);
        add(exitButton);

    }


    public String checkState(){
        if (check){
            return nextPanel;
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
