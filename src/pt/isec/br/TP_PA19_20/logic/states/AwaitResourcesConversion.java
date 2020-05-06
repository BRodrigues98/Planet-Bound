package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class AwaitResourcesConversion extends StateAdapter{
    public AwaitResourcesConversion(DataGame game) {
        super(game);
    }

    @Override
    public IStates stopConvert() {
        game.addLogs("Going back to main deck");
        return new AwaitPlanetDecision(game);
    }
}
