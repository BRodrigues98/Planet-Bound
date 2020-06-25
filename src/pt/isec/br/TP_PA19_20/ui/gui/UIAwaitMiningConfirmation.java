package pt.isec.br.TP_PA19_20.ui.gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.integration.Type;
import pt.isec.br.TP_PA19_20.logic.data.DataGameObs;
import pt.isec.br.TP_PA19_20.resources.ImageLoader;


import javax.tools.Tool;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UIAwaitMiningConfirmation extends HBox {
    private final DataGameObs dgObs;
    private HBox hbNextPlanet = new HBox();
    private ImageView nextPlanetImg = new ImageView();
    private Tooltip ttNextPlanet = new Tooltip("Advance in space");
    
    private VBox vbArrow = new VBox();
    private ImageView arrowConvertImg = new ImageView();
    private Tooltip ttArrow = new Tooltip("Convert resources");

    public UIAwaitMiningConfirmation(DataGameObs dgObsN) {
        dgObs = dgObsN;
        dgObs.registerPropertyChangeListener(Type.STATE, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                updateView();
            }
        });
        organizeComponents();
        updateView();
    }

    private void updateView() {
        StateID stateID = dgObs.getStateID();
        setVisible(stateID == StateID.AWAIT_MINING_CONFIRMATION);
        setManaged(stateID == StateID.AWAIT_MINING_CONFIRMATION);

        nextPlanetImg.setImage(ImageLoader.loadImage("proceed.png"));
        arrowConvertImg.setImage(ImageLoader.loadImage("arrow.png"));

        nextPlanetImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dgObs.nextTurn();
            }
        });
    }

    private void organizeComponents() {

        BackgroundImage bgImg = new BackgroundImage(ImageLoader.loadImage("cockpit.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background bg = new Background(bgImg);
        setBackground(bg);

        
        hbNextPlanet.setPadding(new Insets(560, 0, 0, 120));
        nextPlanetImg.setPreserveRatio(true);
        nextPlanetImg.setFitWidth(700);
        ttNextPlanet.setShowDelay(Duration.ZERO);
        Tooltip.install(nextPlanetImg, ttNextPlanet);

        nextPlanetImg.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                nextPlanetImg.setOpacity(0.75f);
            }
        });

        nextPlanetImg.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                nextPlanetImg.setOpacity(1f);
            }
        });

        nextPlanetImg.setOnMouseMoved((MouseEvent f)->{
            ttNextPlanet.setX(f.getScreenX());
            ttNextPlanet.setY(f.getScreenY());
        });

        hbNextPlanet.getChildren().add(nextPlanetImg);
        
        vbArrow.setPadding(new Insets(400, 0, 0, 0));
        arrowConvertImg.setPreserveRatio(true);
        arrowConvertImg.setFitWidth(100);
        ttArrow.setShowDelay(Duration.ZERO);
        Tooltip.install(arrowConvertImg, ttArrow);

        arrowConvertImg.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                arrowConvertImg.setOpacity(0.75f);
            }
        });

        arrowConvertImg.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                arrowConvertImg.setOpacity(1f);
            }
        });

        arrowConvertImg.setOnMouseMoved((MouseEvent f)->{
            ttArrow.setX(f.getScreenX());
            ttArrow.setY(f.getScreenY());
        });

        vbArrow.getChildren().add(arrowConvertImg);
        
        getChildren().addAll(vbArrow, hbNextPlanet);
    }
}
