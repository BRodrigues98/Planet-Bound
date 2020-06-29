package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.logic.data.DataGame;

import java.io.Serializable;

public interface IStates extends Serializable {
    IStates start(DataGame data);
    IStates selectShip(int value);
    IStates move(boolean firstMove);
    IStates nextTurn();
    IStates makesDecision(int choice);
    IStates landOnSS();
    IStates land();
    IStates convert();
    IStates convert(int type);
    IStates convert(int resNew, int resOld);
    IStates roll(int type);
    IStates stopConvert();
    IStates backToPlanet();
    IStates end();
    IStates restart();

    StateID getStateID();



    //IStates extraConversion();
}
