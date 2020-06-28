package pt.isec.br.TP_PA19_20.logic.data.planet;

import pt.isec.br.TP_PA19_20.logic.data.planet.alien.Alien;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public abstract class Planet implements Serializable {
    protected boolean spaceStation;
    protected int numResources;
    protected int timesMined;
    /*
    0 - green planet
    1 - black planet
    2 - red planet
    3 - blue planet
     */
    protected List<String> typeResource;
    protected Alien alien;

    //------------ CONSTRUCTOR ------------
    public Planet() {
        double rand = Math.random();
        if(rand <= 0.3)
            spaceStation = true;
        else
            spaceStation = false;

        alien = null;
        timesMined = 0;
    }
    //-------------------------------------

    //------------ GETTERS/SETTERS ------------

    public boolean isSpaceStation() { return spaceStation; }

    public void setSpaceStation(boolean spaceStation) { this.spaceStation = spaceStation; }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return spaceStation == planet.spaceStation &&
                numResources == planet.numResources &&
                timesMined == planet.timesMined &&
                Objects.equals(typeResource, planet.typeResource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spaceStation, numResources, timesMined, typeResource);
    }

    @Override
    public String toString() {
        return "Planet{" +
                "spaceStation=" + spaceStation +
                ", numResources=" + numResources +
                '}';
    }

    public abstract int asInt();
}
