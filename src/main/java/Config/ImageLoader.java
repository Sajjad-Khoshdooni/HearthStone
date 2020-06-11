package Config;


import Logic.Card;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class ImageLoader {
    private ImageIcon icon = new ImageIcon("Images\\IconImage\\background.jpg");
    private ImageIcon battleGround1 = new ImageIcon("Images\\CardImage\\battle1.png");
    private ImageIcon battleGround2 = new ImageIcon("Images\\CardImage\\battle2.png");
    private ImageIcon diamonds = new ImageIcon();

    private ImageLoader(){}

    private static ImageLoader imageLoader = new ImageLoader();

    public static ImageLoader getInstance() {
        return imageLoader;
    }

    public Image getBackGroundImage(){
        return icon.getImage();
    }

    public ImageObserver getBackGroundImageObserver(){
        return icon.getImageObserver();
    }

    public Image getBattleGroundImage(int a){
        switch (a){
            case 1:
                return battleGround1.getImage();
            case 2:
                return battleGround2.getImage();
        }
        return battleGround1.getImage();
    }

    public ImageObserver getBattleGroundImageObserver(int a){
        switch (a){
            case 1:
                return battleGround1.getImageObserver();
            case 2:
                return battleGround2.getImageObserver();
        }
        return battleGround1.getImageObserver();
    }

    public Image getImage(String imageName){
        return new ImageIcon("Images\\CardImage\\"+imageName+".png").getImage();
    }

    public ImageObserver getImageObserver(String imageName){
        return new ImageIcon("Images\\CardImage\\"+imageName+".png").getImageObserver();
    }
}

