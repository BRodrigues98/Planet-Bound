package pt.isec.br.TP_PA19_20.integration;

public enum Type {

    STATE("type_state"),
    SHIP("type_ship"),
    INSTRUCTION("type_instruction"),
    TIP("type_tip");

    String value;
    Type(String s){
        value = s;
    }

    @Override
    public String toString() {
        return value;
    }
}
