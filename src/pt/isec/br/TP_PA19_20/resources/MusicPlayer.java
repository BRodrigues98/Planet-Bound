package pt.isec.br.TP_PA19_20.resources;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MusicPlayer {
    static MediaPlayer mp;
    public static void playMusic(String name, boolean onRepeat) {
        String path = MusicPlayer.class.getResource("sounds/"+name).toExternalForm();
        //System.out.println("Path: "+path);
        Media music = new Media(path);
        mp = new MediaPlayer(music);
        mp.setStartTime(Duration.ZERO);
        mp.setStopTime(music.getDuration());
        mp.setVolume(0.25);
        if(onRepeat){
            mp.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    mp.seek(Duration.ZERO);
                }
            });
        }

        mp.setOnReady(() -> {
                mp.play();
        });
    }

    public static void changeVolume(double val) {
        mp.setVolume(val / 100);
    }
}
