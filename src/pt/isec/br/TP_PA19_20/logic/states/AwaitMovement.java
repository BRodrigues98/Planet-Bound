package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class AwaitMovement extends StateAdapter{

    public AwaitMovement(DataGame data) {
        super(data);
        //data.setState(this);
    }

    @Override
    public IStates move(boolean firstMove) {
        if(firstMove){
            data.setFirstMove(false);
            //data.setPreviousPosition(data.getPosition());
            //data.setPosition("planet");

            return new AwaitPlanetDecision(data);
        }
        else{
            int whereTo = data.whereTo();
            if(whereTo == 0)
                return new GameOver(data);
            else if(whereTo == 1)
                return new AwaitDiceRoll(data);
            else if(whereTo == 2)
                return new AwaitPlanetDecision(data);
            else if(whereTo == -1) {
                if(!data.getOfficers().get(data.getOfficers().size() - 1))
                    return new GameOver(data);
                return new LastChance(data);
            }
            else
                return this;
        }
    }

    @Override
    public StateID getStateID() {
        return StateID.AWAIT_MOVEMENT;
    }

}
