package model.sounds;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

import static controller.Variables.volume;

public abstract class Sounds {

    public static void  damageSound(){
        try {
            File file = new File("damage.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue((int)(volume / 1.2) - 80);
            clip.loop(0);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void  collapseSound(){
        try {
            File file = new File("collapse.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue((int)(volume / 1.2) - 80);
            clip.loop(0);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void  enemySpawnSound(){
        try {
            File file = new File("spawnSound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue((int)(volume / 1.2) - 80);
            clip.loop(0);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void waveSound(){
        try {
            File file = new File("waveEndSound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue((int)(volume / 1.2) - 80);
            clip.loop(0);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void gameOverSound(){
        try {
            File file = new File("gameOverSound.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue((int)(volume / 1.2) - 80);
            clip.loop(0);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
