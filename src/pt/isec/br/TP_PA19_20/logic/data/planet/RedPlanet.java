package pt.isec.br.TP_PA19_20.logic.data.planet;

import java.util.ArrayList;
import java.util.List;

public class RedPlanet extends Planet {
    public RedPlanet() {
        super();
        numResources = 2;
        typeResource = new ArrayList<>();
        typeResource.add("red");
        typeResource.add("blue");

    }

    @Override
    public String toString() {
        return "You've found a Red Planet. You can find " + numResources + " resources here of type red and blue";
    }
}
