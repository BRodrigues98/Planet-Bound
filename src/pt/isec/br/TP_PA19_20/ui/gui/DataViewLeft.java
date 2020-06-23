package pt.isec.br.TP_PA19_20.ui.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pt.isec.br.TP_PA19_20.integration.Type;
import pt.isec.br.TP_PA19_20.logic.data.DataGameObs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DataViewLeft extends VBox {
    private DataGameObs dgObs;
    private Text leftText = new Text();



    public DataViewLeft(DataGameObs dgObsN) {
        this.dgObs = dgObsN;
        organizeComponents();

        dgObs.registerPropertyChangeListener(Type.TIP, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                    leftText.setText(dgObs.getTip());
            }
        });



    }

    private void organizeComponents() {
        this.setPrefWidth(200);
        this.setMaxWidth(200);
        leftText.setText("This area will show stats if you hover an important item");
        leftText.setTextAlignment(TextAlignment.LEFT);
        leftText.setWrappingWidth(200);

        this.getChildren().addAll(leftText);
    }
}
