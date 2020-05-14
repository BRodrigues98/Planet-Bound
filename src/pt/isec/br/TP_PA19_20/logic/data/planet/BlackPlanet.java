package pt.isec.br.TP_PA19_20.logic.data.planet;

import pt.isec.br.TP_PA19_20.ui.Colors;

import java.util.ArrayList;

public class BlackPlanet extends Planet {
    public BlackPlanet() {
        super();
        numResources = 2;
        typeResource = new ArrayList<>();
        typeResource.add(Colors.ANSI_BLACK + "black" + Colors.ANSI_RESET);
        typeResource.add(Colors.ANSI_BLUE + "blue" + Colors.ANSI_RESET);

    }

    @Override
    public String toString() {
        return "You've found a " + Colors.ANSI_BLACK + " Black Planet." + Colors.ANSI_RESET + " You can find " + numResources + " resources here of type "  + Colors.ANSI_BLACK +
                "black" + Colors.ANSI_RESET + " and" + Colors.ANSI_BLUE + " blue" + Colors.ANSI_RESET;
    }
}
