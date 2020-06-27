package pt.isec.br.TP_PA19_20.ui.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.integration.Type;
import pt.isec.br.TP_PA19_20.logic.data.DataGameObs;
import pt.isec.br.TP_PA19_20.resources.ImageLoader;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UIAwaitSSDecision extends VBox {
    private DataGameObs dgObs;
    private Button      btnCargoHold        = new Button();
    private Button      btnCrewMember       = new Button();
    private Button      btnWeaponSystem     = new Button();
    private Button      btnDrone            = new Button();
    private Button      btnGoBack           = new Button();
    
    public UIAwaitSSDecision(DataGameObs dgObsN) {
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

    private void organizeComponents() {
        BackgroundImage bgImg = new BackgroundImage(ImageLoader.loadImage("spaceStationShop.png"), BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background bg = new Background(bgImg);
        setBackground(bg);

        btnCargoHold.setMinSize(600, 50);
        btnCrewMember.setMinSize(600, 50);
        btnWeaponSystem.setMinSize(600, 50);
        btnDrone.setMinSize(600, 50);
        btnGoBack.setMinSize(600, 50);

        btnCargoHold.setAlignment(Pos.CENTER);
        btnCrewMember.setAlignment(Pos.CENTER);
        btnWeaponSystem.setAlignment(Pos.CENTER);
        btnDrone.setAlignment(Pos.CENTER);
        btnGoBack.setAlignment(Pos.CENTER);

        btnCargoHold.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnCargoHold.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnCrewMember.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnCrewMember.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnWeaponSystem.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnWeaponSystem.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnDrone.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnDrone.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnGoBack.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnGoBack.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        handlersHover();

        handlersOnAction();

        getChildren().addAll(btnCargoHold, btnCrewMember, btnWeaponSystem, btnDrone, btnGoBack);

        setPadding(new Insets(200, 0, 0, 0));
        setSpacing(20);
        setAlignment(Pos.BASELINE_CENTER);
    }

    private void updateView() {
        StateID stateID = dgObs.getStateID();
        setVisible(stateID == StateID.AWAIT_SS_DECISION);
        setManaged(stateID == StateID.AWAIT_SS_DECISION);
    }

    private void handlersHover() {
        btnCargoHold.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnCargoHold.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnCargoHold.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnCargoHold.setBorder(null);
            }
        });

        btnCrewMember.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnCrewMember.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnCrewMember.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnCrewMember.setBorder(null);
            }
        });

        btnWeaponSystem.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnWeaponSystem.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnWeaponSystem.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnWeaponSystem.setBorder(null);
            }
        });

        btnDrone.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnDrone.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnDrone.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnDrone.setBorder(null);
            }
        });

        btnGoBack.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnGoBack.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnGoBack.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnGoBack.setBorder(null);
            }
        });
    }

    private void handlersOnAction() {
        btnCargoHold.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(!dgObs.isCargoMaxLevel()) {
                    if (dgObs.getBlackRes() <= 1 || dgObs.getRedRes() <= 1 || dgObs.getBlueRes() <= 1 || dgObs.getGreenRes() <= 1) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Not enough resources");
                        alert.setContentText("You don't have enough resources to upgrade your Cargo Hold.");

                        alert.showAndWait();
                    }
                    else if(dgObs.wasUpgradedThisTurn()){
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Cargo Already Upgraded");
                        alert.setContentText("You can upgrade your Cargo Hold one time per visit.");

                        alert.showAndWait();
                    }
                    else {
                        dgObs.makesDecision(1);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Drone's Armor replenished");
                        alert.setContentText("Your Drone's Armor was replenished. It now has 6 Armor.");

                        alert.showAndWait();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cargo is at max level");
                    alert.setContentText("Your Cargo is already at its max level.");

                    alert.showAndWait();
                }

                dgObs.setShipText(dgObs.getShipText());
            }
        });

        btnCrewMember.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!dgObs.isAllOfficersAlive()){
                    if (dgObs.getBlackRes() == 0 || dgObs.getRedRes() == 0 || dgObs.getBlueRes() == 0 || dgObs.getGreenRes() == 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Not enough resources");
                        alert.setContentText("You don't have enough resources to hire a new crew member.");

                        alert.showAndWait();
                    }
                    else {
                        dgObs.convert(2);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Crew Member Hired");
                        alert.setContentText("You've hired a new Crew Member.");

                        alert.showAndWait();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("All Officers Alive");
                    alert.setContentText("All your crew is alive and healthy. No need to hire a new crew member.");

                    alert.showAndWait();
                }

                dgObs.setShipText(dgObs.getShipText());
            }
        });

        btnWeaponSystem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!dgObs.isWeaponMaxLevel()){
                    if (dgObs.getBlackRes() <= 1 || dgObs.getRedRes() <= 1 || dgObs.getBlueRes() <= 1 || dgObs.getGreenRes() <= 1) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Not enough resources");
                        alert.setContentText("You don't have enough resources to upgrade your Weapon system.");

                        alert.showAndWait();
                    }
                    else {
                        dgObs.convert(3);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Weapon System upgraded.");
                        alert.setContentText("Your Weapon System has now been upgraded.");

                        alert.showAndWait();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Weapons at max level");
                    alert.setContentText("Your Weapon's System is at max level.");

                    alert.showAndWait();
                }

                dgObs.setShipText(dgObs.getShipText());
            }
        });

        btnDrone.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                if(!dgObs.hasDrone()){
                    if (dgObs.getBlackRes() <= 1 || dgObs.getRedRes() <= 1 || dgObs.getBlueRes() <= 1 || dgObs.getGreenRes() <= 1) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Not enough resources");
                        alert.setContentText("You don't have enough resources to buy a new Drone.");

                        alert.showAndWait();
                    }
                    else {
                        dgObs.convert(4);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Bought new Drone");
                        alert.setContentText("New Drone bought and added to your ship at full Armor.");

                        alert.showAndWait();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Can't buy drone");
                    alert.setContentText("You already have a Drone and you can't buy a new one.");

                    alert.showAndWait();
                }

                dgObs.setShipText(dgObs.getShipText());
            }
        });

        btnGoBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //dgObs.savePlanet();
                dgObs.backToPlanet();
            }
        });
    }


}
