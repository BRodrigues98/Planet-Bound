package pt.isec.br.TP_PA19_20.logic.data.planet;

import pt.isec.br.TP_PA19_20.ui.text.Colors;

import java.util.ArrayList;

public class GreenPlanet extends Planet {
    public GreenPlanet() {
        super();
        numResources = 2;
        typeResource = new ArrayList<>();
        typeResource.add("red");
        typeResource.add("green");

    }

    @Override
    public String toString() {
        return "Green Planet:\n" + numResources + " resources of type red and green";
    }

    @Override
    public int asInt() {
        return 2;
    }
}
