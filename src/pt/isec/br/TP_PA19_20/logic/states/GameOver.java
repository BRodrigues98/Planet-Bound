package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class GameOver extends StateAdapter{
    public GameOver(DataGame game) {
        super(game);
    }

    @Override
    public IStates start(DataGame game) {
        game.init();
        return new AwaitSpaceshipSelection(game);
    }
}
