package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.logic.data.DataGame;


public class AwaitDiceRoll  extends StateAdapter{

    public AwaitDiceRoll(DataGame data) {
        super(data);
        data.setState(this);
    }

    @Override
    public IStates roll(int type) {
        int roll = data.roll(type);
        if(roll == 0)
            return new GameOver(data);
        else if(roll == -1){
            if(!data.getOfficers().get(data.getOfficers().size() - 1))
                return new GameOver(data);
            return new LastChance(data);
        }
        else if(roll == 1) {
            data.setWasRedDot(true);    //Resolve o bug de 2 rolls seguidos após conversão
            return new AwaitMovement(data);
        }
        else
            return this;
    }

    @Override
    public StateID getStateID() {
        return StateID.AWAIT_DICE_ROLL;
    }
}
