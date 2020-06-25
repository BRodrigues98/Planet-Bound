package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.logic.data.DataGame;
import pt.isec.br.TP_PA19_20.logic.data.ship.Military;
import pt.isec.br.TP_PA19_20.logic.data.ship.Mining;

public class AwaitSpaceshipSelection extends StateAdapter{
    public AwaitSpaceshipSelection(DataGame data) {
        super(data);
        data.setState(this);
    }


    @Override
    public IStates selectShip(int value) {
        if(value == 1){
            data.setShip(new Mining());
        }
        else if(value == 2){
            data.setShip(new Military());
        }

        return new AwaitMovement(data);
    }

    @Override
    public StateID getStateID() {
        return StateID.AWAIT_SPACESHIP_SELECTION;
    }
}
