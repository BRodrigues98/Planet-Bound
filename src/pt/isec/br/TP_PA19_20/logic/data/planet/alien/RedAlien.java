package pt.isec.br.TP_PA19_20.logic.data.planet.alien;

import java.util.ArrayList;

public class RedAlien extends Alien {
    public RedAlien(int dronePosX, int dronePosY) {
        super(dronePosX, dronePosY);
        attack = new ArrayList<>();
        attack.add(5);
        attack.add(6);

        death = new ArrayList<>();
        death.add(1);
        death.add(2);
    }
}
