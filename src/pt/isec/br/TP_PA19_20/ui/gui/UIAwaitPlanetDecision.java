package pt.isec.br.TP_PA19_20.ui.gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.integration.Type;
import pt.isec.br.TP_PA19_20.logic.data.DataGameObs;
import pt.isec.br.TP_PA19_20.logic.data.ship.Military;
import pt.isec.br.TP_PA19_20.logic.data.ship.Mining;
import pt.isec.br.TP_PA19_20.resources.ImageLoader;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class UIAwaitPlanetDecision extends VBox {
    DataGameObs dgObs;
    HBox hbPlanet = new HBox();
    HBox hbShip = new HBox();
    ImageView planetImg = new ImageView();
    ImageView shipImg = new ImageView();
    ImageView spaceStation = new ImageView();
    ImageView portalImg = new ImageView();

    private final String defaultLeftText = "This area will show stats if you hover an important item";

    public UIAwaitPlanetDecision(DataGameObs dgObsN) {
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

        if(dgObs.hasPlanet()) {
            planetImg.setDisable(false);
            planetImg.setImage(ImageLoader.loadImage("planet" + dgObs.getPlanet() + ".png"));
            if(dgObs.hasSpaceStation()) {
                spaceStation.setDisable(false);
                spaceStation.setImage(ImageLoader.loadImage("spacestation.png"));
            }
            else
                spaceStation.setDisable(true);
        }
        else
            planetImg.setDisable(true);

        if(dgObs.getShip() == null)
            shipImg.setDisable(true);
        else {
            shipImg.setDisable(false);
            if(dgObs.getShip() instanceof Mining)
                shipImg.setImage(ImageLoader.loadImage("miner2.png"));
            else if(dgObs.getShip() instanceof Military)
                shipImg.setImage(ImageLoader.loadImage("military2.png"));
        }


        StateID stateID = dgObs.getStateID();
        setVisible(stateID == StateID.AWAIT_PLANET_DECISON);
    }

    private void organizeComponents() {

        BackgroundImage bg = new BackgroundImage(ImageLoader.loadImage("space.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background bg2 = new Background(bg);
        setBackground(bg2);


        //Tratar do planeta
        planetImg.setPreserveRatio(true);
        planetImg.setFitWidth(500);
        Tooltip ttPlanet = new Tooltip("Land on the Planet");
        ttPlanet.setShowDelay(Duration.ZERO);
        Tooltip.install(planetImg, ttPlanet);

        hbPlanet.setPadding(new Insets(0, 0, 0, 400));
        hbPlanet.getChildren().add(planetImg);


        //Tratar da nave
        shipImg.setPreserveRatio(true);
        shipImg.setFitWidth(200);
        Tooltip ttShip = new Tooltip("Convert your resources");
        ttShip.setShowDelay(Duration.ZERO);
        Tooltip.install(shipImg, ttShip);

        hbShip.setPadding(new Insets(20, 0, 0, 50));
        hbShip.getChildren().add(shipImg);


        //Tratar da spacestation
        spaceStation.setPreserveRatio(true);
        spaceStation.setFitWidth(100);
        Tooltip ttSs = new Tooltip("Land on the Space Station");
        ttSs.setShowDelay(Duration.ZERO);
        Tooltip.install(spaceStation, ttSs);

        //É adicionado ao HBox do planeta para ficarem ao mesmo nivel
        hbPlanet.getChildren().add(spaceStation);

        //Tratar do salto para outro planeta

        portalImg.setImage(ImageLoader.loadImage("portal.gif"));
        portalImg.setPreserveRatio(true);
        portalImg.setFitWidth(100);
        Tooltip ttPortal = new Tooltip("Move to next planet");
        ttPortal.setShowDelay(Duration.ZERO);
        Tooltip.install(portalImg, ttPortal);

        hbShip.setSpacing(700);
        hbShip.getChildren().add(portalImg);


        getChildren().addAll(hbShip, hbPlanet);

        //HANDLERS

        //Planeta
        planetImg.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dgObs.setTip(dgObs.getPlanetAsString());
                planetImg.setOpacity(0.75f);
            }
        });

        planetImg.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dgObs.setTip(defaultLeftText);
                planetImg.setOpacity(1f);
            }
        });

        planetImg.setOnMouseMoved((MouseEvent f)->{
            ttPlanet.setX(f.getScreenX());
            ttPlanet.setY(f.getScreenY());
        });


        planetImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dgObs.land();
            }
        });

        //Nave
        shipImg.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dgObs.setTip(dgObs.getConvertText());
                shipImg.setOpacity(0.75f);
            }
        });

        shipImg.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dgObs.setTip(defaultLeftText);
                shipImg.setOpacity(1f);
            }
        });

        shipImg.setOnMouseMoved((MouseEvent f)->{
            ttShip.setX(f.getScreenX());
            ttShip.setY(f.getScreenY());
        });

        shipImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dgObs.convert();
            }
        });

        //Space Station
        spaceStation.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dgObs.setTip(dgObs.getSSText());
                spaceStation.setOpacity(0.75f);
            }
        });

        spaceStation.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dgObs.setTip(defaultLeftText);
                spaceStation.setOpacity(1f);
            }
        });

        spaceStation.setOnMouseMoved((MouseEvent f)->{
            ttSs.setX(f.getScreenX());
            ttSs.setY(f.getScreenY());
        });

        //Portal
        portalImg.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                portalImg.setOpacity(0.75f);
            }
        });

        portalImg.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                portalImg.setOpacity(1f);
            }
        });

        portalImg.setOnMouseMoved((MouseEvent f)->{
            ttPortal.setX(f.getScreenX());
            ttPortal.setY(f.getScreenY());
        });

        portalImg.setOnMouseClicked((MouseEvent f) ->{
            dgObs.nextTurn();
        });
    }
}
