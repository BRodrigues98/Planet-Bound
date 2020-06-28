package pt.isec.br.TP_PA19_20.ui.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.integration.Type;
import pt.isec.br.TP_PA19_20.logic.data.DataGameObs;
import pt.isec.br.TP_PA19_20.resources.ImageLoader;
import pt.isec.br.TP_PA19_20.resources.MusicPlayer;
import pt.isec.br.TP_PA19_20.resources.VideoLoader;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UIAwaitDiceRoll extends VBox {
    private         DataGameObs dgObs;
    private         MediaView   mv                  = new MediaView(VideoLoader.playVideo("hyperdrive.mp4"));
    private         HBox        hbVideo             = new HBox();
    private         HBox        hbChoices           = new HBox();
    private         ImageView   dice                = new ImageView(ImageLoader.loadImage("dice.png"));
    private         ImageView   forced              = new ImageView(ImageLoader.loadImage("clipboard.png"));
    private         HBox        hbDice              = new HBox();
    private         HBox        hbForced            = new HBox();
    private         Tooltip     ttDice              = new Tooltip("Random Event");
    private         Tooltip     ttForced            = new Tooltip("Forced Event");
    private         Button      btnCrewDeath        = new Button();
    private         Button      btnSalvageShip      = new Button();
    private         Button      btnCargoLoss        = new Button();
    private         Button      btnFuelLoss         = new Button();
    private         Button      btnNoEvent          = new Button();
    private         Button      btnCrewRescue       = new Button();
    private         VBox        hbBtnsForced        = new VBox();
    private final   String      defaultLeftText     = "This area will show stats if you hover an important item";

    public UIAwaitDiceRoll(DataGameObs dgObsN) {
        super(5);
        this.dgObs = dgObsN;
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
        setVisible(stateID == StateID.AWAIT_DICE_ROLL);
        setManaged(stateID == StateID.AWAIT_DICE_ROLL);

        mv.getMediaPlayer().seek(Duration.ZERO);

    }

    private void organizeComponents() {
        mv.setPreserveRatio(true);
        mv.setFitWidth(800);

        hbVideo.getChildren().add(mv);
        hbVideo.setPadding(new Insets(50, 0, 0, 125));

        dice.setPreserveRatio(true);
        dice.setFitWidth(150);

        forced.setPreserveRatio(true);
        forced.setFitWidth(150);


        ttDice.setShowDelay(Duration.ZERO);
        Tooltip.install(dice, ttDice);

        ttForced.setShowDelay(Duration.ZERO);
        Tooltip.install(forced, ttForced);

        hbDice.setMaxHeight(dice.getFitHeight());
        hbDice.setMaxWidth(dice.getFitWidth());
        hbDice.setAlignment(Pos.BASELINE_CENTER);
        hbDice.getChildren().add(dice);

        hbForced.setMaxHeight(forced.getFitHeight());
        hbForced.setMaxWidth(forced.getFitWidth());
        hbForced.setAlignment(Pos.BASELINE_CENTER);
        hbForced.getChildren().add(forced);

        hbChoices.getChildren().addAll(hbDice, hbForced);
        hbChoices.setAlignment(Pos.BASELINE_CENTER);
        hbChoices.setSpacing(75);

        btnCrewDeath.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnCrewDeath.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnSalvageShip.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnSalvageShip.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnCargoLoss.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnCargoLoss.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnFuelLoss.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnFuelLoss.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnNoEvent.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnNoEvent.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnCrewRescue.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnCrewRescue.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnCrewDeath.setMinSize(600,50);
        btnSalvageShip.setMinSize(600,50);
        btnCargoLoss.setMinSize(600,50);
        btnFuelLoss.setMinSize(600,50);
        btnNoEvent.setMinSize(600,50);
        btnCrewRescue.setMinSize(600,50);

        btnCrewDeath.setAlignment(Pos.BASELINE_CENTER);
        btnSalvageShip.setAlignment(Pos.BASELINE_CENTER);
        btnCargoLoss.setAlignment(Pos.BASELINE_CENTER);
        btnFuelLoss.setAlignment(Pos.BASELINE_CENTER);
        btnNoEvent.setAlignment(Pos.BASELINE_CENTER);
        btnCrewRescue.setAlignment(Pos.BASELINE_CENTER);
        
        hoverHandlers();
        handlersOnAction();

        hbBtnsForced.getChildren().addAll(btnCrewDeath, btnSalvageShip, btnCargoLoss, btnFuelLoss, btnNoEvent, btnCrewRescue);
        hbBtnsForced.setVisible(false);
        hbBtnsForced.setManaged(false);
        hbBtnsForced.setSpacing(10);
        hbBtnsForced.setAlignment(Pos.BASELINE_CENTER);

        this.setAlignment(Pos.CENTER);
        this.getChildren().addAll(hbBtnsForced, hbVideo, hbChoices);
        this.setSpacing(20);
        
        
        //Handlers
        mv.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MusicPlayer.playMusic("chewie.mp3", 50);
            }
        });

        hbDice.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dgObs.setTip("Go through one of these events at random before going to the next planet:\n" +
                        "\t1 - Lose one crew\n\t2 - Salvage Loot\n\t3 - Loss of some cargo\n\t4 - Loss of Fuel\n\t5 - No Event\n\t6 - Rescue of crew");

                hbDice.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, new CornerRadii(10), BorderStroke.MEDIUM)));
            }
        });

        hbDice.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dgObs.setTip(defaultLeftText);
                hbDice.setBorder(null);
            }
        });

        hbDice.setOnMouseMoved((MouseEvent f)->{
            ttDice.setX(f.getScreenX());
            ttDice.setY(f.getScreenY());
        });

        hbDice.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dgObs.roll(1);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Event");
                alert.setContentText(dgObs.getEvent());

                alert.showAndWait();

                dgObs.setShipText(dgObs.getShipText());
                dgObs.setInstruction(dgObs.getStateID().toString());
            }
        });

        hbForced.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dgObs.setTip("Choose of these events before going to the next planet:\n" +
                        "\t1 - Lose one crew\n\t2 - Salvage Loot\n\t3 - Loss of some cargo\n\t4 - Loss of Fuel\n\t5 - No Event\n\t6 - Rescue of crew");

                hbForced.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, new CornerRadii(10), BorderStroke.MEDIUM)));
            }
        });

        hbForced.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dgObs.setTip(defaultLeftText);
                hbForced.setBorder(null);
            }
        });

        hbForced.setOnMouseMoved((MouseEvent f)->{
            ttForced.setX(f.getScreenX());
            ttForced.setY(f.getScreenY());
        });

        hbForced.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                hbVideo.setVisible(false);
                hbVideo.setManaged(false);

                hbChoices.setVisible(false);
                hbChoices.setManaged(false);

                hbBtnsForced.setVisible(true);
                hbBtnsForced.setManaged(true);


            }
        });
    }

    private void handlersOnAction() {
        btnCrewDeath.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dgObs.roll(-1);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Crew Death");
                alert.setContentText("One of crew members has died due to a system malfunction.");

                alert.showAndWait();

                dgObs.setShipText(dgObs.getShipText());
                dgObs.setInstruction(dgObs.getStateID().toString());

                hbVideo.setVisible(true);
                hbVideo.setManaged(true);

                hbChoices.setVisible(true);
                hbChoices.setManaged(true);

                hbBtnsForced.setVisible(false);
                hbBtnsForced.setManaged(false);
            }
        });

        btnSalvageShip.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dgObs.roll(-2);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ship Salvage");
                alert.setContentText("You've found an abandoned ship. You've found some extra resources.");

                alert.showAndWait();

                dgObs.setShipText(dgObs.getShipText());
                dgObs.setInstruction(dgObs.getStateID().toString());

                hbVideo.setVisible(true);
                hbVideo.setManaged(true);

                hbChoices.setVisible(true);
                hbChoices.setManaged(true);

                hbBtnsForced.setVisible(false);
                hbBtnsForced.setManaged(false);
            }

        });

        btnCargoLoss.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dgObs.roll(-3);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Cargo Loss");
                alert.setContentText("You've had a Cargo Loss. You lost some resources.");

                alert.showAndWait();

                dgObs.setShipText(dgObs.getShipText());
                dgObs.setInstruction(dgObs.getStateID().toString());

                hbVideo.setVisible(true);
                hbVideo.setManaged(true);

                hbChoices.setVisible(true);
                hbChoices.setManaged(true);

                hbBtnsForced.setVisible(false);
                hbBtnsForced.setManaged(false);
            }

        });

        btnFuelLoss.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dgObs.roll(-4);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Fuel Loss");
                alert.setContentText("You've accidentally used too much fuel on a fuel run.");

                alert.showAndWait();

                dgObs.setShipText(dgObs.getShipText());
                dgObs.setInstruction(dgObs.getStateID().toString());

                hbVideo.setVisible(true);
                hbVideo.setManaged(true);

                hbChoices.setVisible(true);
                hbChoices.setManaged(true);

                hbBtnsForced.setVisible(false);
                hbBtnsForced.setManaged(false);
            }

        });

        btnNoEvent.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dgObs.roll(-5);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("No Event");
                alert.setContentText("Fortunately nothing happened. Smooth sailing.");

                alert.showAndWait();

                dgObs.setShipText(dgObs.getShipText());
                dgObs.setInstruction(dgObs.getStateID().toString());

                hbVideo.setVisible(true);
                hbVideo.setManaged(true);

                hbChoices.setVisible(true);
                hbChoices.setManaged(true);

                hbBtnsForced.setVisible(false);
                hbBtnsForced.setManaged(false);
            }

        });

        btnCrewRescue.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dgObs.roll(-6);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Crew Rescue");
                alert.setContentText("You've found a ship in distress with a lone crew member and he is added to your officers list.");

                alert.showAndWait();

                dgObs.setShipText(dgObs.getShipText());
                dgObs.setInstruction(dgObs.getStateID().toString());

                hbVideo.setVisible(true);
                hbVideo.setManaged(true);

                hbChoices.setVisible(true);
                hbChoices.setManaged(true);

                hbBtnsForced.setVisible(false);
                hbBtnsForced.setManaged(false);
            }

        });
    }

    private void hoverHandlers() {
        btnCrewDeath.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnCrewDeath.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnCrewDeath.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnCrewDeath.setBorder(null);
            }
        });

        btnSalvageShip.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnSalvageShip.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnSalvageShip.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnSalvageShip.setBorder(null);
            }
        });

        btnCargoLoss.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnCargoLoss.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnCargoLoss.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnCargoLoss.setBorder(null);
            }
        });

        btnFuelLoss.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnFuelLoss.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnFuelLoss.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnFuelLoss.setBorder(null);
            }
        });

        btnNoEvent.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnNoEvent.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnNoEvent.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnNoEvent.setBorder(null);
            }
        });

        btnCrewRescue.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnCrewRescue.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnCrewRescue.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btnCrewRescue.setBorder(null);
            }
        });
    }
}
