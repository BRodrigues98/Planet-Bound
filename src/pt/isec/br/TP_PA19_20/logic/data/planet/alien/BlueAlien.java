package pt.isec.br.TP_PA19_20.logic.data.planet.alien;

import java.util.ArrayList;

public class BlueAlien extends Alien {
    public BlueAlien(int dronePosX, int dronePosY) {
        super(dronePosX, dronePosY);
        attack = new ArrayList<>();
        attack.add(3);
        attack.add(4);
        attack.add(5);

        death = new ArrayList<>();
        death.add(3);
        death.add(4);
        death.add(5);
    }
}
