package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class AwaitRestart extends StateAdapter {
    public AwaitRestart(DataGame data) {
        super(data);
    }

    @Override
    public IStates restart() {
        return new AwaitSpaceshipSelection(data);
    }

    @Override
    public StateID getStateID() {
        return StateID.AWAIT_RESTART;
    }
}
