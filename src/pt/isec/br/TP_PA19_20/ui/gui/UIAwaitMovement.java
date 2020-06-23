package pt.isec.br.TP_PA19_20.ui.gui;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.integration.Type;
import pt.isec.br.TP_PA19_20.logic.data.DataGameObs;
import pt.isec.br.TP_PA19_20.resources.ImageLoader;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

class UIAwaitMovement extends VBox {
    private DataGameObs dgObs;

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
        /*
        //Registar o listener
        dgObs.registerPropertyChangeListener(Type.STATE, new PropertyChangeListenerJFXAdapter() {
            @Override
            public void onChange(PropertyChangeEvent evt) {
                if(dgObs.getStateID() == StateID.AWAIT_MOVEMENT){
                    setVisible(true);
                    bottomText.setText("Let's find out where we're going next");
                    rightText.setText(dgObs.getShipText());
                    usefulArea.setBackground(null);
                }
                else
                    setVisible(false);

                spaceTravelGif.setImage(ImageLoader.loadImage("spaceTravelGif.gif"));
                spaceTravelGif.setPreserveRatio(true);
                spaceTravelGif.setFitWidth(600);
                boxGif.setPadding(new Insets(100, 0, 0, 225));
                boxGif.getChildren().add(spaceTravelGif);

                txtPlanet.setTextAlignment(TextAlignment.CENTER);
                txtPlanet.setFont(new Font("Gotham", 20));
                if(dgObs.isFirstMove())
                    txtPlanet.setText("Let's find your first planet!");
                else
                    txtPlanet.setText("Let's keep going!");
                boxText.setPadding(new Insets(25, 0, 0, 400));
                boxText.getChildren().add(txtPlanet);

                btnGo.setPadding(new Insets(15));
                btnGo.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        dgObs.move(dgObs.isFirstMove());
                    }
                });


                boxButton.setPadding(new Insets(25, 0, 0, 490));
                boxButton.getChildren().add(btnGo);
                getChildren().addAll(boxGif, boxText, boxButton);
            }
        });
*/

    }

    private void updateView() {
        StateID stateID = dgObs.getStateID();
        setVisible(stateID == StateID.AWAIT_MOVEMENT);

    }

    private void organizeComponents() {

        ImageView spaceTravelGif = new ImageView();
        spaceTravelGif.setImage(ImageLoader.loadImage("spaceTravelGif.gif"));
        spaceTravelGif.setPreserveRatio(true);
        spaceTravelGif.setFitWidth(600);

        VBox boxGif = new VBox();
        boxGif.setPadding(new Insets(100, 0, 0, 225));
        boxGif.getChildren().add(spaceTravelGif);

        Text txtPlanet = new Text();
        txtPlanet.setTextAlignment(TextAlignment.CENTER);
        txtPlanet.setFont(new Font("Gotham", 20));
        if(dgObs.isFirstMove())
            txtPlanet.setText("Let's find your first planet!");
        else
            txtPlanet.setText("Let's keep going!");

        VBox boxText = new VBox();
        boxText.setPadding(new Insets(25, 0, 0, 400));
        boxText.getChildren().add(txtPlanet);

        Button btnGo = new Button("Go!");
        btnGo.setPadding(new Insets(15));
        btnGo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dgObs.move(dgObs.isFirstMove());
                dgObs.setShipText(dgObs.getShipText());
                dgObs.setInstruction("Choose your next move!");
            }
        });

        VBox boxButton = new VBox();
        boxButton.setPadding(new Insets(25, 0, 0, 490));
        boxButton.getChildren().add(btnGo);
        getChildren().addAll(boxGif, boxText, boxButton);
    }
}