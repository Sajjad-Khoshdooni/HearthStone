package Controller;

import Config.*;
import Logic.*;
import gui.*;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Controller.GameState.*;

public class Controller extends JFrame implements Runnable{
    private Config conf = Config.getInstance();
    private SoundPlayer soundPlayer = SoundPlayer.getInstance();
    private Board board;
    private Player homePlayer;
    private FileWorks fileWorks;
    private Log getLog;

    //Game Panels
    private EntranceWindow entranceWindow;
    private MenuWindow menuWindow;
    private ShopWindow shopWindow;
    private CollectionWindow collectionWindow;
    private StatusWindow statusWindow;
    private PlayWindow playWindow;
    private SettingWindow settingWindow;

    //Game Logic
    private BattleGround battleGround;
    private Shop shop;

    // Variable
    private GameState gameState = entrance;
    private boolean start = true;

    //
    private MyButton exitButton;

    public Controller(){
        fileWorks = new FileWorks();

        initFrame();
        init(this);

    }

    private void initFrame(){

        setSize(conf.getData("framex"),conf.getData("framey"));
        setLayout(null);
        setTitle("HearthStone");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void init(Controller controller){
        exitButton = new MyButton("exit");
        exitButton.setBounds(conf.getData("exitbuttonx"),
                conf.getData("exitbuttony"),
                conf.getData("exitbuttonsize"),
                conf.getData("exitbuttonsize"));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameState == menu){
                    new EndGameFrame(homePlayer, controller,soundPlayer);
                    log(homePlayer.getUsername()+" exit game");
                }
                else {
                    getContentPane().removeAll();
                    start = true;
                    menuWindow = new MenuWindow(exitButton);
                    gameState = menu;
                    gameState();

                    repaint();
                    revalidate();

                    fileWorks.writePlayerAccInfo(homePlayer);
                }
            }
        });

        board = new Board();
        entranceWindow = new EntranceWindow(this,fileWorks);
        menuWindow = new MenuWindow(exitButton);
        shopWindow = new ShopWindow(this,exitButton);
        collectionWindow = new CollectionWindow(this,exitButton);
        statusWindow = new StatusWindow(this,exitButton);
        playWindow = new PlayWindow(this,exitButton);
        settingWindow = new SettingWindow(this,exitButton);

        battleGround = new BattleGround();
        shop = new Shop();
        getLog = new Log();

        homePlayer = new Player();
//        homePlayer.setUsername("asdsgkjhjhjh123415246346");
//        homePlayer.setPassword("79375773057");
//        homePlayer.initPlatoonCard("asd",board.getHeros().get(3));
//        for (int i=0 ;i<20; i++){
//            homePlayer.getPlatoonCardList().get(0).addCards(board.getCards().get(i));
//            homePlayer.addEntireCardList(board.getCards().get(i));
//        }
//
//        homePlayer.setGamePlatoonCard(homePlayer.getPlatoonCardList().get(0));
//        fileWorks.setUsername(homePlayer.getUsername());
    }

    private void gameState(){

        switch (gameState){
            case entrance:
                entranceSetup();
                break;

            case menu:
                menuSetup();
                break;

            case shop:
                shopSetup();
                break;

            case play:
                playSetup();
                break;

            case collection:
                collectionSetup();
                break;

            case status:
                statusSetup();
                break;

            case exit:
                exitSetup();
                break;

            case setting:
                settingSetup();
                break;
        }
    }

    private void entranceSetup(){
        if (entranceWindow.checkState().equals("null") && start) {
            start = false;
            setContentPane(entranceWindow);
        }
        else if (entranceWindow.checkState().equals("success")){
            start = true;
            this.homePlayer = entranceWindow.getPlayer();
            fileWorks.setUsername(homePlayer.getUsername());
            getContentPane().removeAll();
            gameState = menu;
            entranceWindow.trueCheck();
        }
    }

    private void menuSetup(){
        if (menuWindow.checkState().equals("null") && start) {
            soundPlayer.playSound("menu");
            start = false;
            setContentPane(menuWindow);
            log("open menu window");
        }
        else if (!menuWindow.checkState().equals("null")) {
            start = true;
            getContentPane().removeAll();
            gameState = GameState.valueOf(menuWindow.checkState());
            menuWindow.trueCheck();
            gameState();
        }
    }

    private void shopSetup(){
        if (shopWindow.checkState().equals("null") && start) {
            start = false;
            shopWindow.setData(board,homePlayer);
            setContentPane(shopWindow);
            log("open shop window");
        }
        else if (shopWindow.checkState().equals("success")){
            start = true;
            getContentPane().removeAll();
            gameState = menu;
            shopWindow.trueCheck();
            gameState();
        }
    }

    private void statusSetup(){
        if (statusWindow.checkState().equals("null") && start) {
            start = false;
            statusWindow.setPlayer(homePlayer);
            setContentPane(statusWindow);
            log("open status window");
        }
        else if (statusWindow.checkState().equals("success")) {
            start = true;
            getContentPane().removeAll();
            gameState = menu;
            statusWindow.trueCheck();
            gameState();
        }
    }

    private void collectionSetup(){
        if (collectionWindow.checkState().equals("null") && start) {
            collectionWindow.setData(board, homePlayer);
            start = false;
            setContentPane(collectionWindow);
            log("open collection Window");
        }
        else if (collectionWindow.checkState().equals("success")){
            start = true;
            getContentPane().removeAll();
            gameState = menu;
            collectionWindow.trueCheck();
            gameState();
        }
    }

    private void playSetup(){
        if (homePlayer.getGamePlatoonCard() == null) {
            gameState = collection;
            new ErrorWindow("Please create/choose Platoon","Ok");
            gameState();
            log("open play Window");
        }
        else if (playWindow.checkState().equals("null") && start) {
            soundPlayer.playSound("battle");
            start = false;
            playWindow.setPlayer(homePlayer);
            setContentPane(playWindow);
        }
        else if (playWindow.checkState().equals("success")){
            start = true;
            getContentPane().removeAll();
            gameState = menu;
            playWindow.trueCheck();
            gameState();
        }
    }

    private void settingSetup(){
        if (settingWindow.checkState().equals("null") && start) {
            settingWindow.setData(battleGround);
            start = false;
            setContentPane(settingWindow);
            log("open setting window");
        }
        else if (settingWindow.checkState().equals("success")){
            start = true;
            getContentPane().removeAll();
            gameState = setting;
            settingWindow.trueCheck();
            gameState();
        }
    }

    public void log(String firstLog, String secondLog){
        fileWorks.writeLog(getLog.getlog(firstLog, secondLog));
    }

    public void log(String firstLog){
        fileWorks.writeLog(getLog.getlog(firstLog, ""));
    }

    private void exitSetup(){
        setVisible(false);
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public void run() {
        while (gameState == end){
            gameState();
        }
    }
}
