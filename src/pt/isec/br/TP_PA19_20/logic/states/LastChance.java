package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class LastChance extends StateAdapter {

    public LastChance(DataGame data) {
        super(data);
    }

    @Override
    public IStates end() {
        return new GameOver(data);
    }

    @Override
    public IStates convert() {
        return new AwaitResourcesConversion(data);
    }

    @Override
    public StateID getStateID() {
        return StateID.LAST_CHANCE;
    }
}
