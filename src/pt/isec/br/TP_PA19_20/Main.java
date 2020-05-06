package pt.isec.br.TP_PA19_20;

import pt.isec.br.TP_PA19_20.logic.states.GameToStates;
import pt.isec.br.TP_PA19_20.ui.Text;

public class Main {

    public static void main(String[] args) {
        Text ui = new Text(new GameToStates());
        //Run the program in the Text class of the UI package
        ui.run();
    }
}
