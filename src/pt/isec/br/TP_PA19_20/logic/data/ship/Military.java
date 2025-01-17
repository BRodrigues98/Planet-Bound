package pt.isec.br.TP_PA19_20.logic.data.ship;



public class Military extends Ship{

    public Military() {
        super();
        fuel = 35;
        shieldSystem = 9;
        maxLevel = 2;
        maxShield = 9;
        maxWeapon = 9;
        maxFuel = 35;
    }


    @Override
    public String toString() {
        return "Military{" +
                " weaponSystem=" + weaponSystem +
                ", shieldSystem=" + shieldSystem +
                ", fuel=" + fuel +
                ", cargoHoldLvl=" + cargoHoldLvl +
                '}';
    }
}
