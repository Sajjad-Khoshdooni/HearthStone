package gui;

import Config.ImageLoader;
import Logic.BattleGround;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingThemeWindow extends JFrame{
    private BattleGround battleGround;

    public SettingThemeWindow(BattleGround battleGround){
        this.battleGround = battleGround;

        initFrame();
        initComponent(battleGround);
    }

    private void initFrame(){
        setLayout(null);
        setSize(new Dimension(600,300));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponent(BattleGround battleGround){
        JLabel label = new JLabel("Choose theme");
        label.setBounds(250,30,100,30);
        add(label);

        MyButton button;
        for (int i=0 ; i<2 ; i++){
            button = new MyButton("battle"+String.valueOf(i+1));
            button.setBounds(105 + i*200,100,190,120);
            final int ii = i+1;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    battleGround.setBattleGroundNumber(ii);
                    setVisible(false);
                }
            });
            add(button);
        }
    }
}
