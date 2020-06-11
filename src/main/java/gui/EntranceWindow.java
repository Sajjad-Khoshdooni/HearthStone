package gui;

import Config.*;
import Controller.Controller;
import Logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntranceWindow extends JPanel {
    private JTextField usernameLabel;
    private JTextField passwordLabel;
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton logIn;
    private JButton signUp;

    private Controller controller;
    private FileWorks fileWorks;
    private Player player;
    private boolean check = false;

    private Config conf = Config.getInstance();
    private ImageLoader imageLoader = ImageLoader.getInstance();

    public EntranceWindow(Controller controller ,FileWorks fileWorks){
        this.controller = controller;
        this.fileWorks = fileWorks;

        setLayout(null);
      //  setContentPane(new JLabel(new ImageIcon("2.jpg")));
        initComponent();
        initPanel();
    }

    private void initComponent(){
        usernameLabel = new JTextField("Username :");
        usernameLabel.setEditable(false);

        passwordLabel = new JTextField("Password :");
        passwordLabel.setEditable(false);

        usernameField = new JTextField(10);
        passwordField = new JTextField(10);
        logIn = new JButton("LogIn");
        signUp = new JButton("SignUp");

        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileWorks.logInCheck(usernameField.getText(),passwordField.getText())){
                    player = fileWorks.logIn(usernameField.getText());
                    check = true;
                    controller.log(player.getUsername(),"log in");
                }
                else {
                    new ErrorWindow("Wrong Info","Ok");
                    controller.log(usernameField.getText(),"log in Error");
                }

            }
        });

        signUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(fileWorks.existAccountCheck(usernameField.getText())){
                    new ErrorWindow("Username exists,try another one!!","Ok");
                    controller.log(usernameField.getText(),"sign up Error");
                }
                else {
                    player = fileWorks.signUp(usernameField.getText(),passwordField.getText());
                    check = true;
                    controller.log(player.getUsername(),"Sign up");
                }

            }
        });
    }

    private void initPanel(){
        setSize(1200,700);
        setVisible(true);

        setComponent();
        add(usernameLabel);
        add(passwordLabel);
        add(usernameField);
        add(passwordField);
        add(logIn);
        add(signUp);
    }

    private void setComponent(){
        usernameLabel.setBounds(505,275,90,30);
        usernameField.setBounds(600,275,90,30);
        passwordLabel.setBounds(505,315,90,30);
        passwordField.setBounds(600,315,90,30);
        logIn.setBounds(505,400,90,30);
        signUp.setBounds(606,400,90,30);
    }

    public String checkState(){
        if (check){
            return "success";
        }
        return "null";
    }

    public void trueCheck() {
        this.check = false;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageLoader.getBackGroundImage(), 0, 0, getWidth(), getHeight(), imageLoader.getBackGroundImageObserver());
    }
}
