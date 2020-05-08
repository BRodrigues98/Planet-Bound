package pt.isec.br.TP_PA19_20.logic.data;

import pt.isec.br.TP_PA19_20.logic.data.planet.BluePlanet;
import pt.isec.br.TP_PA19_20.logic.data.planet.Planet;
import pt.isec.br.TP_PA19_20.logic.data.planet.alien.*;
import pt.isec.br.TP_PA19_20.logic.data.ship.Drone;
import pt.isec.br.TP_PA19_20.logic.data.ship.Mining;
import pt.isec.br.TP_PA19_20.logic.data.ship.Ship;

import java.util.ArrayList;
import java.util.List;

public class DataGame {
    private List<String> logs;
    private Ship ship;
    private List<Boolean> officers;
    private List<String> positions;
    /*
        0 - Captain
        1 - Navigation Officer
        2 - Landing Party / Exploration Officer
        3 - Shields Officer
        4 - Weapons Officer
        5 - Cargo Hold Officer
     */

    //private String position;
    //private String previousPosition;
    private boolean firstMove;
    private Planet planet;
    private List<String> events;
    private boolean wasRedDot;



    //------------ CONSTRUCTOR ------------

    public DataGame() {
        init();
    }

    //-------------------------------------

    public void init(){
        ship = null;
        officers = new ArrayList<>();

        for (int i = 0; i < 6; i++)
            officers.add(true);

        logs = new ArrayList<>();
        //position = null;
        //previousPosition = null;
        firstMove = true;
        planet = null;

        events = new ArrayList<>();
        events.add("Crew Death");
        events.add("Salvage Ship");
        events.add("Cargo Loss");
        events.add("Fuel Loss");
        events.add("No Event");
        events.add("Crew Rescue");

        positions = new ArrayList<>();
        positions.add("Captain");
        positions.add("Navigation Officer");
        positions.add("Landing Party / Exploration Officer");
        positions.add("Shields Officer");
        positions.add("Weapons Officer");
        positions.add("Cargo Hold Officer");
        wasRedDot = false;
    }
    //------------ GETTERS/SETTERS ------------

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) { this.ship = ship; }

    public List<Boolean> getOfficers() { return officers; }

    public void setOfficers(List<Boolean> officers) { this.officers = officers; }

    //public String getPosition() { return position; }

    //public void setPosition(String position) { this.position = position; }

    //public String getPreviousPosition() { return previousPosition; }

    //public void setPreviousPosition(String previousPosition) { this.previousPosition = previousPosition; }

    public boolean isFirstMove() { return firstMove; }

    public void setFirstMove(boolean firstMove) { this.firstMove = firstMove; }

    public Planet getPlanet() { return planet; }

    public void setPlanet(Planet planet) { this.planet = planet; }

    public List<String> getEvents() { return events; }

    public void setEvents(List<String> events) { this.events = events; }

    public List<String> getPositions() { return positions; }

    public void setPositions(List<String> positions) { this.positions = positions; }

    public boolean isWasRedDot() { return wasRedDot; }

    public void setWasRedDot(boolean wasRedDot) { this.wasRedDot = wasRedDot; }

    //-----------------------------------------

    //------------ LOGS ------------

    public void clearLogs() { logs.clear(); }

    public void addLogs(String msg) { logs.add(msg); }

    public List<String> getLogs() { return logs; }

    public void getShipStats() {
        logs.add("Ships Stats:\n");
        logs.add("Mining:\n\t1 Weapons System - 9 cells\n\t1 Shield Systems - 18 cells\n\tFuel System - 53 cells\n\tCargo Hold:\n\t\t4 levels - 1-6/7-12/13-18/19-24 of each resource\n");
        logs.add("Military:\n\t2 Weapons System - 9 cells each\n\t1 Shield Systems - 9 cells\n\tFuel System - 35 cells\n\tCargo Hold:\n\t\t2 levels - 1-6/7-12 of each resource\n");
    }

    public String officerKilled(int index) {
        switch (index){
            case 0:
                return "Your Captain is dead.";
            case 1:
                return "Your Navigation Officer is dead.";
            case 2:
                return "Your Landing Party Officer is dead. You can't explore a planet until you hire a new member";
            case 3:
                return "Your Shields Officer is dead. Your costs while exploring space are increased until you hire a new member";
            case 4:
                return "Your Weapons Officer is dead. Nothing will happen because the Weapons are useless.";
            case 5:
                return "Your Cargo Hold Officer is dead. You can't convert your resources or upgrade your ship until you hire a new member";
            default:
                return null;
        }
    }

    //------------------------------

    public int whereTo() {
        double wormHole = Math.random();
        if(wormHole <= 0.125){
            addLogs("You're going through a wormhole!");
            int shieldToLose, fuelToLose;
            if(!officers.get(3)){
                shieldToLose = 4;
                fuelToLose = 4;
            }
            else{
                shieldToLose = 2;
                fuelToLose = 3;
            }
            if(ship.getShieldSystem() >= shieldToLose) {
                ship.setShieldSystem(ship.getShieldSystem() - shieldToLose);
                ship.setFuel(ship.getFuel() - fuelToLose);

                if(ship.getFuel() <= 0) {
                    addLogs("You've ran out of fuel.");
                    return 0;
                }
                else {
                    //game.setPreviousPosition(game.getPosition());
                    //game.setPosition("planet");
                    addLogs("Exiting wormhole.");
                    if(!wasRedDot) {
                        addLogs("New event about to happen. Hold tight.");
                        wasRedDot = true;
                        return 1;
                    }
                    else{
                        addLogs("Travelling to next planet.");
                        wasRedDot = false;
                        return 2;
                    }
                }
            }
            else{ //Nao tem shield suficiente para passar o wormhole
                int index = 0;
                //Procura o último officer vivo e guarda o indice em index
                for (int i = 0; i < officers.size(); i++) {
                    if (!officers.get(i)) {
                        if (i != 0)
                            index = i - 1;
                        break;
                    }
                }
                addLogs(officerKilled(index));
                officers.set(index, false);
                ship.setFuel(ship.getFuel() - 2);
                if(ship.getFuel() <= 0){
                    addLogs("You've ran out of fuel.");
                    return 0;
                }
                else if(index == 0)  //Morreu o ultimo crew member
                    return 0;
                else { //Passamos o wormhole e chegamos a novo planeta
                    //game.setPreviousPosition(game.getPosition());
                    //game.setPosition("planet");
                    addLogs("Exiting wormhole.");
                    if(!wasRedDot) {
                        addLogs("New event about to happen. Hold tight.");
                        wasRedDot = true;
                        return 1;
                    }
                    else{
                        addLogs("Travelling to next planet.");
                        wasRedDot = false;
                        return 2;
                    }
                }

            }
        }
        else { //Não estamos num wormhole
            //game.setPreviousPosition(game.getPosition());
            //game.setPosition("red dot");
            ship.setFuel(ship.getFuel() - 1);
            if(!wasRedDot) {
                addLogs("New event about to happen. Hold tight.");
                wasRedDot = true;
                return 1;
            }
            else{
                addLogs("Travelling to next planet.");
                wasRedDot = false;
                return 2;
            }
        }
    }

    public int mine(){
        if(!ship.isDrone()){
            addLogs("You don't have a drone to mine.");
            return 0;
        }

        ship.getDrone().setPosX((int) (Math.random() * 6) + 1);
        ship.getDrone().setPosY((int) (Math.random() * 6) + 1);

        int[] droneStartingLocation = new int[2];
        droneStartingLocation[0] = ship.getDrone().getPosX();
        droneStartingLocation[1] = ship.getDrone().getPosY();

        //planet.setAlien(new Alien(drone.getPosX(), drone.getPosY()));

        //Verifica se já foi minado vezes suficientes
        if(planet.getTimesMined() == planet.getNumResources()) {
            addLogs("Planet already fully mined.");
            return 0;
        }
        else{
            randomAlien();
            int index = (int) (Math.random() * planet.getNumResources());
            String resource = planet.getTypeResource().get(index);

            int howMany = 1;
            if(!resource.equals("artifact")){
                howMany = (int) (Math.random() * 3) + 1;
            }
            //Se o artefacto já foi encontrado, cria-se outro recurso mas sem contar com o artefacto
            else if(planet instanceof BluePlanet){
                if(((BluePlanet) planet).isFoundArtifact()){
                    index = (int) (Math.random() * (planet.getNumResources() - 1));
                    resource = planet.getTypeResource().get(index);
                }
            }

            boolean pickedUp = false;
            int resPosX, resPosY;

            do{
                resPosX = (int) (Math.random() * 6) + 1;
                resPosY = (int) (Math.random() * 6) + 1;
            }
            while(resPosX == planet.getAlien().getPosX() || resPosY == planet.getAlien().getPosY());


            while(!pickedUp){   //ship.getDrone().getPosX() != resPosX && ship.getDrone().getPosY() != resPosY

                //Movimentação do drone
                if(ship.getDrone().getPosX() > resPosX)
                    ship.getDrone().setPosX(ship.getDrone().getPosX() - 1);
                else if(ship.getDrone().getPosX() < resPosX)
                    ship.getDrone().setPosX(ship.getDrone().getPosX() + 1);
                else if(ship.getDrone().getPosY() > resPosY)
                    ship.getDrone().setPosY(ship.getDrone().getPosY() - 1);
                else if(ship.getDrone().getPosY() < resPosY)
                    ship.getDrone().setPosY(ship.getDrone().getPosY() + 1);

                //Verificação se o drone está em cima do recurso
                if(ship.getDrone().getPosX() == resPosX && ship.getDrone().getPosY() == resPosY) {
                    pickedUp = true;
                }


                boolean matouDrone;
                //Verificar se o alien e o drone estao em posicoes adjacentes
                if(planet.getAlien().getPosX() == ship.getDrone().getPosX() + 1 || planet.getAlien().getPosX() == ship.getDrone().getPosX() - 1) {
                    if (planet.getAlien().getPosY() == ship.getDrone().getPosY() + 1 || planet.getAlien().getPosY() == ship.getDrone().getPosY() - 1) {
                        addLogs("Your drone found an alien. A fight is about to happen!");
                        matouDrone = alienAttack();
                        if (matouDrone) {
                            return 1;
                        } else {
                            randomAlien();
                        }
                    }
                }
                //Nao estao adjacentes, mexer o alien
                else {
                    if(planet.getAlien().getPosX() > ship.getDrone().getPosX())
                        planet.getAlien().setPosX(planet.getAlien().getPosX() - 1);
                    else if(planet.getAlien().getPosX() < ship.getDrone().getPosX())
                        planet.getAlien().setPosX(planet.getAlien().getPosX() + 1);
                    else if(planet.getAlien().getPosY() > ship.getDrone().getPosY())
                        planet.getAlien().setPosY(planet.getAlien().getPosY() - 1);
                    else if(planet.getAlien().getPosY() < ship.getDrone().getPosY())
                        planet.getAlien().setPosY(planet.getAlien().getPosY() + 1);
                }
            }
            //Só sai quando o drone já tem o recurso, voltar para a posição inicial
            //Podiamos nao fazer a movimentacao do alien, porque este nao encontra o drone a partir do momento em que ele apanha o recurso (porque se movem ao mesmo tempo)
            //Mas para a interface grafica (futura) é interessante ver o movimento do drone na mesma
             while(ship.getDrone().getPosX() != droneStartingLocation[0] && ship.getDrone().getPosY() != droneStartingLocation[1]) {
                //Movimentação do drone
                if (ship.getDrone().getPosX() > droneStartingLocation[0])
                    ship.getDrone().setPosX(ship.getDrone().getPosX() - 1);
                else if (ship.getDrone().getPosX() < droneStartingLocation[0])
                    ship.getDrone().setPosX(ship.getDrone().getPosX() + 1);
                else if (ship.getDrone().getPosY() > droneStartingLocation[1])
                    ship.getDrone().setPosY(ship.getDrone().getPosY() - 1);
                else if (ship.getDrone().getPosY() < droneStartingLocation[1])
                    ship.getDrone().setPosY(ship.getDrone().getPosY() + 1);


                boolean matouDrone;
                //Verificar se o alien e o drone estao em posicoes adjacentes
                if (planet.getAlien().getPosX() == ship.getDrone().getPosX() + 1 || planet.getAlien().getPosX() == ship.getDrone().getPosX() - 1) {
                    if (planet.getAlien().getPosY() == ship.getDrone().getPosY() + 1 || planet.getAlien().getPosY() == ship.getDrone().getPosY() - 1) {
                        addLogs("Your drone found an alien. A fight is about to happen!");
                        matouDrone = alienAttack();
                        if (matouDrone) {
                            return 1;
                        } else {
                            randomAlien();
                        }
                    }
                }
                //Nao estao adjacentes, mexer o alien
                else {
                    if (planet.getAlien().getPosX() > ship.getDrone().getPosX())
                        planet.getAlien().setPosX(planet.getAlien().getPosX() - 1);
                    else if (planet.getAlien().getPosX() < ship.getDrone().getPosX())
                        planet.getAlien().setPosX(planet.getAlien().getPosX() - 1);
                    else if (planet.getAlien().getPosY() > ship.getDrone().getPosY())
                        planet.getAlien().setPosY(planet.getAlien().getPosY() - 1);
                    else if (planet.getAlien().getPosY() < ship.getDrone().getPosY())
                        planet.getAlien().setPosY(planet.getAlien().getPosY() - 1);
                }
            }
            ship.getDrone().setResourceFound(resource);
            if(planet instanceof BluePlanet && resource.equals("artifact")) {
                ((BluePlanet) planet).setFoundArtifact(true);
                addLogs("Your drone found an artifact! Mining successful and removed one fuel from ship.");
                ship.setNumArtifacts(ship.getNumArtifacts() + 1);
            }
            else{
                addLogs("Your drone found a " + resource + " resource. Mining successful and removed one fuel from ship.");
            }
            planet.setTimesMined(planet.getTimesMined() + 1);
            ship.setFuel(ship.getFuel() - 1);
            for (int i = 0; i < ship.getCargoHold().size(); i++) {
                if(ship.getCargoType().get(i).equals(resource)){
                    if(ship.getCargoHold().get(i) + howMany > ship.getMaxCargo()){
                        addLogs("Your cargo hold is now full but you had to waste a few resources since you've found " + howMany + " of this resource.");
                        ship.getCargoHold().set(i, ship.getMaxCargo());
                    }
                    else{
                        addLogs("You've found " + howMany + " of this resource and it's been added to your cargo.");
                        ship.getCargoHold().set(i, ship.getCargoHold().get(i) + howMany);
                    }
                }
            }
            return 2;

        }



    }

    private void randomAlien() {
        double rand = Math.random();
        if(rand <= 0.25){
            addLogs("Black Alien spawned!");
            getPlanet().setAlien(new BlackAlien(ship.getDrone().getPosX(), ship.getDrone().getPosY()));
        }
        else if(rand > 0.25 && rand <= 0.5){
            addLogs("Green Alien spawned!");
            getPlanet().setAlien(new GreenAlien(ship.getDrone().getPosX(), ship.getDrone().getPosY()));
        }
        else if(rand > 0.5 && rand <= 0.75){
            addLogs("Blue Alien spawned!");
            getPlanet().setAlien(new BlueAlien(ship.getDrone().getPosX(), ship.getDrone().getPosY()));
        }
        else {
            addLogs("Red Alien spawned!");
            getPlanet().setAlien(new RedAlien(ship.getDrone().getPosX(), ship.getDrone().getPosY()));
        }
    }

    private boolean alienAttack() {
        do {
            int dice = (int) (Math.random() * 6) + 1;
            for (int i = 0; i < getPlanet().getAlien().getAttack().size(); i++) {
                if (getPlanet().getAlien().getAttack().get(i) == dice) {
                    ship.getDrone().setHp(ship.getDrone().getHp() - 1);
                    addLogs("Your drone took a hit! It has " + ship.getDrone().getHp() + " HP left.");
                    if(ship.getDrone().getHp() == 0){
                        ship.setHasDrone(false);
                        addLogs("Drone destroyed. You must buy a new one at a Space Station to mine resources.");
                        return true;
                    }
                }
            }
            dice = (int) (Math.random() * 6) + 1;
            for (int i = 0; i < getPlanet().getAlien().getDeath().size(); i++) {
                if(getPlanet().getAlien().getDeath().get(i) == dice){
                    addLogs("Alien killed by the drone. Drone has " + ship.getDrone().getHp() + " hp.");
                    return false;
                }
            }
        }
        while(ship.getDrone().getHp() > 0);

        ship.setHasDrone(false);
        addLogs("Drone destroyed. You must buy a new one at a Space Station to mine resources.");
        return true;
    }

    public int roll(int type) {
        int rand;
        if(type == 1)
            rand = (int) (Math.random() * 6) + 1;
        else{
            rand = - type;
        }
        addLogs("Event about to happen: " + events.get(rand - 1) );

        switch (rand){
            case 1: {
                addLogs("A crew member is injured due to a system malfunction.");
                if (ship.getExtraMember().isEmpty()) {
                    int index = 0;
                    //Procura o último officer vivo e guarda o indice em index
                    for (int i = officers.size() - 1; i >= 0; i--) {
                        if (officers.get(i)) {
                            if (i != 0)
                                index = i;
                            break;
                        }
                    }
                    addLogs(officerKilled(index));
                    officers.set(index, false);
                }
                else {
                    ship.getExtraMember().remove(ship.getExtraMember().size() - 1);
                }

                break;
            }
            case 2: {
                int randomIndex = (int) (Math.random() * 4), howMany = (int) (Math.random() * 6) + 1;
                if (ship.getCargoHold().get(randomIndex) + howMany > ship.getMaxCargo()) {
                    ship.getCargoHold().set(randomIndex, ship.getMaxCargo());
                    addLogs("You've found a salvaged ship. You've found " + ship.getCargoType().get(randomIndex) + " resource in that ship. Unfortunately you had to leave some behind because it filled your cargo hold for that resource.");
                } else {
                    ship.getCargoHold().set(randomIndex, (ship.getCargoHold().get(randomIndex) + howMany));
                    addLogs("You've found a salvaged ship. You've found " + howMany + " " + ship.getCargoType().get(randomIndex) + " resources in that ship and they have been added to your cargo hold.");
                }
                break;
            }
            case 3: {
                int randomIndex = (int) (Math.random() * 4), howMany = (int) (Math.random() * 3) + 1;

                if(howMany > ship.getCargoHold().get(randomIndex))
                    ship.getCargoHold().set(randomIndex, 0);
                else
                    ship.getCargoHold().set(randomIndex, (ship.getCargoHold().get(randomIndex) - howMany));

                addLogs("You've had a cargo loss. You now have " + ship.getCargoHold().get(randomIndex) + " " + ship.getCargoType().get(randomIndex) + " resources.");
                break;
            }
            case 4: {
                ship.setFuel(ship.getFuel() - 1);
                addLogs("You've accidentally used too much fuel on a fuel run. You now have " + ship.getFuel() + ".");
                break;
            }
            case 5: {
                addLogs("Fortunately, nothing happened. Smooth sailing.");
                break;
            }
            case 6: {
                boolean fNewOfficer = false;
                for (int i = 0; i < officers.size(); i++) {
                    if(!officers.get(i)){
                        officers.set(i, true);
                        fNewOfficer = true;
                        break;
                    }
                }

                if(!fNewOfficer)
                    ship.getExtraMember().add(true);

                addLogs("You've found a ship in distress with a lone crew member and he is added to your officers list.");
            }
        }

        if(officers.isEmpty() || ship.getFuel() <= 0)
            return 0;
        else if(!officers.isEmpty() && ship.getFuel() > 0)
            return 1;
        else
            return 2;
    }


    public int convert(int type) {
        if(!officers.get(5)) { //5 - cargo hold officer
            addLogs("You can't convert because you don't have a Cargo Hold Officer.");
            return 0;
        }

        switch (type){
            case 0:
                //Replenish Drone armor. Costs 1 of each resource.
                if(!ship.isDrone()) {
                    addLogs("Can't replenish Drone Armor. You must buy a new one at a Space Station.");
                    return 0;
                }
                else if(ship.getDrone().getHp() == 6){
                    addLogs("Drone at full health.");
                    return 0;
                }

                for (int i = 0; i < ship.getCargoHold().size(); i++) {
                    if (ship.getCargoHold().get(i) == 0) {
                        addLogs("Insufficient " + ship.getCargoType().get(i) + " resources.");
                        return 0;
                    }
                }

                for (int i = 0; i < ship.getCargoHold().size(); i++)
                    ship.getCargoHold().set(i, ship.getCargoHold().get(i) - 1);

                ship.getDrone().setHp(6);
                addLogs("Drone Armor replenished.");
                return 1;
            case 1:
                //1 Extra Energy Shield. Costs 1 black, green and blue resources.
                for (int i = 0; i < ship.getCargoHold().size(); i++) {
                    if (ship.getCargoHold().get(i) == 0 && i != 1 ) { //1 é Red Resource
                        addLogs("Insufficient " + ship.getCargoType().get(i) + " resources.");
                        return 0;
                    }
                }

                for (int i = 0; i < ship.getCargoHold().size(); i++) {
                    if(i != 1)
                        ship.getCargoHold().set(i, ship.getCargoHold().get(i) - 1);
                }

                ship.setShieldSystem(ship.getShieldSystem() + 1);
                addLogs("1 Extra Energy Shield added.");
                return 1;
            case 2:
                //1 Extra Ammo. Costs 1 black and blue resources.
                for (int i = 0; i < ship.getCargoHold().size(); i++) {
                    if (ship.getCargoHold().get(i) == 0 && i != 1 && i != 3 ) { //1 é Red Resource
                        addLogs("Insufficient " + ship.getCargoType().get(i) + " resources.");
                        return 0;
                    }
                }

                for (int i = 0; i < ship.getCargoHold().size(); i++) {
                    if(i != 1 && i != 3)
                        ship.getCargoHold().set(i, ship.getCargoHold().get(i) - 1);
                }

                ship.setWeaponSystem(ship.getWeaponSystem() + 1);
                addLogs("1 Extra Ammo added.");
                return 1;
            case 3:
                //1 Extra Fuel. Costs 1 black, red and green resources.
                for (int i = 0; i < ship.getCargoHold().size(); i++) {
                    if (ship.getCargoHold().get(i) == 0 && i != 2) { //1 é Red Resource
                        addLogs("Insufficient " + ship.getCargoType().get(i) + " resources.");
                        return 0;
                    }
                }

                for (int i = 0; i < ship.getCargoHold().size(); i++) {
                    if(i != 2)
                        ship.getCargoHold().set(i, ship.getCargoHold().get(i) - 1);
                }

                ship.setFuel(ship.getFuel() + 1);
                addLogs("1 Extra Fuel added.");
                return 1;
            default:
                return 0;
            }
    }

    public int convert(int newRes, int oldRes){
        if(!officers.get(5)) { //5 - cargo hold officer
            addLogs("You can't convert because you don't have a Cargo Hold Officer.");
            return 0;
        }

        if(ship.getCargoHold().get(oldRes) > 0 ) {
            if(ship.getCargoHold().get(newRes) < ship.getMaxCargo()) {
                ship.getCargoHold().set(oldRes, ship.getCargoHold().get(oldRes) - 1);
                ship.getCargoHold().set(newRes, ship.getCargoHold().get(newRes) + 1);

                addLogs("Resource of type " + ship.getCargoType().get(oldRes) + " turned into resource of type " + ship.getCargoType().get(newRes) + ".");
                return 1;
            }
            else{
                addLogs("Cargo Hold of type " + ship.getCargoType().get(newRes) + " at max capacity.");
                return 0;
            }
        }
        else{
            addLogs("You don't have any resources of type " + ship.getCargoType().get(oldRes) + " to convert.");
            return 0;
        }
    }

    public int makesDecision(int choice) {
        switch (choice){
            case 1: //Upgrade cargo hold
                if(ship.getCargoHoldLvl() == ship.getMaxLevel()){
                    addLogs("Your cargo hold is already at max level (level " + ship.getCargoHoldLvl() + ").");
                    return 0;
                }
                for (int i = 0; i < ship.getCargoHold().size(); i++) {
                    if(ship.getCargoHold().get(i) < 2){
                        addLogs("You don't have enough resources of type " + ship.getCargoType().get(i) + " to complete this purchase.");
                        return 0;
                    }
                }

                for (int i = 0; i < ship.getCargoHold().size(); i++)
                    ship.getCargoHold().set(i, ship.getCargoHold().get(i) - 2);

                ship.setCargoHoldLvl(ship.getCargoHoldLvl() + 1);
                ship.setMaxCargo(ship.getMaxCargo() + 6);
                addLogs("Your cargo hold level is now at level " + ship.getCargoHoldLvl() + " and can carry up to" + ship.getMaxCargo() + " of each resource.");

                return 1;

            case 2: //Hire a crew member
                for (int i = 0; i < ship.getCargoHold().size(); i++) {
                    if(ship.getCargoHold().get(i) < 1){
                        addLogs("You don't have enough resources of type " + ship.getCargoType().get(i) + " to complete this purchase.");
                        return 0;
                    }
                }


                for (int i = 0; i < officers.size(); i++) {
                    if(!officers.get(i)){
                        for (int j = 0; j < ship.getCargoHold().size(); j++)
                            ship.getCargoHold().set(j, ship.getCargoHold().get(j) - 1);
                        officers.set(i, true);
                        addLogs("You've hired a new " + positions.get(i) + ".");
                        return 1;
                    }
                }

                addLogs("All your officers are alive and healthy.");
                return 0;

            case 3: //Upgrade weapon system
                if(ship instanceof Mining){
                    addLogs("Your ship can't have a weapons upgrade.");
                    return 0;
                }
                else{
                    for (int i = 0; i < ship.getCargoHold().size(); i++) {
                        if(ship.getCargoHold().get(i) < 2){
                            addLogs("You don't have enough resources of type " + ship.getCargoType().get(i) + " to complete this purchase.");
                            return 0;
                        }
                    }

                    if(ship.getWeaponLevel() == 2){
                        addLogs("You ship already has its Weapon System maxed out");
                        return 0;
                    }

                    for (int i = 0; i < ship.getCargoHold().size(); i++)
                        ship.getCargoHold().set(i, ship.getCargoHold().get(i) - 2);

                    ship.setWeaponLevel(2);
                    ship.setWeaponSystem(18);
                    addLogs("Weapons System upgraded.");
                    return 1;
                }
            case 4: //Purchase new mining drone
                if(ship.isDrone()){
                    addLogs("You already have a drone.");
                    return 0;
                }

                for (int i = 0; i < ship.getCargoHold().size(); i++) {
                    if(ship.getCargoHold().get(i) < 2){
                        addLogs("You don't have enough resources of type " + ship.getCargoType().get(i) + " to complete this purchase.");
                        return 0;
                    }
                }

                for (int i = 0; i < ship.getCargoHold().size(); i++)
                    ship.getCargoHold().set(i, ship.getCargoHold().get(i) - 2);

                ship.setHasDrone(true);
                ship.setDrone(new Drone());
                addLogs("New Drone bought");
                return 1;
            default:
                return 0;
        }
    }

    public void currentShipStats() {
        addLogs("Officers Available:");
        for (int i = 0; i < officers.size(); i++) {
            if(officers.get(i)){
                addLogs(positions.get(i));
            }
        }

        addLogs("\nWeapons System cells: " + ship.getWeaponSystem() + "\nShield System cells: " + ship.getShieldSystem() + "\nFuel cells: " + ship.getFuel());

        addLogs("Cargo Hold (Level " + ship.getCargoHoldLvl() + " -> " + ship.getMaxCargo() + " max for each resource):");
        for (int i = 0; i < ship.getCargoHold().size(); i++) {
            addLogs(ship.getCargoType().get(i) + " resources: " + ship.getCargoHold().get(i));
        }
        addLogs("Artifacts: " + ship.getNumArtifacts());
    }
}