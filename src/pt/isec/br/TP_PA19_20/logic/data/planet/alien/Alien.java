package pt.isec.br.TP_PA19_20.logic.data.planet.alien;

import java.util.List;

public abstract class Alien {
    protected int posX;
    protected int posY;
    protected List<Integer> attack;
    protected List<Integer> death;
    //private String type;
    /*
    0 - black
        - Attack on 1
        - Death  on 5-6
    1 - green
        - Attack on 1-2
        - Death  on 4-6
    2 - blue
        - Attack on 3-5
        - Death  on 3-5
    3 - red
        - Attack on 5-6
        - Death  on 1-2
     */

    public Alien(int dronePosX, int dronePosY) {
        do {
            this.posX = (int) (Math.random() * 6) + 1;
            this.posY = (int) (Math.random() * 6) + 1;
        }
        while(dronePosX == this.posX || dronePosY == this.posY);

    }


    //------------ GETTERS/SETTERS ------------
    public int getPosX() { return posX; }

    public void setPosX(int posX) { this.posX = posX; }

    public int getPosY() { return posY; }

    public void setPosY(int posY) { this.posY = posY; }

    public List<Integer> getAttack() { return attack; }

    public void setAttack(List<Integer> attack) { this.attack = attack; }

    public List<Integer> getDeath() { return death; }

    public void setDeath(List<Integer> death) { this.death = death; }

    //-----------------------------------------
}