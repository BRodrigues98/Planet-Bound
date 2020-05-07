package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;
import pt.isec.br.TP_PA19_20.logic.data.planet.Planet;
import pt.isec.br.TP_PA19_20.logic.data.ship.Military;
import pt.isec.br.TP_PA19_20.logic.data.ship.Mining;

import java.util.List;

public class GameToStates {
    DataGame game;
    IStates state;

    public GameToStates() {
        this.game = new DataGame();
        this.state = new AwaitSpaceshipSelection(game);
    }

    public DataGame getGame() { return game; }

    public void setGame(DataGame game) { this.game = game; }

    public IStates getState() { return state; }

    public void setState(IStates state) { this.state = state; }

    public List<String> getLogs() { return game.getLogs(); }

    public void clearLogs() { game.clearLogs(); }

    public void selectShip(int value) { this.state = state.selectShip(value); }

    public void getShipsStats() { game.getShipStats(); }

    public boolean isFirstMove() { return game.isFirstMove(); }

    public void move(boolean firstMove) { this.state = state.move(firstMove); }

    public boolean isSpaceStation() { return game.getPlanet().isSpaceStation(); }

    public void land() { this.state = state.land(); }

    public void landOnSS() { this.state = state.landOnSS(); }

    public void nextTurn() { this.state = state.nextTurn(); }

    public String getDroneResource() { return game.getShip().getDrone().getResourceFound(); }

    public int getNumArtifacts() { return game.getShip().getNumArtifacts(); }

    public void roll(int type) { this.state = state.roll(type); }

    public void debug(int i) {
        if(i == 1){
            this.state = new AwaitDiceRoll(game);
        }
    }

    public void returnToShip() { this.state = state.returnToShip(); }

    public void convert(int type) { this.state = state.convert(type); }

    public void convert(int resNew, int resOld) { this.state = state.convert(resNew, resOld); }

    public int getDroneArmor() { return game.getShip().getDrone().getHp(); }

    public int getShipShield() { return game.getShip().getShieldSystem(); }

    public int getShipAmmo() { return game.getShip().getWeaponSystem(); }

    public int getShipFuel() { return game.getShip().getFuel(); }

    public void stopConvert() { this.state = state.stopConvert(); }

    public void makesDecision(int value) { this.state = state.makesDecision(value); }

    public void start() { this.state = state.start(game); }

    public void currentShipStats() { game.currentShipStats(); }

    public void checkLossConditions() { this.state = state.checkLossConditions();}


    //public void extraConversion() { this.state = state.extraConversion();}

    //public String getPosition() { return game.getPosition(); }
}
