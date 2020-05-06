package pt.isec.br.TP_PA19_20.logic.data.planet;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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
        return "You've found a Green Planet. You can find " + numResources + " resources here of type red and green";
    }
}
