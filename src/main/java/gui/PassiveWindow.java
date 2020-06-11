package gui;

import Logic.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import static gui.PassiveEnum.manaJump;
import static gui.PassiveEnum.twiceDraw;

public class PassiveWindow extends JFrame{
    private Set<PassiveEnum> enums;
    private Player player;

    private JLabel pass;


    public PassiveWindow(Player player){
        this.player = player;

        enums = new HashSet<>();


        passiveMaker();
        initFrame();
        initComponent();
    }

    private void initFrame(){
        setSize(600,400);
        setLayout(null);
        setTitle("Passive");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void initComponent(){
        pass = new JLabel("Choose your Passive");
        pass.setBounds(250,50,200,30);
        add(pass);

        int i=0;
        JButton button;
        System.out.println(enums);
        for (PassiveEnum passiveEnum:enums){
            button = new JButton(String.valueOf(passiveEnum));
            button.setBounds(130 + 110*i ,150,100,30);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    player.setPassiveEnum(passiveEnum);
                }
            });
            add(button);
            i++;

            revalidate();
            repaint();
        }
    }

    private void passiveMaker(){
        int num;
        for (int i=0 ; enums.size() < 3 ; i++) {
            num = new Random().nextInt()%7;
            switch (num) {
                case 0:
                    enums.add(PassiveEnum.twiceDraw);
                    break;

                case 1:
                    enums.add(PassiveEnum.offCards);
                    break;

                case 2:
                    enums.add(PassiveEnum.warriors);
                    break;

                case 3:
                    enums.add(PassiveEnum.nurse);
                    break;

                case 4:
                    enums.add(PassiveEnum.freePower);
                    break;

                case 5:
                    enums.add(manaJump);
                    break;

                case 6:
                    enums.add(PassiveEnum.zombies);
                    break;
            }
        }
    }
}