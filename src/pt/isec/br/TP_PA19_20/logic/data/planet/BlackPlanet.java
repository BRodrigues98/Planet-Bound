package pt.isec.br.TP_PA19_20.logic.data.planet;

import java.util.ArrayList;
import java.util.List;

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
        return "You've found a Black Planet. You can find " + numResources + " resources here of type black and blue";
    }
}
