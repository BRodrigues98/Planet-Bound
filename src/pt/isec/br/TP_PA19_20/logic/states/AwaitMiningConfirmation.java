package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class AwaitMiningConfirmation extends StateAdapter{
    public AwaitMiningConfirmation(DataGame data) {
        super(data);
    }

    @Override
    public IStates convert() {
        return new AwaitResourcesConversion(data);
    }

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
    public StateID getStateID() {
        return StateID.AWAIT_MINING_CONFIRMATION;
    }
}
