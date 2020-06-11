package Config;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    /**
     * singletonesh kon
     */
    private Clip mainClip;

    private Clip battle1Clip;
    private AudioInputStream battle1audioInputStream;
    private String battle1Path;

    private Clip menuClip;
    private AudioInputStream menuAudioInputStream;
    private String menuPath;

    //
    private static SoundPlayer soundPlayer;

    static {
        try {
            soundPlayer = new SoundPlayer();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    private SoundPlayer() throws IOException, UnsupportedAudioFileException {
        battle1Path = "sounds\\battle1.wav";
        menuPath = "sounds\\battle2.wav";

        try {
            battle1audioInputStream = AudioSystem.getAudioInputStream(new File(battle1Path).getAbsoluteFile());
            battle1Clip = AudioSystem.getClip();
            battle1Clip.open(battle1audioInputStream);

            menuAudioInputStream = AudioSystem.getAudioInputStream(new File(menuPath).getAbsoluteFile());
            menuClip = AudioSystem.getClip();
            menuClip.open(menuAudioInputStream);

        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static SoundPlayer getInstance() {
        return soundPlayer;
    }

    public void playSound(String str){
        switch (str.toLowerCase()){
            case "battle":
                if (mainClip != null) pause();
                mainClip = battle1Clip;
                play();
                break;

            case "menu":
                if (mainClip != null) pause();
                mainClip = menuClip;
                play();
                break;
        }
    }

    public void setVolume(int vol){
        switch (vol){
            case 0:
                volume(0.00);
                return;
            case 1:
                volume(0.25);
                return;
            case 2:
                volume(0.50);
                return;
            case 3:
                volume(0.75);
                return;
            case 4:
                volume(1.00);
                return;
        }
    }

    private void volume(double gain){
        FloatControl gainControl = (FloatControl) mainClip.getControl(FloatControl.Type.MASTER_GAIN);
        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        gainControl.setValue(dB);
    }

    private void play() {
        mainClip.start();
        mainClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void pause() {
        mainClip.stop();
    }
}

