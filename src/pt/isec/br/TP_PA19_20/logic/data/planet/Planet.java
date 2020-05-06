package pt.isec.br.TP_PA19_20.logic.data.planet;

import pt.isec.br.TP_PA19_20.logic.data.planet.alien.Alien;

import java.util.List;

public abstract class Planet {
    protected boolean spaceStation;
    protected int numResources;
    protected int timesMined;
    //int type;
    /*
    0 - green planet
    1 - black planet
    2 - red planet
    3 - blue planet
     */
    protected List<String> typeResource;
    protected Alien alien;

    public Planet() {
        double rand = Math.random();
        if(rand <= 0.3)
            spaceStation = true;
        else
            spaceStation = false;

        /*
        rand = Math.random();
        if(rand <= 0.25)
            type = 0;
        else if(rand > 0.25 && rand <= 0.5)
            type = 1;
        else if(rand > 0.5 && rand < 0.75)
            type = 2;
        else
            type = 3;

         */
        alien = null;
        timesMined = 0;
    }


    //------------ GETTERS/SETTERS ------------

    public boolean isSpaceStation() { return spaceStation; }

    public void setSpaceStation(boolean spaceStation) { this.spaceStation = spaceStation; }

    /*
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
     */

    public int getNumResources() { return numResources; }

    public void setNumResources(int numResources) { this.numResources = numResources; }

    public List<String> getTypeResource() { return typeResource; }

    public void setTypeResource(List<String> typeResource) { this.typeResource = typeResource; }

    public Alien getAlien() { return alien; }

    public void setAlien(Alien alien) { this.alien = alien; }

    public int getTimesMined() { return timesMined; }

    public void setTimesMined(int timesMined) { this.timesMined = timesMined; }

    //-----------------------------------------


    @Override
    public String toString() {
        return "Planet{" +
                "spaceStation=" + spaceStation +
                ", numResources=" + numResources +
                '}';
    }
}
