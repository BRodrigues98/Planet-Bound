package pt.isec.br.TP_PA19_20.ui.gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.integration.Type;
import pt.isec.br.TP_PA19_20.logic.data.DataGameObs;
import pt.isec.br.TP_PA19_20.resources.ImageLoader;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class UIGameOver extends VBox {
    private         DataGameObs dgObs;
    private         Text            txtFinal            = new Text();
    private         Text            txtPlayAgain        = new Text("Do you want to play again?");
    private         Button          btnYes              = new Button();
    private         Button          btnNo               = new Button();
    private         VBox            vbButton            = new VBox();
    private         VBox            vbTxt               = new VBox();
    private final   String          defaultRightText    = "This area will show your ship condition and your resources";

    public UIGameOver(DataGameObs dgObsN) {
        dgObs = dgObsN;
        dgObs.registerPropertyChangeListener(Type.STATE, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                updateView();
            }
        });

        registerComponents();
        updateView();
    }

    private void updateView() {
        StateID stateID = dgObs.getStateID();
        setVisible(stateID == StateID.GAME_OVER);
        setManaged(stateID == StateID.GAME_OVER);

        if(dgObs.getShip() != null) {
            if (dgObs.hasWon()) {
               txtFinal.setText("Congratulations!\nYou've won the game.");
               txtFinal.setFill(Color.GOLD);
               setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("won.png"), BackgroundRepeat.REPEAT,
                       BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            } else {
                txtFinal.setText("Sorry,\nyou've lost the game.");
                txtFinal.setFill(Color.BLUE);
                setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("gameOver.png"), BackgroundRepeat.REPEAT,
                        BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
            }
        }
    }

    private void registerComponents() {


        txtPlayAgain.setStyle("-fx-font-family: Magneto Bold; -fx-font-size: 40");
        txtFinal.setStyle("-fx-font-family: Magneto Bold; -fx-font-size: 50");

        vbTxt.setSpacing(100);
        vbTxt.setAlignment(Pos.BASELINE_CENTER);
        vbTxt.getChildren().addAll(txtFinal, txtPlayAgain);


        btnYes.setMinSize(300, 50);
        btnNo.setMinSize(300,50);

        btnYes.setAlignment(Pos.BASELINE_CENTER);
        btnNo.setAlignment(Pos.BASELINE_CENTER);

        btnYes.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnYes.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnNo.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnNo.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        vbButton.setSpacing(40);
        vbButton.setAlignment(Pos.BASELINE_CENTER);
        vbButton.getChildren().addAll(btnYes, btnNo);

        handlersHover();

        handlersOnAction();

        setSpacing(50);
        setPadding(new Insets(0, 0, 0, 0));
        setAlignment(Pos.BASELINE_CENTER);
        getChildren().addAll(vbTxt, vbButton);
    }

    private void handlersOnAction() {
        btnYes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dgObs.setInstruction("Choose your spaceship (Hover to see stats)");
                dgObs.setShipText(defaultRightText);

                dgObs.start();
            }
        });

        btnNo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ((Stage) getScene().getWindow()).close();
            }
        });
    }

    private void handlersHover() {
        btnYes.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnYes.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnYes.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnYes.setBorder(null);
            }
        });

        btnNo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnNo.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnNo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnNo.setBorder(null);
            }
        });
        
    }

}
