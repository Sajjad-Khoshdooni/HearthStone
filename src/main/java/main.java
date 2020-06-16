import Controller.Controller;
import Logic.FileWorks;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException, UnsupportedAudioFileException {
        Controller controller = new Controller();
        Thread mainThread = new Thread(controller);
        mainThread.start();
    }
}
