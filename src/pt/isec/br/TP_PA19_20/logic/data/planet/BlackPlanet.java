package pt.isec.br.TP_PA19_20.logic.data.planet;

import java.util.ArrayList;

public class BlackPlanet extends Planet {
    public BlackPlanet() {
        super();
        numResources = 2;
        typeResource = new ArrayList<>();
        typeResource.add("black");
        typeResource.add("blue");

    }

    @Override
    public String toString() {
        return "Black Planet:\n" + numResources + " resources of type black and blue";
    }

    @Override
    public int asInt() {
        return 0;
    }
}
