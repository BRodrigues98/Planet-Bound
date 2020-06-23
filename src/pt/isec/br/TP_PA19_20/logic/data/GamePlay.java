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

    //------------------------------------
}
