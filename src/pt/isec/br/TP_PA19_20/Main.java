package pt.isec.br.TP_PA19_20;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import pt.isec.br.TP_PA19_20.logic.data.DataGameObs;
import pt.isec.br.TP_PA19_20.resources.ImageLoader;
import pt.isec.br.TP_PA19_20.ui.gui.gui;


public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Planet Bound - A Virtual Space Exploration Game");
        primaryStage.getIcons().add(ImageLoader.loadImage("icon.png"));
        DataGameObs obs = new DataGameObs();
        gui ui = new gui(obs);
        Scene scene = new Scene(ui, 1500, 1000);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();

        //Alerta que mostra a informação inicial
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Hyperlink linkToGame = new Hyperlink("https://boardgamegeek.com/boardgame/298332/planet-bound");

        alert.setTitle("A message from the Dev :)");

        //Para mostrar o ícone
        Stage st = (Stage) alert.getDialogPane().getScene().getWindow();
        st.getIcons().add(ImageLoader.loadImage("icon.png"));

        FlowPane fp = new FlowPane();
        Label lblAlertLeft = new Label("This Game was created for a Practical Assignment of " +
                "my university's Advanced Programming class, based on the board game of the same name made by Joseph" +
                " Propati.\nYou can visit the original game's page here:\n");
        Label lblAlertRight = new Label("\nI sincerely hope you enjoy\n\nThis may not be distributed as there are uses of copyrighted material (not that you would" +
                " want to distribute this).");

        linkToGame.setOnAction( new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                getHostServices().showDocument(linkToGame.getText());
                linkToGame.setVisited(true);
            }
        } );

        fp.getChildren().addAll(lblAlertLeft, linkToGame, lblAlertRight);
        alert.setHeaderText("Thank you for playing Planet Bound!");
        alert.getDialogPane().contentProperty().set(fp);

        alert.showAndWait();
    }
}
