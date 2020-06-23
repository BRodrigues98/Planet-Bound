package pt.isec.br.TP_PA19_20.ui.text;

public abstract class Colors {
    //ANSI escape codes
    //System.out.println(ANSI_RED + "First you must choose your spacehip!" + ANSI_RESET);
    //https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
    //https://en.wikipedia.org/wiki/ANSI_escape_code#Colors
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001b[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
}
