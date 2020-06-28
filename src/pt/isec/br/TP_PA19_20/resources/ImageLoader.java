package pt.isec.br.TP_PA19_20.resources;

import javafx.scene.image.Image;

public class ImageLoader {

    public static Image loadImage(String name) {
        try {
            return new Image(ImageLoader.class.getResourceAsStream("images/"+name));
        } catch (Exception e) {
            System.out.println("NOT FOUND: " + name);
            return null;
        }
    }
}