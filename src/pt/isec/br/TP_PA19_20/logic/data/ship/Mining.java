package pt.isec.br.TP_PA19_20.logic.data.ship;

import java.util.Arrays;

public class Mining extends Ship {
    public Mining() {
        super();
        fuel = 53;
        shieldSystem = 18;
        maxLevel = 4;
    }


    @Override
    public String toString() {
        return "Mining{" +
                "weaponSystem=" + weaponSystem +
                ", shieldSystem=" + shieldSystem +
                ", fuel=" + fuel +
                ", cargoHoldLvl=" + cargoHoldLvl +
                '}';
    }
}
