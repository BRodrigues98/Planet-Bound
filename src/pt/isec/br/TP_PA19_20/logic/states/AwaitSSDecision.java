package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;
import pt.isec.br.TP_PA19_20.logic.data.ship.Mining;

public class AwaitSSDecision extends StateAdapter{
    boolean cargoUpgradedThisTurn;
    public AwaitSSDecision(DataGame game) {
        super(game);

        cargoUpgradedThisTurn = false;
    }

    public boolean isCargoUpgradedThisTurn() { return cargoUpgradedThisTurn; }

    public void setCargoUpgradedThisTurn(boolean cargoUpgradedThisTurn) { this.cargoUpgradedThisTurn = cargoUpgradedThisTurn; }

    @Override
    public IStates makesDecision(int choice) {
         //game.debug();
         //game.getShip().debug();
        if(choice == 1 && cargoUpgradedThisTurn){
            game.addLogs("You can't upgrade your cargo again.");
            return this;
        }

        int decided = game.makesDecision(choice);

        if (choice == 1 && decided == 1) //upgrade cargo hold
            cargoUpgradedThisTurn = true;

        if(decided == 1){
            return this;
        }
        else
            return this;
    }

    @Override
    public IStates backToPlanet() {
        if(game.getState() instanceof AwaitMiningConfirmation) {
            game.setState(null);
            return new AwaitDiceRoll(game);
        }
        else
            return new AwaitPlanetDecision(game);
    }
}
