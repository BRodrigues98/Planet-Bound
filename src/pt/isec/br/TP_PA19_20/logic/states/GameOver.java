package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class GameOver extends StateAdapter{
    public GameOver(DataGame data) {
        super(data);
    }

    @Override
    public IStates start(DataGame data) {
        data.init();
        return new AwaitSpaceshipSelection(data);
    }

    @Override
    public StateID getStateID() {
        return StateID.GAME_OVER;
    }
}
