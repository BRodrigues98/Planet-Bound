package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;


public abstract class StateAdapter implements IStates {
    protected DataGame game;

    public StateAdapter(DataGame game) { this.game = game; }

    public DataGame getGame() { return game; }

    public void setGame(DataGame game) { this.game = game; }

    @Override
    public IStates start(DataGame game) { return this; }

    @Override
    public IStates selectShip(int value) { return this; }

    @Override
    public IStates move(boolean firstMove) { return this; }

    @Override
    public IStates nextTurn() { return this; }

    @Override
    public IStates makesDecision(int choice) { return this; }

    @Override
    public IStates landOnSS() { return this; }

    @Override
    public IStates returnToShip() { return this; }

    @Override
    public IStates land() { return this; }

    @Override
    public IStates convert(int type) { return this; }

    @Override
    public IStates convert(int resNew, int resOld) { return this; }

    @Override
    public IStates roll(int type) { return this; }

    @Override
    public IStates stopConvert() { return this; }

    @Override
    public IStates checkLossConditions() {
        if(game.getShip() != null) {
            if(!game.getOfficers().get(0))
                return new GameOver(game);
            else if (game.getShip().getFuel() <= 0 && !game.isAlreadyHadChance())
                return new LastChance(game);
            else if (game.getShip().getFuel() <= 0)
                return new GameOver(game);
            else
                return this;
        }
        return this;
    }

    @Override
    public IStates backToPlanet() { return this; }

    @Override
    public IStates lastChance() { return this; }

    @Override
    public IStates end() { return this; }

    //@Override
    //public IStates extraConversion() { return this; }

}
