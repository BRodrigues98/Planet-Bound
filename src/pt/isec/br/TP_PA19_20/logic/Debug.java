package pt.isec.br.TP_PA19_20.logic;

import pt.isec.br.TP_PA19_20.logic.data.DataGame;

import javax.xml.crypto.Data;

public class Debug {
    DataGame data;

    public Debug(DataGame data) {
        this.data = data;
    }

    public void setMax(){
        for (int i = 0; i < data.getShip().getCargoHoldLvl(); i++) {
            data.getShip().getCargoHold().set(i, data.getShip().getMaxCargo());
        }
    }
}
