package gui;

import Config.ImageLoader;
import Config.SoundPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingSoundWindow extends JFrame {
    private SoundPlayer soundPlayer = SoundPlayer.getInstance();
    private ImageLoader imageLoader = ImageLoader.getInstance();

    public SettingSoundWindow(){
        initFrame();
        initComponent();
    }

    private void initFrame(){
        setLayout(null);
        setSize(new Dimension(600,200));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponent(){
        JLabel label = new JLabel("Choose level");
        label.setBounds(130,30,100,30);
        add(label);

        JButton button;
        for (int i=0 ; i<5 ; i++){
            button = new JButton(String.valueOf(i));
            button.setBounds(130 + i*70,100,60,30);
            final int ii =i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    soundPlayer.setVolume(ii);
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
