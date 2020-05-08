package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class AwaitMiningConfirmation extends StateAdapter{
    public AwaitMiningConfirmation(DataGame game) {
        super(game);
    }

    @Override
    public IStates returnToShip() {
        return new AwaitMovement(game);
    }


}
