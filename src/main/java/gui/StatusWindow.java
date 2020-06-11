package gui;

import Controller.Controller;
import Logic.PlatoonCard;
import Logic.Player;
import Config.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StatusWindow extends JPanel {
    private JTextField searchField;
    private JButton searchButton;
    private JButton top10Button;
    private MyButton exitButton;

    private Player player;
    private List<PlatoonCard> platoonCardList;
    private List<PlatoonCard> top10PlatoonCardList;

    private List<JButton> top10PlatoonCardButtonList;

    private boolean check = false;
    //
    private Config conf = Config.getInstance();
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private Controller controller;

    public StatusWindow(Controller controller,MyButton exitButton){
        this.controller = controller;
        this.exitButton = exitButton;
    }

    public void setPlayer(Player player) {
        this.player = player;

        initPanel();
        initComponent();

        if (!player.getPlatoonCardList().isEmpty()) {
            platoonCardList.addAll(player.getPlatoonCardList());
            top10PlatoonCardList.addAll(player.getTop10PlatoonCards());
        }
    }

    private void initPanel(){
        setSize(new Dimension(1200,700));
        setLayout(null);
        setVisible(true);
    }

    private void initComponent(){
        platoonCardList = new ArrayList<>();
        top10PlatoonCardList = new ArrayList<>();
        top10PlatoonCardButtonList = new ArrayList<>();


        searchField = new JTextField();
        searchField.setBounds(405,50,90,30);
        add(searchField);

        searchButton = new JButton("Search");
        searchButton.setBounds(505,50,90,30);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.log("Searching platoon",searchField.getText());
                if (platoonCardList.isEmpty()) new ErrorWindow("No-Existing-PlatoonCard", "Ok");
                else {
                    for (PlatoonCard platoonCard : platoonCardList) {
                        if (platoonCard.getPlatoonName().toLowerCase().contains(searchField.getText().toLowerCase())
                                && !searchField.getText().isEmpty()) {
                            new StatusWindowInfo(player,platoonCard);
                        } else {
                            new ErrorWindow("No-Existing-PlatoonCard", "Ok");
                        }
                    }
                }
            }
        });
        add(searchButton);

        top10Button = new JButton("Top 10 Platoons");
        top10Button.setBounds(605,50,90,30);
        top10Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.log("Checking top10 platoons");
                if (!top10PlatoonCardList.isEmpty()) initTop10PlatoonCards();
                else new ErrorWindow("No-Existing-PlatoonCard","Ok");
            }
        });
        add(top10Button);

        add(exitButton);
    }

    private void initTop10PlatoonCards(){
        top10PlatoonCardButtonList.clear();

        JButton button = null;
        for (PlatoonCard pl: top10PlatoonCardList){
            button = new JButton(pl.getPlatoonName());
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new StatusWindowInfo(player,pl);
                }
            });
            top10PlatoonCardButtonList.add(button);
        }
        new Bounds().platoonCard(top10PlatoonCardButtonList);

        for (JButton button1:top10PlatoonCardButtonList){
            add(button1);
        }

        repaint();
        revalidate();
    }

    public String checkState(){
        //update frame
        revalidate();
        if (check) return "success";
        return "null";
    }

    public void trueCheck() {
        this.check = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageLoader.getBackGroundImage(), 0, 0, getWidth(), getHeight(),
                imageLoader.getBackGroundImageObserver());
    }
}
