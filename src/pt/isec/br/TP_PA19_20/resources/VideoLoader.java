package pt.isec.br.TP_PA19_20.resources;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class VideoLoader {
    static MediaPlayer mp;

    public static MediaPlayer playVideo(String nome) {
        String path = VideoLoader.class.getResource(nome).toExternalForm();
        Media video = new Media(path);
        mp = new MediaPlayer(video);
        mp.setAutoPlay(true);
        mp.setVolume(0);

        mp.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                mp.seek(Duration.ZERO);
            }
        });
        return mp;
    }
}
