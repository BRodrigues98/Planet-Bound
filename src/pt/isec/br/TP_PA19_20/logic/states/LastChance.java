package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class LastChance extends StateAdapter {

    public LastChance(DataGame data) {
        super(data);
        data.getShip().setFuel(0);
        //data.setState(this);
    }

    @Override
    public IStates end() {
        return new GameOver(data);
    }

    @Override
    public IStates convert(int type) {
        int converted = data.convert(type);
        if (converted == 0)
            return this;
        else
            return new AwaitResourcesConversion(data);
    }

    @Override
    public IStates convert(int resNew, int resOld) {
        int converted = data.convert(resNew, resOld);
        if (converted == 0)
            return this;
        else
            return new AwaitResourcesConversion(data);
    }

    @Override
    public StateID getStateID() {
        return StateID.LAST_CHANCE;
    }
}
