package pt.isec.br.TP_PA19_20.logic.data.planet;

import java.util.ArrayList;

public class BluePlanet extends Planet {
    boolean foundArtifact;
    public BluePlanet() {
        super();
        numResources = 4;
        typeResource = new ArrayList<>();
        typeResource.add("black");
        typeResource.add("green");
        typeResource.add("blue");
        typeResource.add("artifact");
        foundArtifact = false;
    }

    public boolean isFoundArtifact() { return foundArtifact; }

    public void setFoundArtifact(boolean foundArtifact) { this.foundArtifact = foundArtifact; }

    @Override
    public String toString() {
        return "Blue Planet:\n" + (numResources - 1) + " resources of type black, green, blue and an artifact";
    }

    @Override
    public int asInt() {
        return 1;
    }
}
