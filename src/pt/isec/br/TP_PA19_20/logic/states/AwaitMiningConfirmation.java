package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class AwaitMiningConfirmation extends StateAdapter{
    public AwaitMiningConfirmation(DataGame data) {
        super(data);
        data.setState(this);
    }

    @Override
    public IStates returnToShip() {
        return new AwaitMovement(data);
    }

    @Override
    public IStates convert() {
        return new AwaitResourcesConversion(data);
    }

    /*
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
    */

    @Override
    public IStates nextTurn() {
        if(data.getShip().getNumArtifacts() < 5) {
            data.addLogs("Exiting planet");
            data.setWasRedDot(true);
            return new AwaitDiceRoll(data);
        }
        else {
            data.addLogs("You've won the game! Congratulations.");
            return new GameOver(data);
        }
    }

    @Override
    public IStates landOnSS() {
        if(data.getPlanet().isSpaceStation()) {
            data.addLogs("Flying to Space Station");
            return new AwaitSSDecision(data);
        }
        else {
            data.addLogs("This planet does not have a Space Station in its orbit.");
            return this;
        }
    }

    @Override
    public StateID getStateID() {
        return StateID.AWAIT_MINING_CONFIRMATION;
    }
}
