package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class AwaitResourcesConversion extends StateAdapter{
    public AwaitResourcesConversion(DataGame data) {
        super(data);
    }

    @Override
    public IStates stopConvert() {
        data.addLogs("Going back to main deck");
        //return new AwaitDiceRoll(data);
        if(data.getPlanet().getTimesMined() == data.getPlanet().getNumResources())
            return new AwaitMiningConfirmation(data);
        else if(data.getShip().getFuel() <= 0)
            return new LastChance(data);
        else
            return new AwaitPlanetDecision(data);
    }

    @Override
    public IStates convert(int type) {
        int converted = data.convert(type);
        return this;
    }

    @Override
    public IStates convert(int resNew, int resOld) {
        int converted = data.convert(resNew, resOld);

        return this;
    }

    @Override
    public StateID getStateID() {
        return StateID.AWAIT_RESOURCES_CONVERSION;
    }
}
