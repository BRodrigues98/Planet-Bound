package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.logic.data.DataGame;


public abstract class StateAdapter implements IStates {
    protected DataGame data;

    public StateAdapter(DataGame data) { this.data = data; }

    @Override
    public IStates start(DataGame data) { return this; }

    @Override
    public IStates selectShip(int value) { return this; }

    @Override
    public IStates move(boolean firstMove) { return this; }

    @Override
    public IStates nextTurn() { return this; }

    @Override
    public IStates makesDecision(int choice) { return this; }

    @Override
    public IStates landOnSS() { return this; }

    @Override
    public IStates land() { return this; }

    //Passar para estado AwaitResourceConversion
    @Override
    public IStates convert() { return this; }

    //Dentro do AwaitResourceConversion
    @Override
    public IStates convert(int type) { return this; }

    //Dentro do AwaitResourceConversion
    @Override
    public IStates convert(int resNew, int resOld) { return this; }

    @Override
    public IStates roll(int type) { return this; }

    @Override
    public IStates stopConvert() { return this; }

    @Override
    public IStates backToPlanet() { return this; }

    @Override
    public IStates end() { return this; }

    @Override
    public StateID getStateID(){
        return StateID.GAME_OVER;
    }

    @Override
    public IStates restart() {
        data.init();
        return new AwaitSpaceshipSelection(data);
    }
}
