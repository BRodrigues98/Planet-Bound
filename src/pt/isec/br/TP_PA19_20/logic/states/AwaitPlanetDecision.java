package pt.isec.br.TP_PA19_20.logic.states;

import pt.isec.br.TP_PA19_20.integration.StateID;
import pt.isec.br.TP_PA19_20.logic.data.DataGame;
import pt.isec.br.TP_PA19_20.logic.data.planet.BlackPlanet;
import pt.isec.br.TP_PA19_20.logic.data.planet.BluePlanet;
import pt.isec.br.TP_PA19_20.logic.data.planet.GreenPlanet;
import pt.isec.br.TP_PA19_20.logic.data.planet.RedPlanet;

public class AwaitPlanetDecision extends StateAdapter{
    public AwaitPlanetDecision(DataGame data) {
        super(data);
        //data.setState(this);
        if(data.getSavedPlanet() != null){
            data.setPlanet(data.getSavedPlanet());
            data.setSavedPlanet(null);
        }
        else {
            double rand = Math.random();
            if (rand <= 0.25)
                data.setPlanet(new GreenPlanet());
            else if (rand > 0.25 && rand <= 0.5)
                data.setPlanet(new BlackPlanet());
            else if (rand > 0.5 && rand >= 0.75)
                data.setPlanet(new RedPlanet());
            else
                data.setPlanet(new BluePlanet());

            data.addLogs(data.getPlanet().toString());
        }

    }

    @Override
    public IStates land() {
        if(!data.getOfficers().get(2)) { //Se não tiver exploration officer
            data.addLogs("You don't have an " + data.getPositions().get(2) +". You can't land.");
            return this;
        }
        else if(!data.getShip().isDrone()){
            data.addLogs("You don't have a Drone. You can't explore.");
            return this;
        }
        else {
            data.addLogs("Sending exploration drone to planet.");
            int mined = data.mine();
            if(mined == 3)
                return new LastChance(data);
            else if(mined == 0 || mined == 1) //Planet already fully mined || Alien destroyed drone
                return this;
            else if(mined == 4)
                return new GameOver(data);
            else if(data.getPlanet().getTimesMined() < data.getPlanet().getNumResources())
                return this;
            else
                return new AwaitMiningConfirmation(data);


        }

    }

    @Override
    public IStates nextTurn() {
        if(data.getShip().getNumArtifacts() < 5) {
            data.addLogs("Exiting planet");
            //return new AwaitMiningConfirmation(data);
            return new AwaitMovement(data);
        }
        else {
            data.addLogs("You've won the game! Congratulations.");
            return new GameOver(data);
        }
    }

    @Override
    public IStates landOnSS() {
        if(data.getPlanet().isSpaceStation()) {
            data.addLogs("Flying to Space Station");
            return new AwaitSSDecision(data);
        }
        else {
            data.addLogs("This planet does not have a Space Station in its orbit.");
            return this;
        }
    }

    @Override
    public IStates convert() {
        return new AwaitResourcesConversion(data);
    }


    @Override
    public StateID getStateID() {
        return StateID.AWAIT_PLANET_DECISON;
    }
}
