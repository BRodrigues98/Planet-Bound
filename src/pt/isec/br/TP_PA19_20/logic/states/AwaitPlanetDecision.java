package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;
import pt.isec.br.TP_PA19_20.logic.data.planet.*;

public class AwaitPlanetDecision extends StateAdapter{
    public AwaitPlanetDecision(DataGame game) {
        super(game);
        double rand = Math.random();
        if(rand <= 0.25)
            game.setPlanet(new GreenPlanet());
        else if(rand > 0.25 && rand <= 0.5)
            game.setPlanet(new BlackPlanet());
        else if(rand > 0.5 && rand >= 0.75)
            game.setPlanet(new RedPlanet());
        else
            game.setPlanet(new BluePlanet());

        game.addLogs(game.getPlanet().toString());
    }

    @Override
    public IStates land() {
        if(!game.getOfficers().get(2)) { //Se não tiver exploration officer
            game.addLogs("You don't have an Exploration Officer. You can't land.");
            return this;
        }
        else if(!game.getShip().isDrone()){
            game.addLogs("You don't have a Drone. You can't explore.");
            return this;
        }
        else {
            game.addLogs("Sending exploration drone to planet.");
            int mined = game.mine();
            if(mined == 0 || mined == 1) //Planet already fully mined || Alien destroyed drone
                return this;
            else
                return new AwaitMiningConfirmation(game);


        }

    }

    @Override
    public IStates nextTurn() {
        if(game.getShip().getNumArtifacts() < 5) {
            game.addLogs("Exiting planet");
            return new AwaitMovement(game);
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
}
