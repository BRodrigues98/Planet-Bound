package pt.isec.br.TP_PA19_20.ui.gui;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.integration.Type;
import pt.isec.br.TP_PA19_20.logic.data.DataGameObs;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DataViewBottom extends HBox {
    private DataGameObs dgObs;
    private Text bottomText = new Text();


    public DataViewBottom(DataGameObs dgObsN) {
        this.dgObs = dgObsN;
        organizeComponents();

        dgObs.registerPropertyChangeListener(Type.INSTRUCTION, new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                bottomText.setText(dgObs.getInstruction());
            }
        });
    }

    private void organizeComponents() {
        this.setPrefHeight(100);
        this.setPadding(new Insets(10, 0, 0, 0));

        bottomText.setWrappingWidth(this.getWidth());
        bottomText.setFont(new Font("Gotham", 50));
        bottomText.setTextAlignment(TextAlignment.CENTER);

        bottomText.setText("Choose your spacecraft (Hover to see stats)");
        getChildren().add(bottomText);
    }
}
