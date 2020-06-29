package pt.isec.br.TP_PA19_20.ui.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.isec.br.TP_PA19_20.logic.data.DataGameObs;
import pt.isec.br.TP_PA19_20.resources.MusicPlayer;

import java.io.File;


public class gui extends BorderPane {
    private         DataGameObs dgObs;
    private         StackPane   usefulArea          = new StackPane();
    private         MenuBar     menuBar             = new MenuBar();
    private         FileChooser fileChooser         = new FileChooser();
    private final   String      defaultRightText    = "This area will show your ship condition and your resources";


    public gui(DataGameObs dgObsN) {
        this.dgObs = dgObsN;

        UIAwaitSpaceshipSelection uiass = new UIAwaitSpaceshipSelection(dgObs);
        UIAwaitMovement uiam = new UIAwaitMovement(dgObs);
        UIAwaitPlanetDecision uiapd = new UIAwaitPlanetDecision(dgObs);
        UIAwaitMiningConfirmation uiamc = new UIAwaitMiningConfirmation(dgObs);
        UIAwaitResourcesConversion uiarc = new UIAwaitResourcesConversion(dgObs);
        UIAwaitDiceRoll uiadr = new UIAwaitDiceRoll(dgObs);
        UIAwaitSSDecision uiassd = new UIAwaitSSDecision(dgObs);
        UILastChance uilc = new UILastChance(dgObs);
        UIGameOver uigo = new UIGameOver(dgObs);


        DataViewLeft dvL = new DataViewLeft(dgObs);
        DataViewRight dvR = new DataViewRight(dgObs);
        DataViewBottom dvB = new DataViewBottom(dgObs);

        uiam.setVisible(false);
        uiapd.setVisible(false);
        uiamc.setVisible(false);
        uiarc.setVisible(false);
        uiadr.setVisible(false);
        uiassd.setVisible(false);
        uilc.setVisible(false);
        uigo.setVisible(false);


        uiam.setManaged(false);
        uiapd.setManaged(false);
        uiamc.setManaged(false);
        uiarc.setManaged(false);
        uiadr.setManaged(false);
        uiassd.setManaged(false);
        uilc.setManaged(false);
        uigo.setManaged(false);

        usefulArea.setStyle("-fx-padding: 5;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 2;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: gray;");

        usefulArea.getChildren().addAll(dvL, dvR, dvB, uiass, uiam, uiapd, uiamc, uiarc, uiadr, uiassd, uilc, uigo);

        MusicPlayer.playMusic("lol.mp3", true);

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
        Slider slider = new Slider(0, 100, 10);
        slider.setOrientation(Orientation.VERTICAL);
        customMenuItem.setContent(slider);
        customMenuItem.setHideOnClick(false);
        volume.getItems().add(customMenuItem);

        menuBar.getMenus().addAll(fileMenu, volume);

        //Eventos dos menus
        //File -> New
        fileNew.setOnAction((ActionEvent e) -> {
            dgObs.setInstruction(dgObs.getStateID().toString());
            dgObs.setShipText(defaultRightText);
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

            dgObs.setShipText(dgObs.getShipText());
            dgObs.setInstruction(dgObs.getStateID().toString());
        });

        // File -> Exit
        fileExit.setOnAction((ActionEvent e) -> {
            ((Stage) getScene().getWindow()).close();
        });

        //Volume
        volume.setOnShowing(e -> {

            slider.valueProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    MusicPlayer.changeVolume(t1.doubleValue());
                }
            });


        });

        setCenter(usefulArea);
        setLeft(leftSide);
        setBottom(bottomSide);
        setTop(menuBar);
        setRight(rightSide);
    }
}
