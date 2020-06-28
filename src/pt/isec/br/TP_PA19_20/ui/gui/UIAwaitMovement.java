package pt.isec.br.TP_PA19_20.ui.gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.integration.Type;
import pt.isec.br.TP_PA19_20.logic.data.DataGameObs;
import pt.isec.br.TP_PA19_20.resources.ImageLoader;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class UIAwaitMovement extends VBox {
    private DataGameObs dgObs;
    private Text        txtPlanet       = new Text();
    private ImageView   spaceTravelGif  = new ImageView();
    private Button      btnGo           = new Button("Go!");

    public UIAwaitMovement(DataGameObs dgObsN) {
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
        setVisible(stateID == StateID.AWAIT_MOVEMENT);
        setManaged(stateID == StateID.AWAIT_MOVEMENT);

        if(dgObs.isFirstMove())
            txtPlanet.setText("Let's find your first planet!");
        else
            txtPlanet.setText("Let's keep going!");

    }

    private void organizeComponents() {

        spaceTravelGif.setImage(ImageLoader.loadImage("spaceTravelGif.gif"));
        spaceTravelGif.setPreserveRatio(true);
        spaceTravelGif.setFitWidth(600);

        txtPlanet.setFont(new Font(25));

        btnGo.setPadding(new Insets(15));

        btnGo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dgObs.move(dgObs.isFirstMove());
                dgObs.setShipText(dgObs.getShipText());

                if(dgObs.getWasWormhole()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Went through wormhole");
                    alert.setContentText("You went through a wormhole. You lost extra cells!");

                    alert.showAndWait();
                }

                dgObs.setInstruction(dgObs.getStateID().toString());
            }
        });

        setAlignment(Pos.BASELINE_CENTER);

        setSpacing(30);

        setPadding(new Insets(150, 0, 0, 0));

        getChildren().addAll(spaceTravelGif, txtPlanet, btnGo);
    }
}