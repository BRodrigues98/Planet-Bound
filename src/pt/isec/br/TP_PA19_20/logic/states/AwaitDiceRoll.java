package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;


public class AwaitDiceRoll  extends StateAdapter{

    public AwaitDiceRoll(DataGame game) {
        super(game);

    }

    @Override
    public IStates roll(int type) {
        int roll = game.roll(type);
        if(roll == 0)
            return new GameOver(game);
        else if(roll == 1) {
            game.setWasRedDot(true);    //Resolve o bug de 2 rolls seguidos após conversão
            return new AwaitMovement(game);
        }
        else
            return this;
    }

}
