package pt.isec.br.TP_PA19_20.logic.data.ship;

public class Drone {
    private int posX;
    private int posY;
    private int hp;
    private String resourceFound;

    public Drone() {
        this.posX = (int) (Math.random() * 6) + 1;
        this.posY = (int) (Math.random() * 6) + 1;
        this.hp = 6;
    }


    //------------ GETTERS/SETTERS ------------
    public int getPosX() { return posX; }

    public void setPosX(int posX) { this.posX = posX; }

    public int getPosY() { return posY; }

    public void setPosY(int posY) { this.posY = posY; }

    public int getHp() { return hp; }

    public void setHp(int hp) { this.hp = hp; }

    public String getResourceFound() { return resourceFound; }

    public void setResourceFound(String resourceFound) { this.resourceFound = resourceFound; }

    //-----------------------------------------
}
