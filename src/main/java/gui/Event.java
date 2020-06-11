package gui;

import javax.swing.*;
import java.awt.*;

public class Event extends JPanel {
    private JTextArea textArea;

    public Event(){
        textArea = new JTextArea();
      //  setLayout();
        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }

    public void appendEvent(String event){
        textArea.append(event);
    }
}

