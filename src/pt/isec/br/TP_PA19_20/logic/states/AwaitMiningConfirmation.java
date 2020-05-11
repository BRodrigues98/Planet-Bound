package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;

public class AwaitMiningConfirmation extends StateAdapter{
    public AwaitMiningConfirmation(DataGame game) {
        super(game);
        game.setState(this);
    }

    @Override
    public IStates returnToShip() {
        return new AwaitMovement(game);
    }

    @Override
    public IStates convert(int type) {
        int converted = game.convert(type);
        if (converted == 0)
            return this;
        else
            return new AwaitResourcesConversion(game);
    }

    @Override
    public IStates convert(int resNew, int resOld) {
        int converted = game.convert(resNew, resOld);
        if (converted == 0)
            return this;
        else
            return new AwaitResourcesConversion(game);
    }

    @Override
    public IStates nextTurn() {
        if(game.getShip().getNumArtifacts() < 5) {
            game.addLogs("Exiting planet");
            game.setWasRedDot(true);
            return new AwaitDiceRoll(game);
        }
        else {
            game.addLogs("You've won the game! Congratulations.");
            return new GameOver(game);
        }
    }

    @Override
    public IStates landOnSS() {
        if(game.getPlanet().isSpaceStation()) {
            game.addLogs("Flying to Space Station");
            return new AwaitSSDecision(game);
        }
        else {
            game.addLogs("This planet does not have a Space Station in its orbit.");
            return this;
        }
    }
}
