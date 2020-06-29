package pt.isec.br.TP_PA19_20.ui.gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.integration.Type;
import pt.isec.br.TP_PA19_20.logic.data.DataGameObs;
import pt.isec.br.TP_PA19_20.resources.ImageLoader;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class UIAwaitSpaceshipSelection extends HBox {
    private         DataGameObs dgObs;
    private final   String      defaultLeftText = "This area will show stats if you hover an important item";

    public UIAwaitSpaceshipSelection(DataGameObs dgObsN) {
        super(5);
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
        setVisible(stateID == StateID.AWAIT_SPACESHIP_SELECTION);
        setManaged(stateID == StateID.AWAIT_SPACESHIP_SELECTION);
    }

    private void organizeComponents() {
        dgObs.setInstruction(dgObs.getStateID().toString());
        setAlignment(Pos.CENTER);
        ImageView miningImg = new ImageView();
        miningImg.setImage(ImageLoader.loadImage("miner.jpg"));
        miningImg.setFitHeight(400);
        miningImg.setFitWidth(400);

        Button miningBtn = new Button();
        miningBtn.setGraphic(miningImg);
        miningBtn.setPadding(new Insets(5));
        miningBtn.setAlignment(Pos.CENTER_LEFT);

        ImageView militaryImg = new ImageView();
        militaryImg.setImage(ImageLoader.loadImage("military.jpg"));
        militaryImg.setFitHeight(400);
        militaryImg.setFitWidth(400);

        Button militaryBtn = new Button();
        militaryBtn.setGraphic(militaryImg);
        militaryBtn.setPadding(new Insets(5));
        miningBtn.setAlignment(Pos.CENTER_RIGHT);

        miningBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                miningBtn.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, new CornerRadii(10), BorderStroke.MEDIUM)));

                dgObs.setTip(dgObs.getMinerStats());
            }
        });

        militaryBtn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                militaryBtn.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, new CornerRadii(10), BorderStroke.MEDIUM)));

                dgObs.setTip(dgObs.getMilitaryStats());
            }
        });

        miningBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                miningBtn.setBorder(null);
                dgObs.setTip(defaultLeftText);
            }
        });

        militaryBtn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                militaryBtn.setBorder(null);
                dgObs.setTip(defaultLeftText);
            }
        });

        miningBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dgObs.selectShip(1);

                //Definir do lado direito os stats
                dgObs.setShipText(dgObs.getShipText());
                dgObs.setInstruction(dgObs.getStateID().toString());
            }
        });

        militaryBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dgObs.selectShip(2);
                //Definir do lado direito os stats
                dgObs.setShipText(dgObs.getShipText());
                dgObs.setInstruction(dgObs.getStateID().toString());
            }
        });

        getChildren().addAll(miningBtn, militaryBtn);
    }
}
