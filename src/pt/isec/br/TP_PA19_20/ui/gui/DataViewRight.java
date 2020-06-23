package pt.isec.br.TP_PA19_20.ui.gui;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.integration.Type;
import pt.isec.br.TP_PA19_20.logic.data.DataGameObs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DataViewRight extends VBox {
    private DataGameObs dgObs;
    private Text rightText = new Text();
    private final String defaultRightText = "This area will show your ship condition and your resources";


    public DataViewRight(DataGameObs dgObsN) {
        this.dgObs = dgObsN;
        organizeComponents();

        dgObs.registerPropertyChangeListener(Type.SHIP, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                if(dgObs.getStateID() != StateID.AWAIT_SPACESHIP_SELECTION)
                    rightText.setText(dgObs.getShipText());
                else
                    rightText.setText(defaultRightText);
            }
        });



    }

    private void organizeComponents() {

        this.setPrefWidth(200);
        this.setMaxWidth(200);
        rightText.setText(defaultRightText);
        rightText.setTextAlignment(TextAlignment.LEFT);
        rightText.setWrappingWidth(200);
        //this.setBackground(new Background(new BackgroundFill(Color.RED, new CornerRadii(0), new Insets(0))));

        this.getChildren().addAll(rightText);
    }
}
