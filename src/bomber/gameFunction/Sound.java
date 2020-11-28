package bomber.gameFunction;


import javafx.scene.media.*;
import javafx.scene.shape.Path;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

public class Sound  {

    public static void playSound(String fileName) {
        String path = (System.getProperty("user.dir") + "\\src\\sound\\" + fileName + ".wav");

        try {
            File file = new File(path);
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip.open(inputStream);
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void no(){//nổ
        playSound("Mortar_Explode_Incendiary_1a-resources.assets-3848");
    }

    public static void playerDead() {
        playSound("Spawn-resources.assets-2428");
    }

    public static void getItem() {
        playSound("MetalHit-resources.assets-3447");
    }

    public static void enemyDead1() {
        playSound("Pawn_Megascarab_Wounded_1c-resources.assets-3996");
    }

}