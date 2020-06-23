package pt.isec.br.TP_PA19_20.ui.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.isec.br.TP_PA19_20.logic.data.DataGameObs;
import pt.isec.br.TP_PA19_20.resources.MusicPlayer;

import java.io.File;


public class gui extends BorderPane {
    DataGameObs dgObs;
    StackPane usefulArea = new StackPane();
    //Text bottomText = new Text();
    MenuBar menuBar = new MenuBar();
    FileChooser fileChooser = new FileChooser();


    public gui(DataGameObs dgObsN) {
        this.dgObs = dgObsN;

        UIAwaitSpaceshipSelection uiss = new UIAwaitSpaceshipSelection(dgObs);
        UIAwaitMovement uiam = new UIAwaitMovement(dgObs);
        UIAwaitPlanetDecision uiapd = new UIAwaitPlanetDecision(dgObs);

        DataViewLeft dvL = new DataViewLeft(dgObs);
        DataViewRight dvR = new DataViewRight(dgObs);
        DataViewBottom dvB = new DataViewBottom(dgObs);

        uiam.setVisible(false);
        uiapd.setVisible(false);

        MusicPlayer.playMusic("lol.mp3", true);

        usefulArea.setStyle("-fx-padding: 5;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 2;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: gray;");



        usefulArea.getChildren().addAll(uiss, uiam, uiapd, dvL, dvR, dvB);


        //Lado esquerdo
        VBox leftSide = new VBox(dvL);
        leftSide.setStyle("-fx-padding: 5;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 2;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: gray;");

        //Lado direito
        VBox rightSide = new VBox(dvR);
        rightSide.setStyle("-fx-padding: 5;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 2;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: gray;");

        //Bottom - onde vão aparecer as mensagens com o que fazer
        HBox bottomSide = new HBox(dvB);
        bottomSide.setStyle("-fx-padding: 5;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 2;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: gray;");



        //Menu que vai ficar no topo
        Menu fileMenu = new Menu("File");
        MenuItem fileNew = new MenuItem("New");
        MenuItem fileSave = new MenuItem("Save");
        MenuItem fileLoad = new MenuItem("Load");
        MenuItem fileExit = new MenuItem("Exit");

        fileMenu.getItems().addAll(fileNew, fileSave, fileLoad, fileExit);

        Menu volume = new Menu("Volume");
        CustomMenuItem customMenuItem = new CustomMenuItem();
        Slider slider = new Slider(0, 100, 50);
        slider.setOrientation(Orientation.VERTICAL);
        customMenuItem.setContent(slider);
        customMenuItem.setHideOnClick(false);
        volume.getItems().add(customMenuItem);

        menuBar.getMenus().addAll(fileMenu, volume);

        //Eventos dos menus
        //File -> New
        fileNew.setOnAction((ActionEvent e) -> {
            dgObs.restart();
        });

        // File -> Save
        fileSave.setOnAction((ActionEvent e) -> {
            File f = fileChooser.showSaveDialog(getScene().getWindow());
            if (f != null)
                dgObs.saveGame(f.getAbsolutePath());
        });

        // File -> Load
        fileLoad.setOnAction((ActionEvent e) -> {
            File f = fileChooser.showOpenDialog(getScene().getWindow());
            if (f != null)
                dgObs.loadGame(f.getAbsolutePath());
        });

        // File -> Exit
        fileExit.setOnAction((ActionEvent e) -> {
            ((Stage) getScene().getWindow()).close();
        });

        //Volume
        volume.setOnAction((ActionEvent e) -> {

            slider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    MusicPlayer.changeVolume(t1.doubleValue());
                }
            });


        });

        //bottomSide.getChildren().addAll(bottomText);




        setCenter(usefulArea);
        setLeft(leftSide);
        setBottom(bottomSide);
        setTop(menuBar);
        setRight(rightSide);
    }
}
