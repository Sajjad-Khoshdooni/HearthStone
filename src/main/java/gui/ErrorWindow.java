package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorWindow extends JFrame {
    JLabel errorMessage;
    JButton okBtn;
    JButton okBtn2;

    public ErrorWindow(String errorText,String buttonText){
        initComponent(errorText,buttonText);
        initFrame();
    }

    public ErrorWindow(String errorText,String firstButtonText,String secondButtonText){
        initComponent(errorText,firstButtonText,secondButtonText);
        initFrame();
    }

    private void initComponent(String errorText,String buttonText){
        errorMessage = new JLabel(errorText);

        okBtn = new JButton(buttonText);
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(errorMessage);
        add(okBtn);
    }

    private void initComponent(String errorText,String firstButtonText,String secondButtonText){
        errorMessage = new JLabel(errorText);

        okBtn = new JButton(firstButtonText);
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        okBtn2 = new JButton(secondButtonText);
        okBtn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        add(errorMessage);
        add(okBtn);
        add(okBtn2);
    }

    private void initFrame(){
        setLayout(null);
        setComponent();
        setSize(new Dimension(300,200));
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void setComponent() {
        setLayout(null);
        errorMessage.setBounds(40,50,200,30);
        okBtn.setBounds(50,90,90,30);
        if (okBtn2 != null) okBtn2.setBounds(95,140,90,30);
    }
}
