package pt.isec.br.TP_PA19_20.logic.data.ship;

import java.util.Arrays;

public class Mining extends Ship {
    public Mining() {
        super();
        fuel = 53;
        shieldSystem = 18;
        maxLevel = 4;
        maxShield = 18;
        maxWeapon = 9;
        maxFuel = 53;
    }

    @Override
    public void debug() {
        for (int i = 0; i < cargoHold.size(); i++) {
            cargoHold.set(i, 1);
        }

        drone = null;
        hasDrone = false;
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
