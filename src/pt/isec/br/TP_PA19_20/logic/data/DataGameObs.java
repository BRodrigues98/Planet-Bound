package pt.isec.br.TP_PA19_20.logic.data;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.integration.Type;
import pt.isec.br.TP_PA19_20.logic.data.ship.Ship;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataGameObs {
    private GamePlay game;
    private PropertyChangeSupport props;
    private String tip;
    private String shipText;
    private String instruction;

    public DataGameObs() {
        game = new GamePlay();
        props = new PropertyChangeSupport(game);
    }

    //---------- EVENTS/STATES ----------
    public void registerPropertyChangeListener(Type t, PropertyChangeListener listener){
        props.addPropertyChangeListener(t.toString(), listener);
    }

    public StateID getStateID() {
        return game.getStateID();
    }

    public void selectShip(int value){
        game.setShip(value);
        props.firePropertyChange(Type.STATE.toString(), null, null);
    }

    public void restart() {
        game.restart();
        props.firePropertyChange(Type.STATE.toString(), null, null);
    }

    public void move(boolean firstMove) {
        game.move(firstMove);
        props.firePropertyChange(Type.STATE.toString(), null, null);
    }

    public void land() {
        game.land();

        props.firePropertyChange(Type.STATE.toString(), null, null);
    }

    public void convert() {
        game.convert();

        props.firePropertyChange(Type.STATE.toString(), null, null);
    }

    public void convert(int resNew, int resOld) {
        game.convert(resNew, resOld);
    }

    public void convert(int type){
        game.convert(type);
    }

    public void nextTurn() {
        game.nextTurn();
        props.firePropertyChange(Type.STATE.toString(), null, null);
    }

    public void stopConvert() {
        game.stopConvert();
        props.firePropertyChange(Type.STATE.toString(), null, null);
    }

    public void landOnSS() {
        game.landOnSS();
        props.firePropertyChange(Type.STATE.toString(), null, null);
    }

    public void roll(int type) {
        game.roll(type);
        props.firePropertyChange(Type.STATE.toString(), null, null);
    }

    public void backToPlanet() {
        game.backToPlanet();
        props.firePropertyChange(Type.STATE.toString(), null, null);
    }

    public void makesDecision(int value) {
        game.makesDecision(value);
        props.firePropertyChange(Type.STATE.toString(), null, null);
    }

    public void end() {
        game.end();
        props.firePropertyChange(Type.STATE.toString(), null, null);
    }

    public void start() {
        game.start();
        props.firePropertyChange(Type.STATE.toString(), null, null);
    }
    //-----------------------------------

    //------------ LOAD/SAVE ------------

    public void saveGame(String filename) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(filename));
            oos.writeObject(game);
            oos.close();
        } catch (IOException e) {
            Logger.getLogger(DataGameObs.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void loadGame(String filename) {
        ObjectInputStream ois;
        try {
            ois = new ObjectInputStream(new FileInputStream(filename));
            game  = (GamePlay) ois.readObject();
            ois.close();
            props.firePropertyChange(Type.STATE.toString(), null, null);
        } catch (IOException | ClassNotFoundException e ) {
            Logger.getLogger(DataGameObs.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //-----------------------------------

    //--------- DATA RETREIVING ---------

    public String getMinerStats() {
        return "Mining:\n\t1 Weapons System - 9 cells\n\t1 Shield Systems - 18 cells\n\tFuel System - 53 cells\n\t" +
                "Cargo Hold:\n\t\t4 levels -\n\t\t1-6\n\t\t7-12\n\t\t13-18\n\t\t9-24\n\t\tof each resource\n";
    }

    public String getMilitaryStats() {
        return "Military:\n\t2 Weapons System - 9 cells each\n\t1 Shield Systems - 9 cells\n\tFuel System - 35 cells\n\t" +
                "Cargo Hold:\n\t\t2 levels - \n\t\t1-6\n\t\t7-12\n\t\tof each resource\n";
    }

    public String getShipText() {
        String s = "Your ship:\nWeapon System: " + game.getShip().getWeaponSystem() + "/"+ game.getShip().getMaxWeapon() + " cells\n" +
                "Shield System: " + game.getShip().getShieldSystem() + "/" + game.getShip().getMaxShield() + " cells\n" +
                "Fuel: ";

        if(game.isOutOfFuel())
            s += "0 / " + game.getShip().getMaxFuel() + " cells\n";
        else
            s += game.getShip().getFuel() + "/ " + game.getShip().getMaxFuel() + " cells\n";


        s += "Cargo Hold (Level " + game.getShip().getCargoHoldLvl() + " of " + game.getShip().getMaxLevel() + ")\n";

        for (int i = 0; i < game.getShip().getCargoHold().size(); i++) {
            s += "\t" + game.getShip().getCargoType().get(i) + ": " + game.getShip().getCargoHold().get(i) +
                    " of " + game.getShip().getMaxCargo() + "\n";
        }

        s+= "Artifacts: " + game.getShip().getNumArtifacts() + " of 5\n";

        if(game.hasDrone())
            s += "Drone: " + game.getShip().getDrone().getHp() + " of 6 Armor\n";
        else
            s += "Drone: Destroyed\n";

        s += "Officers:\n";
        for (int i = 0; i < game.getOfficers().size(); i++) {
            s += game.getPositions().get(i) + ": ";
            if(game.getOfficers().get(i))
                s += "Alive\n";
            else
                s += "Dead\n";
        }

        return s;
    }

    public String getRight(){
        return shipText;
    }

    public void setShipText(String s){
        shipText = s;

        props.firePropertyChange(Type.SHIP.toString(), null, null);
    }

    public boolean isFirstMove() {
        return game.isFirstMove();
    }

    public int getPlanet() {
        return game.getPlanet();
    }

    public String getPlanetAsString() {
        return game.getPlanetAsString();
    }

    public boolean hasSpaceStation() {
        return game.hasSpaceStation();
    }

    public String getSSText() {
        return game.getSSText();
    }

    public Ship getShip() {
        return game.getShip();
    }

    public String getConvertText() {
        return game.getConvertText();
    }

    public boolean hasPlanet() {
        return game.hasPlanet();
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String toTip) {
        tip = toTip;

        props.firePropertyChange(Type.TIP.toString(), null, null);
    }

    public void setInstruction(String s) {
        instruction = s;

        props.firePropertyChange(Type.INSTRUCTION.toString(), null, null);
    }

    public String getInstruction(){
        return instruction;
    }

    public boolean hasDrone() {
        return game.hasDrone();
    }

    public String getMiningResults() {
        return game.getMiningResults();
    }

    public boolean isExplorationAlive() {
        return game.isExplorationAlive();
    }

    public boolean isCargoAlive() {
        return game.isCargoAlive();
    }

    public void savePlanet() {
        game.savePlanet();
    }

    public DataGame getData() {
        return game.getData();
    }

    public int getBlackRes() {
        return game.getBlackRes();
    }

   public int getRedRes() {
        return game.getRedRes();
    }

   public int getBlueRes() {
        return game.getBlueRes();
    }

   public int getGreenRes() {
        return game.getGreenRes();
    }

    public boolean isDroneFull() {
        return game.isDroneFull();
    }

    public int getDroneHp() {
        return game.getDroneHp();
    }

    public int getResource(int index) {
        return game.getResource(index);
    }

    public int getMaxResource() {
        return game.getMaxResource();
    }

    public boolean isShieldFull() {
        return game.isShieldFull();
    }

    public int getShield() {
        return game.getShield();
    }

    public boolean isAmmoFull() {
        return game.isAmmoFull();
    }

    public int getAmmo() {
        return game.getAmmo();
    }

    public boolean isFuelFull() {
        return game.isFuelFull();
    }

    public int getFuel() {
        return game.getFuel();
    }

    public String getEvent() {
        return game.getEvent();
    }

    public boolean getWasWormhole() {
        return game.getWasWormhole();
    }

    public boolean isCargoMaxLevel() {
        return game.isCargoMaxLevel();
    }

    public boolean wasUpgradedThisTurn() {
        return game.wasUpgradedThisTurn();
    }

    public boolean isAllOfficersAlive() {
        return game.isAllOfficersAlive();
    }

    public boolean isWeaponMaxLevel() {
        return game.isWeaponMaxLevel();
    }

    public int getCargoHoldLvl() {
        return game.getCargoHoldLvl();
    }

    public boolean hasWon() {
        return game.hasWon();
    }
    //-----------------------------------
}
