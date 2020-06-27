package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class AwaitSSDecision extends StateAdapter{

    public AwaitSSDecision(DataGame data) {
        super(data);
        data.setState(this);
        data.setCargoUpgradedThisTurn(false);
    }

    @Override
    public IStates makesDecision(int choice) {
         //data.debug();
         //data.getShip().debug();
        if(choice == 1 && data.isCargoUpgradedThisTurn()){
            data.addLogs("You can't upgrade your cargo again.");
            return this;
        }

        int decided = data.makesDecision(choice);

        if (choice == 1 && decided == 1) //upgrade cargo hold
            data.setCargoUpgradedThisTurn(true);

        return this;
    }

    @Override
    public IStates backToPlanet() {
        return new AwaitPlanetDecision(data);
    }

    @Override
    public StateID getStateID() {
        return StateID.AWAIT_SS_DECISION;
    }
}
