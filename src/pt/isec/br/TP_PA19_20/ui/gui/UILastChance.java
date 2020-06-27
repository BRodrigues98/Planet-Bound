package pt.isec.br.TP_PA19_20.ui.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.integration.Type;
import pt.isec.br.TP_PA19_20.logic.data.DataGameObs;
import pt.isec.br.TP_PA19_20.resources.ImageLoader;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UILastChance extends VBox {
    private DataGameObs dgObs;
    private Text        txtLastChance           = new Text("This is your last chance");
    private Text        txtExplained            = new Text("You've ran out of fuel but you might be able\nto convert some resources to keep going");
    private Button      btnConvertResources     = new Button();
    private Button      btnGiveUp               = new Button();
    private VBox        vbText                  = new VBox();
    private VBox        vbButton                = new VBox();

    public UILastChance(DataGameObs dgObsN) {
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
        setVisible(stateID == StateID.LAST_CHANCE);
        setManaged(stateID == StateID.LAST_CHANCE);
    }

    private void organizeComponents() {
        BackgroundImage bgImg = new BackgroundImage(ImageLoader.loadImage("lastChance.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background bg = new Background(bgImg);
        setBackground(bg);

        txtLastChance.setStyle("-fx-font-family: Magneto Bold; -fx-font-size: 30;");
        txtExplained.setStyle("-fx-font-family: Magneto Bold; -fx-font-size: 20");

        txtLastChance.setFill(Color.WHITE);
        txtExplained.setFill(Color.WHITE);

        vbText.setSpacing(10);
        vbText.setPadding(new Insets(100, 0, 0, 0));
        vbText.setAlignment(Pos.BASELINE_CENTER);
        vbText.getChildren().addAll(txtLastChance, txtExplained);

        btnConvertResources.setMinSize(600, 50);
        btnGiveUp.setMinSize(600,50);

        btnConvertResources.setAlignment(Pos.BASELINE_CENTER);
        btnGiveUp.setAlignment(Pos.BASELINE_CENTER);


        btnConvertResources.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnConvertResources.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnGiveUp.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnGiveUp.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        vbButton.setSpacing(40);
        vbButton.setAlignment(Pos.BASELINE_CENTER);
        vbButton.getChildren().addAll(btnConvertResources, btnGiveUp);

        handlersHover();

        handlersOnAction();

        setPadding(new Insets(0, 0, 0, 300));
        setSpacing(100);
        getChildren().addAll(vbText, vbButton);
    }

    private void handlersOnAction() {
        btnConvertResources.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
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

        btnGiveUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dgObs.setShipText(dgObs.getShipText());
                dgObs.setInstruction("You've lost the game.");

                dgObs.end();
            }
        });
    }

    private void handlersHover() {
        btnConvertResources.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnConvertResources.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnConvertResources.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnConvertResources.setBorder(null);
            }
        });

        btnGiveUp.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnGiveUp.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnGiveUp.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnGiveUp.setBorder(null);
            }
        });
    }
}
