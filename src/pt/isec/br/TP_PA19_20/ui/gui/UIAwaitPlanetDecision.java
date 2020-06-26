package pt.isec.br.TP_PA19_20.ui.gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
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
    private DataGameObs     dgObs;
    private HBox            hbPlanet        = new HBox();
    private HBox            hbShip          = new HBox();
    private ImageView       planetImg       = new ImageView();
    private ImageView       shipImg         = new ImageView();
    private ImageView       spaceStation    = new ImageView();
    private ImageView       portalImg       = new ImageView();
    private Tooltip         ttPlanet        = new Tooltip("Land on the Planet");
    private Tooltip         ttShip          = new Tooltip("Convert your resources");
    private Tooltip         ttSs            = new Tooltip("Land on the Space Station");
    private Tooltip         ttPortal        = new Tooltip("Move to next planet");
    private Alert           showMining      = new Alert(Alert.AlertType.INFORMATION);
    private final String    defaultLeftText = "This area will show stats if you hover an important item";

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


        //Handlers
        //Planeta

        planetImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int dHp = dgObs.getDroneHp();
                dgObs.land();
                if (dgObs.hasDrone()) {
                    if(dHp == dgObs.getDroneHp())
                        showMining.setContentText(dgObs.getMiningResults());
                    else
                        showMining.setContentText(dgObs.getMiningResults() + "\nYour drone lost " + (dHp - dgObs.getDroneHp()) + " armor in a fight with an alien!");
                    if(!dgObs.isExplorationAlive())
                        showMining.setContentText("Couldn't land. You don't have an Exploration Officer.");
                }
                else {
                    showMining.setContentText("Your drone was destroyed. You'll need to buy a new one at a Space Station.");
                }
                showMining.showAndWait();

                if(dgObs.wasFullyMined()){
                    dgObs.setInstruction("Do you want to convert your resources or advance in space?");
                }
                dgObs.setShipText(dgObs.getShipText());
            }
        });

        //Nave

        shipImg.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(dgObs.isCargoAlive()) {
                    dgObs.savePlanet();

                    dgObs.setInstruction("Select an option to convert your resources.");
                    dgObs.setShipText(dgObs.getShipText());

                    dgObs.convert();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cargo Hold Officer dead");
                    alert.setContentText("Your Cargo Hold Officer is dead. You can't convert resources.");

                    alert.showAndWait();
                }
            }
        });

        //Space Station

        spaceStation.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dgObs.landOnSS();
            }
        });

        //Portal

        portalImg.setOnMouseClicked((MouseEvent f) ->{
            dgObs.nextTurn();

            dgObs.setShipText(dgObs.getShipText());
            dgObs.setInstruction("Ready to go?");
        });


        StateID stateID = dgObs.getStateID();
        setVisible(stateID == StateID.AWAIT_PLANET_DECISON);
        setManaged(stateID == StateID.AWAIT_PLANET_DECISON);
    }

    private void organizeComponents() {

        BackgroundImage bgImg = new BackgroundImage(ImageLoader.loadImage("space.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background bg = new Background(bgImg);
        setBackground(bg);


        //Tratar do planeta
        planetImg.setPreserveRatio(true);
        planetImg.setFitWidth(500);

        ttPlanet.setShowDelay(Duration.ZERO);
        Tooltip.install(planetImg, ttPlanet);

        hbPlanet.setPadding(new Insets(0, 0, 0, 400));
        hbPlanet.getChildren().add(planetImg);


        //Tratar da nave
        shipImg.setPreserveRatio(true);
        shipImg.setFitWidth(200);
        ttShip.setShowDelay(Duration.ZERO);
        Tooltip.install(shipImg, ttShip);


        hbShip.setPadding(new Insets(20, 0, 0, 50));
        hbShip.getChildren().add(shipImg);


        //Tratar da spacestation
        spaceStation.setPreserveRatio(true);
        spaceStation.setFitWidth(100);
        ttSs.setShowDelay(Duration.ZERO);
        Tooltip.install(spaceStation, ttSs);

        //É adicionado ao HBox do planeta para ficarem ao mesmo nivel
        hbPlanet.getChildren().add(spaceStation);

        //Tratar do salto para outro planeta

        portalImg.setImage(ImageLoader.loadImage("portal.gif"));
        portalImg.setPreserveRatio(true);
        portalImg.setFitWidth(100);
        ttPortal.setShowDelay(Duration.ZERO);
        Tooltip.install(portalImg, ttPortal);

        hbShip.setSpacing(700);
        hbShip.getChildren().add(portalImg);


        showMining.setTitle("Mining Results");

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

        getChildren().addAll(hbShip, hbPlanet);


    }
}
