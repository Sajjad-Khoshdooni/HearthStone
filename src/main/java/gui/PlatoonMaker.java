package gui;

import Logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PlatoonMaker extends JFrame{
    private JLabel nameLabel;
    private JTextField nameField;
    private JComboBox heroCombo;
    private JButton generate;
    private JButton cancel;
    private JButton edit;
    private PlatoonCard platoonCard;
    private JButton choose;

    /**
     * esm kamel ya nesfe cart ha
     */
    private JTextField textField;
    private JButton editCard;
    private List<String> cards;


    ///
    private Board board;
    private Player player;
    private CollectionWindow collectionWindow;

    public PlatoonMaker(){
    }

    public void setData(Board board, Player player,CollectionWindow collectionWindow){
        this.board = board;
        this.player = player;
        this.collectionWindow = collectionWindow;

        initPanel();
        initComponent();
        initGenerate();
        setComponent();
    }

    public void setData(Board board, Player player, PlatoonCard platoonCard,CollectionWindow collectionWindow){
        this.board = board;
        this.player = player;
        this.platoonCard = platoonCard;
        this.collectionWindow = collectionWindow;

        initPanel();
        initComponent();
        initEdit();
        setComponent();
    }

    private void initPanel(){
        setSize(new Dimension(600,450));
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void initGenerate(){
        generate = new JButton("Generate");
        generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (!name.isEmpty()){
                    for (Hero hero:board.getHeros()){
                        if (hero.getHeroName().equals(heroCombo.getSelectedItem().toString())){
                            PlatoonCard pl = player.initPlatoonCard(name,hero);
                            editCards(pl);
                            collectionWindow.update();
                            setVisible(false);
                            break;
                        }
                    }
                }
                else new ErrorWindow("Please write name","Ok");
            }
        });

    }

    private void initEdit(){
        edit = new JButton("Edit");
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (!name.isEmpty()){
                    for (Hero hero:board.getHeros()){
                        if (hero.getHeroName().equals(heroCombo.getSelectedItem())){
                            platoonCard.setPlatoonName(name);
                            heroCheck(heroCombo.getSelectedItem().toString());
                            editCards(platoonCard);
                            setVisible(false);
                            collectionWindow.update();
                            break;
                        }
                    }
                }
                else new ErrorWindow("Please write name","Ok");
            }
        });

    }

    private void initComponent(){
        cards = new ArrayList<>();
        nameLabel = new JLabel("Platoon Name");
        heroCombo = new JComboBox();
        DefaultComboBoxModel empModel = new DefaultComboBoxModel();
        for (Hero hero:board.getHeros()){
            empModel.addElement(hero.getHeroName());
        }
        heroCombo.setModel(empModel);
        heroCombo.setEditable(false);

        if (platoonCard != null){
            nameField = new JTextField(platoonCard.getPlatoonName());
        }
        else {
            nameField = new JTextField();
            heroCombo.setSelectedIndex(0);
        }

        textField = new JTextField();
        editCard = new JButton("Edit Card");
        editCard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cards.add(textField.getText());
            }
        });

        cancel = new JButton("Cancel");
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        choose = new JButton("Choose as Game Platoon");
        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.setGamePlatoonCard(platoonCard);
                setVisible(false);
            }
        });
    }

    private void setComponent(){
        nameLabel.setBounds(205, 100,90, 30);
        nameField.setBounds(305,100,90, 30);
        heroCombo.setBounds(255,140,90, 30);
        textField.setBounds(205,180,90,30);
        editCard.setBounds(305,180,90,30);
        cancel.setBounds(305,220,90, 30);
        choose.setBounds(205,270,200,30);

        add(nameLabel);
        add(nameField);
        add(heroCombo);
        add(textField);
        add(editCard);
        add(cancel);
        if (platoonCard != null) add(choose);

        if (generate != null){
            generate.setBounds(205,220,90, 30);
            add(generate);
        }
        if (edit != null){
            edit.setBounds(205,220,90, 30);
            add(edit);
        }
    }

    private void editCards(PlatoonCard platoonCard){
        for (Card card : platoonCard.getCards()) {
            for (String name : cards) {
                if (card.getCardName().toLowerCase().contains(name.toLowerCase())) {
                    platoonCard.removeCards(card);
                    cards.remove(name);
                }
            }
        }
        for (Card card : player.getEntireCardList()) {
            for (String name : cards) {
                if (card.getCardName().toLowerCase().contains(name.toLowerCase())) {
                    platoonCard.addCards(card);
                }
            }
        }
    }

    private void heroCheck(String heroName){
        for (Card card:platoonCard.getCards()){
            if (card.getCardClass().toLowerCase().equals(platoonCard.getHero().getHeroName().toLowerCase())) {
                new ErrorWindow("You cant change hero","Ok");
                return;
            }
        }

        for (Hero hero:board.getHeros()){
            if (hero.getHeroName().equals(heroName)) platoonCard.setHero(hero);
        }
    }
}

