package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;

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
        if(cargoUpgradedThisTurn){
            game.addLogs("You can't upgrade your cargo again.");
            return this;
        }

        int decided = game.makesDecision(choice);

        if (choice == 1) //upgrade cargo hold
            cargoUpgradedThisTurn = true;

        if(decided == 1){
            return new AwaitPlanetDecision(game);
        }
        else
            return this;
    }
}
