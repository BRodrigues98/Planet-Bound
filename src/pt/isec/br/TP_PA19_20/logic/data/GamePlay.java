package pt.isec.br.TP_PA19_20.logic.data;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.integration.Type;
import pt.isec.br.TP_PA19_20.logic.data.ship.Ship;
import pt.isec.br.TP_PA19_20.logic.states.AwaitSpaceshipSelection;
import pt.isec.br.TP_PA19_20.logic.states.IStates;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class GamePlay implements Serializable {
    private DataGame data;
    private IStates state;

    public GamePlay(){
        data = new DataGame();
        state = new AwaitSpaceshipSelection(data);
    }

    //------------ CHANGE STATE ------------
    public StateID getStateID(){
        return state.getStateID();
    }

    public void setShip(int value) { state = state.selectShip(value); }

    public void restart() {
        state = state.restart();
    }

    public void move(boolean firstMove) {
        state = state.move(firstMove);
    }

    public void land() {
        state = state.land();
    }

    public void nextTurn() {
        state = state.nextTurn();
    }

    public void convert() {
        state = state.convert();
    }

    public void convert(int resNew, int resOld) {
        state = state.convert(resNew, resOld);
    }

    public void convert(int type){
        state = state.convert(type);
    }

    public void stopConvert() {
        state = state.stopConvert();
    }

    public void landOnSS() {
        state = state.landOnSS();
    }


    //------------------------------------

    //--------- DATA RETREIVING ----------
    public boolean isFirstMove() {
        return data.isFirstMove();
    }

    public Ship getShip() {
        return data.getShip();
    }

    public int getPlanet() {
        if(data.getPlanet() != null)
            return data.getPlanet().asInt();
        else
            return -1;
    }

    public String getPlanetAsString() {
        return data.getPlanet().toString() + "\n" + "Mined " + data.getPlanet().getTimesMined() + " out of " + data.getPlanet().getNumResources();
    }

    public boolean hasSpaceStation() {
        return data.getPlanet().isSpaceStation();
    }

    public String getSSText() {
        return data.getSSText();
    }

    public String getConvertText() {
        return data.getConvertText();
    }

    public boolean hasPlanet() {
        return data.getPlanet() != null;
    }

    public boolean wasFullyMined() {
        if(data.getPlanet().getTimesMined() == data.getPlanet().getNumResources())
            return true;
        else
            return false;
    }

    public boolean hasDrone() {
        return data.getShip().isDrone();
    }

    public String getMiningResults() {
        if(data.getShip().getDrone().getResourceFound() != null)
            if(!data.getShip().getDrone().getResourceFound().equals("artifact"))
                return "Your drone found a " + data.getShip().getDrone().getResourceFound() + " resource";
            else
                return "Your drone found an artifact! You need " + (5 - data.getShip().getNumArtifacts()) + " to win the game!";
        else
            return null;
    }

    public boolean isExplorationAlive() {
        return data.getOfficers().get(2);
    }

    public void savePlanet() {
        data.setSavedPlanet(data.getPlanet());
    }

    public DataGame getData() {
        return data;
    }

    /*
    0 - Black resource
    1 - Red resource
    2 - Blue resource
    3 - Green resource
     */

    public int getBlackRes() {
        return data.getShip().getCargoHold().get(0);
    }

    public int getRedRes() {
        return data.getShip().getCargoHold().get(1);
    }

    public int getBlueRes() {
        return data.getShip().getCargoHold().get(2);
    }

    public int getGreenRes() {
        return data.getShip().getCargoHold().get(3);
    }

    public boolean isDroneFull() {
        return data.getShip().getDrone().getHp() == 6;
    }

    public int getDroneHp() {
        return data.getShip().getDrone().getHp();
    }

    public int getResource(int index) {
        return data.getShip().getCargoHold().get(index);
    }

    public int getMaxResource() {
        return data.getShip().getMaxCargo();
    }

    public boolean isCargoAlive() {
        return data.getOfficers().get(5);
    }

    public boolean isShieldFull() {
        return data.getShip().getShieldSystem() == data.getShip().getMaxShield();
    }

    public int getShield() {
        return data.getShip().getShieldSystem();
    }

    public boolean isAmmoFull() {
        return data.getShip().getWeaponSystem() == data.getShip().getMaxWeapon();
    }

    public int getAmmo() {
        return data.getShip().getWeaponSystem();
    }

    public boolean isFuelFull() {
        return data.getShip().getFuel() == data.getShip().getMaxFuel();
    }

    public int getFuel() {
        return data.getShip().getFuel();
    }

    //------------------------------------
}
