package gui;

import Config.ImageLoader;
import Logic.BattleGround;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingSkinWindow extends JFrame{
    private BattleGround battleGround;
    private ImageLoader imageLoader = ImageLoader.getInstance();

    public SettingSkinWindow(BattleGround battleGround){
        this.battleGround = battleGround;

        initFrame();
        initComponent(battleGround);
    }

    private void initFrame(){
        setLayout(null);
        setSize(new Dimension(600,400));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponent(BattleGround battleGround){
        JLabel label = new JLabel("Choose skin");
        label.setBounds(250,30,100,30);
        add(label);

        MyButton button;
        for (int i=0 ; i<5 ; i++){
            button = new MyButton("skin"+String.valueOf(i));
            button.setBounds(120 + i * 70,100 ,60,95);
            final int ii = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    battleGround.setCardSkinPath("skin"+String.valueOf(ii));
                    setVisible(false);
                }
            });
            add(button);
        }
    }


    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        g.drawImage(imageLoader.getBackGroundImage(), 0, 0, getWidth(), getHeight(),
                imageLoader.getBackGroundImageObserver());
    }
}
