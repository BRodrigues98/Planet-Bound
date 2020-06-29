package pt.isec.br.TP_PA19_20.logic.data.ship;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Ship implements Serializable {
    protected int weaponSystem;
    protected int shieldSystem;
    protected int maxWeapon;
    protected int maxShield;
    protected int fuel;
    protected int maxFuel;
    protected List<Integer> cargoHold;
    protected List<String> cargoType;
    /*
    0 - Black resource
    1 - Red resource
    2 - Blue resource
    3 - Green resource
     */
    protected int cargoHoldLvl;
    protected int maxCargo;
    protected int maxLevel; //Nivel do cargo hold
    protected int numArtifacts;
    protected Drone drone;
    protected boolean hasDrone;
    protected List<Boolean> extraMember;
    protected int weaponLevel;

    //------------ CONSTRUCTOR ------------
    public Ship() {
        weaponSystem = 9;
        weaponLevel = 1;
        cargoHoldLvl = 1;
        cargoHold = new ArrayList<>();
        cargoHold.add(0);   //Black resource
        cargoHold.add(0);   //Red resource
        cargoHold.add(0);   //Blue resource
        cargoHold.add(0);   //Green resource

        cargoType = new ArrayList<>();
        cargoType.add("black");   //Black resource
        cargoType.add("red");   //Red resource
        cargoType.add("blue");   //Blue resource
        cargoType.add("green");   //Green resource

        numArtifacts = 0;
        drone = new Drone();
        hasDrone = true;
        extraMember = new ArrayList<>();
        maxCargo = 6;
    }
    //-------------------------------------

    //------------ GETTERS/SETTERS ------------

    public int getWeaponSystem() {
        return weaponSystem;
    }

    public void setWeaponSystem(int weaponSystem) {
        this.weaponSystem = weaponSystem;
    }

    public int getShieldSystem() {
        return shieldSystem;
    }

    public void setShieldSystem(int shieldSystem) {
        this.shieldSystem = shieldSystem;
    }

    public int getFuel() { return fuel; }

    public void setFuel(int fuel) { this.fuel = fuel; }

    public List<Integer> getCargoHold() {
        return cargoHold;
    }

    public void setCargoHold(List<Integer> cargoHold) {
        this.cargoHold = cargoHold;
    }

    public int getCargoHoldLvl() {
        return cargoHoldLvl;
    }

    public void setCargoHoldLvl(int cargoHoldLvl) {
        this.cargoHoldLvl = cargoHoldLvl;
    }

    public int getNumArtifacts() { return numArtifacts; }

    public void setNumArtifacts(int numArtifacts) { this.numArtifacts = numArtifacts; }

    public Drone getDrone() { return drone; }

    public void setDrone(Drone drone) { this.drone = drone; }

    public boolean isDrone() { return hasDrone; }

    public void setHasDrone(boolean drone) { this.hasDrone = drone; }

    public List<Boolean> getExtraMember() { return extraMember; }

    public void setExtraMember(List<Boolean> extraMember) { this.extraMember = extraMember; }

    public int getMaxCargo() { return maxCargo; }

    public void setMaxCargo(int maxCargo) { this.maxCargo = maxCargo; }

    public List<String> getCargoType() { return cargoType; }

    public void setCargoType(List<String> cargoType) { this.cargoType = cargoType; }

    public int getMaxLevel() { return maxLevel; }

    public void setMaxLevel(int maxLevel) { this.maxLevel = maxLevel; }

    public int getWeaponLevel() { return weaponLevel; }

    public void setWeaponLevel(int weaponLevel) { this.weaponLevel = weaponLevel; }

    public int getMaxShield() { return maxShield; }

    public void setMaxShield(int maxShield) { this.maxShield = maxShield; }

    public int getMaxWeapon() { return maxWeapon; }

    public void setMaxWeapon(int maxWeapon) { this.maxWeapon = maxWeapon; }

    public int getMaxFuel() { return maxFuel; }

    public void setMaxFuel(int maxFuel) { this.maxFuel = maxFuel; }

    //-----------------------------------------

    @Override
    public String toString() {
        return "Ship{" +
                "weaponSystem=" + weaponSystem +
                ", shieldSystem=" + shieldSystem +
                ", fuel=" + fuel +
                ", cargoHold="+
                ", cargoHoldLvl=" + cargoHoldLvl +
                '}';
    }


}
