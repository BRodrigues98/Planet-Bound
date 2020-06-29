package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;

import java.util.List;

public class GameToStates {
    DataGame data;
    IStates state;

    public GameToStates() {
        this.data = new DataGame();
        this.state = new AwaitSpaceshipSelection(data);
    }

    public DataGame getGame() { return data; }

    public void setGame(DataGame data) { this.data = data; }

    public IStates getState() { return state; }

    public void setState(IStates state) { this.state = state; }

    public List<String> getLogs() { return data.getLogs(); }

    public void clearLogs() { data.clearLogs(); }

    public void selectShip(int value) { this.state = state.selectShip(value); }

    public void getShipsStats() { data.getShipStats(); }

    public boolean isFirstMove() { return data.isFirstMove(); }

    public void move(boolean firstMove) { this.state = state.move(firstMove); }

    public boolean isSpaceStation() { return data.getPlanet().isSpaceStation(); }

    public void land() { this.state = state.land(); }

    public void landOnSS() { this.state = state.landOnSS(); }

    public void nextTurn() { this.state = state.nextTurn(); }

    public String getDroneResource() {
        if(data.getShip().getDrone() == null)
            return null;

        return data.getShip().getDrone().getResourceFound();
    }

    public int getNumArtifacts() { return data.getShip().getNumArtifacts(); }

    public void roll(int type) { this.state = state.roll(type); }

    public void debug(int i) {
        if(i == 1){
            this.state = new AwaitDiceRoll(data);
        }
    }

    public void convert(int type) { this.state = state.convert(type); }

    public void convert(int resNew, int resOld) { this.state = state.convert(resNew, resOld); }

    public int getDroneArmor() {
        if(data.getShip().getDrone() == null)
            return 0;
        else
            return data.getShip().getDrone().getHp();
    }

    public int getShipShield() { return data.getShip().getShieldSystem(); }

    public int getShipAmmo() { return data.getShip().getWeaponSystem(); }

    public int getShipFuel() { return data.getShip().getFuel(); }

    public void stopConvert() { this.state = state.stopConvert(); }

    public void makesDecision(int value) { this.state = state.makesDecision(value); }

    public void start() { this.state = state.start(data); }

    public void currentShipStats() { data.currentShipStats(); }

    public int getTimesMined() { return data.getPlanet().getTimesMined(); }

    public int getNumResourcesOnPlanet() { return data.getPlanet().getNumResources(); }

    public void savePlanet() { data.setSavedPlanet(data.getPlanet());}

    public void backToPlanet() { this.state = state.backToPlanet(); }

    public void setLastChance() { data.setAlreadyHadChance(true);}

    public int getFinalScore() { return data.getFinalScore();}

    public void saveScore(int finalScore, String username) { data.saveScore(finalScore, username);}

    public boolean getLastChance() { return data.isAlreadyHadChance(); }

    public void end() { this.state = state.end(); }

    public String readScores() { return data.readScores(); }
}
