package Logic;

import com.google.gson.Gson;

import java.io.*;

public class FileWorks {
    private File userListFile;
    private File Cardfile;
    private File Herofile;
    private File logFile;
    private File playerFile;
    private File eventFile;

    private Player player;
    private Serial serial;

    public FileWorks(){
        userListFile = new File("userlist\\userlist.txt");
        Cardfile = new File("Board\\Cards\\card.txt");
        Herofile = new File("Board\\Heros\\hero.txt");

        serial = new Serial();
    }

    public boolean setUsername(String username) {
        try {
            logFile = new File("Log\\" + username + "-file" + ".log");
            playerFile = new File("Board\\Players\\" + username + ".txt");
            eventFile = new File("Event\\" + username + "-file" + ".log");

            if (logFile == null ||
                playerFile == null ||
                eventFile == null) return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //////////////////////
    public boolean logInCheck(String username,String password){
        if (!existAccountCheck(username)) return false;
        setUsername(username);
        if (passwordCheck(password)) return true;
        return false;
    }

    public Player logIn(String username){
        setUsername(username);
        writeUserList(username);
        return readPlayerAccInfo();
    }

    public Player signUp(String username, String password){
        setUsername(username);
        writeUserList(username);
        writePlayerAccInfo(username, password);
        return player;
    }


    ///////////////////
    private void writeUserList(String username){
        try {
            FileWriter fw = new FileWriter(userListFile,true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(username);
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean existAccountCheck (String username) {
        try {
            FileReader fr = new FileReader(userListFile);
            BufferedReader br = new BufferedReader(fr);

            String line = "";
            while ((line = br.readLine()) != null){
                if (line.contains(username)) return true;
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean passwordCheck (String password){
        Player player;
        player = readPlayerAccInfo();
        if (player.getPassword().equals(password)) return true;
        return false;
    }

    private void writePlayerAccInfo(String username, String password){
        player = new Player();
        player.setUsername(username);
        player.setPassword(password); // ino hash kon
        try {
            FileWriter fw = new FileWriter(playerFile);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(serial.playerSeralize(player));
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePlayerAccInfo(Player player) {
        File file = new File("Board\\Players\\" + player.getUsername() + ".txt");
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(serial.playerSeralize(player));
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Player readPlayerAccInfo(){
        String player = "";
        try{
            FileReader fr = new FileReader(playerFile);
            BufferedReader br = new BufferedReader(fr);

            player = br.readLine();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return serial.playerDeserialize(player);
    }

    public void writeBoardInfo(){
        Board board = new Board();
        try {
            FileWriter fw = new FileWriter(Herofile);
            BufferedWriter bw = new BufferedWriter(fw);
            String s ="";
            s+=serial.heroSerialize(board.getHeros());
            bw.write(s);
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(Cardfile);
            BufferedWriter bw = new BufferedWriter(fw);
            String s ="";
            s+=serial.cardSerialize(board.getCards());
            bw.write(s);
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Board readBoardInfo(){
        String card = "";
        String hero = "";
        try{
            FileReader fr = new FileReader(Cardfile);
            BufferedReader br = new BufferedReader(fr);

            card = br.readLine();
            br.close();
            fr.close();

            FileReader frr = new FileReader(Herofile);
            BufferedReader brr = new BufferedReader(frr);

            hero = brr.readLine();
            brr.close();
            frr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Board board = new Board();
        board.setCards(serial.cardDeserialize(card));
        board.setHeros(serial.heroDeserialize(hero));

        return board;
    }

    public void writeLog(String string){
        try {
            FileWriter fw = new FileWriter(logFile,true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(string);
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


// inaro dorost kon fgt neveshtm k neveshte basham