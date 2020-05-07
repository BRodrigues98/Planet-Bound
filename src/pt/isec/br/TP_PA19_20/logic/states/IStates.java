package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public interface IStates {
    IStates start(DataGame game);
    IStates selectShip(int value);
    IStates move(boolean firstMove);
    IStates nextTurn();
    IStates makesDecision(int choice);
    IStates landOnSS();
    IStates returnToShip();
    IStates land();
    IStates convert(int type);
    IStates convert(int resNew, int resOld);
    IStates roll(int type);
    IStates stopConvert();
    IStates checkLossConditions();
    //IStates extraConversion();
}
