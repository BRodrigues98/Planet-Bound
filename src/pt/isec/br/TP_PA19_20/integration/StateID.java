package pt.isec.br.TP_PA19_20.integration;

public enum  StateID {
    AWAIT_DICE_ROLL("Choose a random or a forced event"),
    AWAIT_MINING_CONFIRMATION("Do you want to convert resources or advance in space?"),
    AWAIT_MOVEMENT("Ready to go?"),
    AWAIT_PLANET_DECISON("Choose your next move"),
    AWAIT_RESOURCES_CONVERSION("Select an option to convert your resources"),
    AWAIT_SPACESHIP_SELECTION("Choose your spacecraft (Hover to see stats)"),
    AWAIT_SS_DECISION("Select an option to buy something from the Space Station."),
    GAME_OVER("The game is over."),
    LAST_CHANCE("Last Chance");

    String value;
    StateID(String s){
        value = s;
    }

    @Override
    public String toString() {
        return value;
    }
}
