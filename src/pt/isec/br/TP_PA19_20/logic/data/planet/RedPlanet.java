package pt.isec.br.TP_PA19_20.logic.data.planet;

import pt.isec.br.TP_PA19_20.ui.text.Colors;

import java.util.ArrayList;

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
        return "Red Planet:\n" + numResources + " resources of type red and blue";
    }

    @Override
    public int asInt() {
        return 3;
    }
}
