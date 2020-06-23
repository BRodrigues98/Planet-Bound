package pt.isec.br.TP_PA19_20.logic.data.planet;

import pt.isec.br.TP_PA19_20.ui.Colors;

import java.util.ArrayList;

public class BluePlanet extends Planet {
    boolean foundArtifact;
    public BluePlanet() {
        super();
        numResources = 4;
        typeResource = new ArrayList<>();
        typeResource.add(Colors.ANSI_BLACK + "black" + Colors.ANSI_RESET);
        typeResource.add(Colors.ANSI_GREEN + "green" + Colors.ANSI_RESET);
        typeResource.add(Colors.ANSI_BLUE + "blue" + Colors.ANSI_RESET);
        typeResource.add(Colors.ANSI_PURPLE + "artifact" + Colors.ANSI_RESET);
        foundArtifact = false;
    }

    public boolean isFoundArtifact() { return foundArtifact; }

    public void setFoundArtifact(boolean foundArtifact) { this.foundArtifact = foundArtifact; }

    @Override
    public String toString() {
        return "You've found a " + Colors.ANSI_BLUE + "Blue Planet" + Colors.ANSI_RESET + ". You can find " + (numResources - 1) + " resources here of type"
                + Colors.ANSI_BLACK + " black" + Colors.ANSI_RESET + ", " + Colors.ANSI_GREEN + "green" + Colors.ANSI_RESET + " , " + Colors.ANSI_BLUE + "blue" + Colors.ANSI_RESET
        + " and an " + Colors.ANSI_PURPLE + "artifact" + Colors.ANSI_RESET;
    }
}
