package pt.isec.br.TP_PA19_20.logic.data.ship;



public class Mining extends Ship{
    public Mining() {
        super();
        fuel = 0;
        shieldSystem = 18;
        maxLevel = 4;
        maxShield = 18;
        maxWeapon = 9;
        maxFuel = 53;
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
