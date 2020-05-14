package pt.isec.br.TP_PA19_20.logic.data.planet;

import pt.isec.br.TP_PA19_20.ui.Colors;

import java.util.ArrayList;

public class GreenPlanet extends Planet {
    public GreenPlanet() {
        super();
        numResources = 2;
        typeResource = new ArrayList<>();
        typeResource.add(Colors.ANSI_RED + "red" + Colors.ANSI_RESET);
        typeResource.add(Colors.ANSI_GREEN + "green" + Colors.ANSI_RESET);

    }

    @Override
    public String toString() {
        return "You've found a " + Colors.ANSI_GREEN + "Green Planet" + Colors.ANSI_RESET + ". You can find " + numResources + " resources here of type "
                + Colors.ANSI_RED + "red" + Colors.ANSI_RESET + " and " + Colors.ANSI_GREEN + "green" + Colors.ANSI_RESET;
    }
}
