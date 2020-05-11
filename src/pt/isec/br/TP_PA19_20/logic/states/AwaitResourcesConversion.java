package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class AwaitResourcesConversion extends StateAdapter{
    public AwaitResourcesConversion(DataGame game) {
        super(game);
    }

    @Override
    public IStates stopConvert() {
        game.addLogs("Going back to main deck");
        //return new AwaitDiceRoll(game);
        if(game.getState() instanceof AwaitPlanetDecision)
            return new AwaitPlanetDecision(game);
        else if(game.getState() instanceof AwaitMiningConfirmation)
            return new AwaitMiningConfirmation(game);
        else
            return this;
    }

    @Override
    public IStates convert(int type) {
        int converted = game.convert(type);
        if (converted == 0)
            return this;
        else
            return new AwaitResourcesConversion(game);
    }

    @Override
    public IStates convert(int resNew, int resOld) {
        int converted = game.convert(resNew, resOld);
        if (converted == 0)
            return this;
        else
            return new AwaitResourcesConversion(game);
    }
}
