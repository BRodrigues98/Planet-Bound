package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class AwaitMovement extends StateAdapter{

    public AwaitMovement(DataGame game) {
        super(game);

    }

    @Override
    public IStates move(boolean firstMove) {
        if(firstMove){
            game.setFirstMove(false);
            //game.setPreviousPosition(game.getPosition());
            //game.setPosition("planet");
            return new AwaitPlanetDecision(game);
        }
        else{
            int whereTo = game.whereTo();
            if(whereTo == 0)
                return new GameOver(game);
            else if(whereTo == 1)
                return new AwaitDiceRoll(game);
            else
                return this;
        }
    }
}
