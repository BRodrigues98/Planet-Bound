package pt.isec.br.TP_PA19_20.logic.data.planet.alien;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BlackAlien extends Alien {
    public BlackAlien(int dronePosX, int dronePosY) {
        super(dronePosX, dronePosY);
        attack = new ArrayList<>();
        attack.add(1);

        death = new ArrayList<>();
        death.add(5);
        death.add(6);
    }
}
