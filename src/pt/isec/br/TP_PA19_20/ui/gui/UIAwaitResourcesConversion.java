package pt.isec.br.TP_PA19_20.ui.gui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
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

public class UIAwaitResourcesConversion extends HBox {
    private DataGameObs dgObs;
    private VBox        boxButtons       = new VBox();
    private VBox        boxResourceC     = new VBox();
    private HBox        boxConvert       = new HBox();
    private HBox        boxCLabel        = new HBox();
    private HBox        boxBtn           = new HBox();
    private Button      btnOneToAnother  = new Button();
    private Button      btnDroneArmor    = new Button();
    private Button      btnEnergyShield  = new Button();
    private Button      btnExtraAmmo     = new Button();
    private Button      btnExtraFuel     = new Button();
    private Button      btnGoBack        = new Button();
    private Button      btnCancel        = new Button("Cancel");
    private Button      btnOk            = new Button("OK");
    private ImageView   moneyBag         = new ImageView();
    private Text        oldR             = new Text("Old Resource");
    private Text        newR             = new Text("New Resource");
    private ComboBox    cbOld            = new ComboBox(FXCollections.observableArrayList("Black", "Red", "Blue", "Green"));
    private ComboBox    cbNew            = new ComboBox(FXCollections.observableArrayList("Black", "Red", "Blue", "Green"));
    private int         resOld;
    private int         resNew;

    public UIAwaitResourcesConversion(DataGameObs dgObsN) {
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
        setVisible(stateID == StateID.AWAIT_RESOURCES_CONVERSION);
        setManaged(stateID == StateID.AWAIT_RESOURCES_CONVERSION);
    }

    private void organizeComponents() {
        BackgroundImage bgImg = new BackgroundImage(ImageLoader.loadImage("workbench.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background bg = new Background(bgImg);
        setBackground(bg);

        moneyBag.setImage(ImageLoader.loadImage("moneybag.png"));
        moneyBag.setPreserveRatio(true);
        moneyBag.setFitHeight(100);

        btnOneToAnother.setMinSize(600,50);
        btnDroneArmor.setMinSize(600,50);
        btnEnergyShield.setMinSize(600,50);
        btnExtraAmmo.setMinSize(600,50);
        btnExtraFuel.setMinSize(600,50);
        btnGoBack.setMinSize(600,50);

        btnOneToAnother.setAlignment(Pos.BASELINE_CENTER);
        btnDroneArmor.setAlignment(Pos.BASELINE_CENTER);
        btnEnergyShield.setAlignment(Pos.BASELINE_CENTER);
        btnExtraAmmo.setAlignment(Pos.BASELINE_CENTER);
        btnExtraFuel.setAlignment(Pos.BASELINE_CENTER);
        btnGoBack.setAlignment(Pos.BASELINE_CENTER);

        btnOneToAnother.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnResources.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnDroneArmor.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnDroneArmor.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnEnergyShield.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnEnergyShield.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnExtraAmmo.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnAmmo.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnExtraFuel.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnFuel.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        btnGoBack.setBackground(new Background(new BackgroundImage(ImageLoader.loadImage("btnGoBack.png"), BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

        handlersHover();

        handlersOnAction();

        cbOld.setValue("Black");
        cbNew.setValue("Black");

        boxButtons.setPadding(new Insets(250, 0, 0, 0));
        boxButtons.setSpacing(10);

        boxButtons.getChildren().addAll(btnOneToAnother, btnDroneArmor, btnEnergyShield, btnExtraAmmo, btnExtraFuel, btnGoBack);


        boxCLabel.getChildren().addAll(oldR, newR);
        boxResourceC.getChildren().addAll(boxCLabel, boxConvert, boxBtn);
        boxConvert.getChildren().addAll(cbOld, cbNew);

        boxBtn.getChildren().addAll(btnOk, btnCancel);

        boxResourceC.setVisible(false);
        boxResourceC.setManaged(false);
        

        getChildren().addAll(boxButtons, boxResourceC); //, costs);
        setAlignment(Pos.BASELINE_CENTER);
    }

    private void handlersOnAction() {
        btnOneToAnother.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boxButtons.setVisible(false);
                boxButtons.setManaged(false);

                boxConvert.setVisible(true);
                boxConvert.setManaged(true);

                boxCLabel.setVisible(true);
                boxCLabel.setManaged(true);

                boxResourceC.setVisible(true);
                boxResourceC.setManaged(true);

                oldR.setFill(Color.WHITE);
                newR.setFill(Color.WHITE);
                oldR.setStyle("-fx-font-family: Magneto Bold; -fx-font-size: 20;");
                newR.setStyle("-fx-font-family: Magneto Bold; -fx-font-size: 20");


                cbOld.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        resOld = t1.intValue();
                    }
                });

                cbNew.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                        resNew = t1.intValue();
                    }
                });

                cbOld.setPrefWidth(200);
                cbNew.setPrefWidth(200);


                boxCLabel.setSpacing(75);
                boxCLabel.setAlignment(Pos.BASELINE_CENTER);
                boxCLabel.setPadding(new Insets(350, 0, 0, 0));


                boxConvert.setAlignment(Pos.BASELINE_CENTER);
                boxConvert.setSpacing(20);

                boxConvert.setPadding(new Insets(0, 0, 0, 0));


                boxBtn.setSpacing(20);

                btnCancel.setPrefSize(80, 30);
                btnOk.setPrefSize(80, 30);

                btnCancel.setAlignment(Pos.BASELINE_CENTER);
                btnOk.setAlignment(Pos.BASELINE_CENTER);

                boxBtn.setPadding(new Insets(100, 0, 0, 0));
                boxBtn.setAlignment(Pos.BASELINE_CENTER);



                btnOk.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        boxResourceC.setVisible(false);
                        boxResourceC.setManaged(false);

                        if(resOld == resNew){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Resources are the same");
                            alert.setContentText("You can't convert resources of the same type.");

                            boxResourceC.setVisible(true);
                            boxResourceC.setManaged(true);

                            alert.showAndWait();
                        }
                        else if(dgObs.getResource(resOld) == 0){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("No resources of type selected");
                            alert.setContentText("You don't have any resources of that type.");

                            boxResourceC.setVisible(true);
                            boxResourceC.setManaged(true);

                            alert.showAndWait();
                        }
                        else if(dgObs.getResource(resNew) == dgObs.getMaxResource()){
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Cargo Hold at Max Capacity");
                            alert.setContentText("Your Cargo Hold is already at max capacity for that resource.");

                            boxResourceC.setVisible(true);
                            boxResourceC.setManaged(true);

                            alert.showAndWait();
                        }
                        else {
                            dgObs.convert(resNew, resOld);

                            boxButtons.setVisible(true);
                            boxButtons.setManaged(true);
                            dgObs.setShipText(dgObs.getShipText());
                        }
                    }
                });

                btnCancel.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        boxResourceC.setVisible(false);
                        boxResourceC.setManaged(false);

                        boxButtons.setVisible(true);
                        boxButtons.setManaged(true);
                        dgObs.setShipText(dgObs.getShipText());
                    }
                });
            }
        });

        btnDroneArmor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boxButtons.setVisible(false);
                boxButtons.setManaged(false);

                if(!dgObs.isDroneFull()) {
                    if (dgObs.getBlackRes() == 0 || dgObs.getRedRes() == 0 || dgObs.getBlueRes() == 0 || dgObs.getGreenRes() == 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Not enough resources");
                        alert.setContentText("You don't have enough resources to replenish your Drone's Armor.");

                        boxButtons.setVisible(true);
                        boxButtons.setManaged(true);

                        alert.showAndWait();
                    } else {
                        dgObs.convert(0);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Drone's Armor replenished");
                        alert.setContentText("Your Drone's Armor was replenished. It now has 6 Armor.");

                        boxButtons.setVisible(true);
                        boxButtons.setManaged(true);

                        alert.showAndWait();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Drone has full armor");
                    alert.setContentText("Your Drone already has it's armor at max capacity.");

                    boxButtons.setVisible(true);
                    boxButtons.setManaged(true);

                    alert.showAndWait();
                }

                dgObs.setShipText(dgObs.getShipText());
            }
        });

        btnEnergyShield.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boxButtons.setVisible(false);
                boxButtons.setManaged(false);

                if(!dgObs.isShieldFull()){
                    if (dgObs.getBlackRes() == 0 || dgObs.getBlueRes() == 0 || dgObs.getGreenRes() == 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Not enough resources");
                        alert.setContentText("You don't have enough resources to get an extra Shield cell.");

                        boxButtons.setVisible(true);
                        boxButtons.setManaged(true);

                        alert.showAndWait();
                    }
                    else {
                        dgObs.convert(1);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("1 extra Shield cell.");
                        alert.setContentText("Extra Shield cell crafted. You now have " + dgObs.getShield());

                        boxButtons.setVisible(true);
                        boxButtons.setManaged(true);

                        alert.showAndWait();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Shield has max cells");
                    alert.setContentText("Your ship's Shield has max cells.");

                    boxButtons.setVisible(true);
                    boxButtons.setManaged(true);

                    alert.showAndWait();
                }

                dgObs.setShipText(dgObs.getShipText());
            }
        });

        btnExtraAmmo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boxButtons.setVisible(false);
                boxButtons.setManaged(false);

                if(!dgObs.isAmmoFull()){
                    if (dgObs.getBlackRes() == 0 || dgObs.getBlueRes() == 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Not enough resources");
                        alert.setContentText("You don't have enough resources to get an extra Ammo cell.");

                        boxButtons.setVisible(true);
                        boxButtons.setManaged(true);

                        alert.showAndWait();
                    }
                    else {
                        dgObs.convert(2);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("1 extra Ammo cell.");
                        alert.setContentText("Extra Ammo cell crafted. You now have " + dgObs.getAmmo());

                        boxButtons.setVisible(true);
                        boxButtons.setManaged(true);

                        alert.showAndWait();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ammo has max cells");
                    alert.setContentText("Your ship's Ammo has max cells.");

                    boxButtons.setVisible(true);
                    boxButtons.setManaged(true);

                    alert.showAndWait();
                }

                dgObs.setShipText(dgObs.getShipText());
            }
        });

        btnExtraFuel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                boxButtons.setVisible(false);
                boxButtons.setManaged(false);

                if(!dgObs.isFuelFull()){
                    if (dgObs.getBlackRes() == 0 || dgObs.getRedRes() == 0 || dgObs.getGreenRes() == 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Not enough resources");
                        alert.setContentText("You don't have enough resources to get an extra Fuel cell.");

                        boxButtons.setVisible(true);
                        boxButtons.setManaged(true);

                        alert.showAndWait();
                    }
                    else {
                        dgObs.convert(3);

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("1 extra Fuel cell.");
                        alert.setContentText("Extra Fuel cell crafted. You now have " + dgObs.getFuel());

                        boxButtons.setVisible(true);
                        boxButtons.setManaged(true);

                        alert.showAndWait();
                    }
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Fuel has max cells");
                    alert.setContentText("Your ship's Fuel has max cells.");

                    boxButtons.setVisible(true);
                    boxButtons.setManaged(true);

                    alert.showAndWait();
                }

                dgObs.setShipText(dgObs.getShipText());
            }
        });

        btnGoBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                dgObs.stopConvert();
            }
        });
    }

    private void handlersHover() {
        btnOneToAnother.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnOneToAnother.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnOneToAnother.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnOneToAnother.setBorder(null);
            }
        });
        
        btnDroneArmor.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnDroneArmor.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnDroneArmor.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnDroneArmor.setBorder(null);
            }
        });
        
        btnEnergyShield.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnEnergyShield.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnEnergyShield.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnEnergyShield.setBorder(null);
            }
        });
        
        btnExtraAmmo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnExtraAmmo.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnExtraAmmo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnExtraAmmo.setBorder(null);
            }
        });
        
        btnExtraFuel.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnExtraFuel.setBorder(new Border(new BorderStroke(Color.GRAY,
                        BorderStrokeStyle.SOLID, null, BorderStroke.MEDIUM)));
            }
        });

        btnExtraFuel.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                btnExtraFuel.setBorder(null);
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


}
