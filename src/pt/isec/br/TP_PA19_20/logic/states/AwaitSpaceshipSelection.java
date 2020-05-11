package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;
import pt.isec.br.TP_PA19_20.logic.data.ship.Military;
import pt.isec.br.TP_PA19_20.logic.data.ship.Mining;

public class AwaitSpaceshipSelection extends StateAdapter{
    public AwaitSpaceshipSelection(DataGame game) {
        super(game);
    }


    @Override
    public IStates selectShip(int value) {
        if(value == 1){
            game.setShip(new Mining());
        }
        else if(value == 2){
            game.setShip(new Military());
        }

        //game.setPosition("stopped");
        game.getShip().setFuel(0);
        return new AwaitMovement(game);
    }
}
