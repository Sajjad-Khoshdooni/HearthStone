package gui;

import Config.*;

import javax.swing.*;
import java.util.List;

public class Bounds extends JFrame {
    private Config conf = Config.getInstance();

    /**
     * check kon bbin hameja doroste andazeha
     * @param buttons
     */
    public void table4o4(List<MyButton> buttons){
        try {
            for (int i = 0; i < 12 && i < buttons.size(); i++) {
                buttons.get(i).setBounds(300 + (i % 4) * 150, 130 + (i / 4) * 120,
                        conf.getData("cardx"),
                        conf.getData("cardy"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handCard12(List<MyButton> buttons){
        try {
            for (int i = 0; i < 12 && i < buttons.size(); i++) {
                buttons.get(i).setBounds((i % 3) * (conf.getData("cardx")+1),
                        (i / 3) * (conf.getData("cardy")+1),
                        conf.getData("cardx"),
                        conf.getData("cardy"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void homePlayerOnBoard(List<MyButton> buttons){
        try {
            for (int i = 0; i < 7 && i < buttons.size(); i++) {
                buttons.get(i).setBounds(470 + i * 70 , 360,
                        conf.getData("cardx"),
                        conf.getData("cardy"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void numberButtons(List<JButton> buttons){
        for (int i=0 ; i<buttons.size(); i++){
            buttons.get(i).setBounds(400 + i * 100, 600,
                    conf.getData("buttonx"),
                    conf.getData("buttony"));
        }
    }

    public void platoonCard(List<JButton> buttons){
        for (int i=0 ; i<buttons.size() ; i++){
            buttons.get(i).setBounds(505 + 100*(i%2),
                    200 + 40*(i/2),
                    conf.getData("buttonx"),
                    conf.getData("buttony"));
        }
    }
}

