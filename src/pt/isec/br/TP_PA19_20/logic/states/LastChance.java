package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class LastChance extends StateAdapter {

    public LastChance(DataGame game) {
        super(game);
        game.getShip().setFuel(0);
        //game.setState(this);
    }

    @Override
    public IStates end() {
        return new GameOver(game);
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
