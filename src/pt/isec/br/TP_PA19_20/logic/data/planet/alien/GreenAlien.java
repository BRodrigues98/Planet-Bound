package pt.isec.br.TP_PA19_20.logic.data.planet.alien;

import java.util.ArrayList;

public class GreenAlien extends Alien {
    public GreenAlien(int dronePosX, int dronePosY) {
        super(dronePosX, dronePosY);
        attack = new ArrayList<>();
        attack.add(1);
        attack.add(2);

        death = new ArrayList<>();
        death.add(4);
        death.add(5);
        death.add(6);
    }
}
