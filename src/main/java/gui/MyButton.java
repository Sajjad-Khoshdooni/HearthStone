package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class MyButton extends JButton {
    Image image;
    ImageObserver imageObserver;


    public MyButton(String name) {
        super();
        setText(name);
        ImageIcon icon = new ImageIcon(pathMaker(name));
        image = icon.getImage();
        imageObserver = icon.getImageObserver();
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), imageObserver);
    }

    private String pathMaker(String name){
        String str ;
        str = "Images\\CardImage\\"+name.toLowerCase()+".png";
        return str;
    }
}
