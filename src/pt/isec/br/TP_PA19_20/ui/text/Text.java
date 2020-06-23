package pt.isec.br.TP_PA19_20.ui.text;

import pt.isec.br.TP_PA19_20.logic.states.AwaitMovement;
import pt.isec.br.TP_PA19_20.logic.states.*;

import java.util.Scanner;

public class Text {


    private GameToStates game;
    private Scanner sc;
    private boolean exit;

    public Text(GameToStates game) {
        this.game = game;
        this.sc = new Scanner(System.in);
        exit = false;
    }

    public void run(){
        System.out.println("Welcome to " + Colors.ANSI_BLUE + "Planet Bound" + Colors.ANSI_RESET + ". Press Enter when you're ready to start the game.");
        sc.nextLine();

        while(!exit){
             checkLogs();

            game.checkLossConditions();

            if(game.getState() instanceof AwaitSpaceshipSelection)
                UIAwaitSpaceshipSelection();
            else if (game.getState() instanceof AwaitMovement)
                UIAwaitMovement();
            else if (game.getState() instanceof AwaitPlanetDecision)
                UIAwaitPlanetDecision();
            else if (game.getState() instanceof AwaitSSDecision)
                UIAwaitSSDecision();
            else if (game.getState() instanceof AwaitMiningConfirmation)
                UIAwaitMiningConfirmation();
            else if (game.getState() instanceof AwaitResourcesConversion)
                UIAwaitResourcesConversion();
            else if (game.getState() instanceof AwaitDiceRoll)
                UIAwaitDiceRoll();
            else if(game.getState() instanceof LastChance)
                UILastChance();
            else if (game.getState() instanceof GameOver)
                UIGameOver();



        }


    }

    //--------- UI ESPECIFICAS DE ESTADO ---------
    private void UIAwaitSpaceshipSelection() {
        int value;
        do {

            System.out.println("First you must choose your spacehip!");
            System.out.println("1 - Mining Ship");
            System.out.println("2 - Military Ship");
            System.out.println("3 - Show Ships stats");
            System.out.println("4 - Show Past Saved Scores");
            System.out.println("5 - Give up and exit");
            System.out.print(">.");

            while (!sc.hasNextInt())
                sc.next();
            value = sc.nextInt();
            sc.nextLine();

            if (value == 1 || value == 2) {
                game.selectShip(value);
            } else if (value == 3) {
                showShipsStats();
                System.out.println("Choose your spaceship:\n1 - Mining Ship\n2 - Military Ship\n3 - Show Past Saved Scores\n4 - Give up and exit");

                System.out.print(">.");

                while (!sc.hasNextInt())
                    sc.next();
                value = sc.nextInt();
                sc.nextLine();
                if (value == 1 || value == 2)
                    game.selectShip(value);
                else if(value == 3)
                    System.out.println(game.readScores());
                else if (value == 4)
                    exit = true;
            }
            else if(value == 4)
                System.out.println(game.readScores());
            else if (value == 5)
                exit = true;
        }
        while(value > 5);
    }

    private void UIAwaitMovement() {
        if(game.isFirstMove()){
            System.out.println("Let's find your first planet!");
        }
        game.move(game.isFirstMove());
    }

    private void UIAwaitPlanetDecision() {
        checkLogs();
        if(game.getTimesMined() > 0) {
            System.out.println("Press Enter to continue.");
            sc.nextLine();

            System.out.println("\nYou've mined this planet " + game.getTimesMined() + " times out of " + game.getNumResourcesOnPlanet() + " possible.");
        }

        int value, flag;
        do {
            if (game.isSpaceStation()) {
                System.out.println("This planet also has a Space Station in its orbit!");
                System.out.println("What do you want to do?");
                System.out.println("1 - Land and mine the planet");
                System.out.println("2 - Land on Space Station");
                System.out.println("3 - Convert Resources");
                System.out.println("4 - Ignore this planet and keep going");
                System.out.println("5 - See your Ship stats");
                flag = 5;
            } else {
                System.out.println("What do you want to do?");
                System.out.println("1 - Land and mine the planet");
                System.out.println("2 - Convert Resources");
                System.out.println("3 - Keep going");
                System.out.println("4 - See your Ship stats");
                flag = 4;
            }

            System.out.print(">.");

            while (!sc.hasNextInt())
                sc.next();
            value = sc.nextInt();
            sc.nextLine(); //O nextInt não limpa o enter em buffer

            if (value == 1)
                game.land();
            else if(flag == 5){
                if(value == 2) {
                    game.savePlanet();
                    game.landOnSS();
                }
                else if(value == 3) {
                    game.savePlanet();
                    craftOptions();
                }
                else if(value == 4)
                    game.nextTurn();
                else if(value == 5){
                    game.currentShipStats();
                    checkLogs();
                    return;
                }
            }
            else{
                if(value == 2) {
                    game.savePlanet();
                    craftOptions();
                }
                else if(value == 3)
                    game.nextTurn();
                else if(value == 4){
                    game.currentShipStats();
                    checkLogs();
                    return;
                }
            }

        }
        while (value > flag);
    }

    private void UIAwaitSSDecision() {
        checkLogs();
        System.out.println("What do you want to do?");
        int value = 0, flag = 0;
        do {
            System.out.println("1 - Upgrade your cargo hold by one section. Costs two of each resource.");
            System.out.println("2 - Hire a crew member. Costs one of each resource.");
            System.out.println("3 - Upgrade your weapon system. Costs two of each resource.");
            System.out.println("4 - Purchase a new mining drone. Costs two of each resource.");
            System.out.println("5 - Cancel and go back.");
            System.out.println("6 - See your ship stats.");

            System.out.print(">.");

            while (!sc.hasNextInt())
                sc.next();
            value = sc.nextInt();
            sc.nextLine();
        }
        while(value > 6);

        if(value == 6){
            game.currentShipStats();
            checkLogs();
            return;
        }
        else if(value == 5){
            game.backToPlanet();
        }
        else if(value > 0) {
            game.makesDecision(value);
        }

        if(value != 5) {
            do {
                checkLogs(); // Se a conversão do recurso não funcionar, ficam coisas pendentes
                System.out.println("Do you want to convert more?");
                System.out.println("1 - Yes");
                System.out.println("2 - No");
                System.out.print(">.");

                while (!sc.hasNextInt())
                    sc.next();
                flag = sc.nextInt();
                sc.nextLine();
            }
            while (flag > 2);

            if (flag == 2) {
                game.backToPlanet();
            }
            else
                return;
        }

    }

    private void UIAwaitMiningConfirmation() {
        checkLogs();

        if (game.getDroneResource() != null && game.getDroneResource().equals("artifact")) {
            if (game.getNumArtifacts() == 4)
                System.out.println("Congratulations. You only need 1 more artifact to win the game.");
            else if (game.getNumArtifacts() < 5 && game.getNumArtifacts() > 0)
                System.out.println("Congratulations. You only need " + (5 - game.getNumArtifacts()) + " more artifacts to win the game.");
        }


        int value, flag;
        do {
            System.out.println("What do you want to do now?");
            System.out.println("1 - Proceed to next planet");
            //System.out.println("2 - Convert your ship resources");
            if(game.isSpaceStation()){
                System.out.println("2 - Land on Space Station.");
                System.out.println("3 - Convert Resources");
                System.out.println("4 - See your Ship stats");
                flag = 4;
            }
            else {
                System.out.println("2 - Convert Resources");
                System.out.println("3 - See your Ship stats");

                flag = 3;
            }


            System.out.print(">.");

            while (!sc.hasNextInt())
                sc.next();
            value = sc.nextInt();
            sc.nextLine(); //O nextInt não limpa o enter em buffer
            if(flag == 3) {
                if (value == 1)
                    game.nextTurn();
                else if(value == 2)
                    craftOptions();
                else if (value == 3) {
                    game.currentShipStats();
                    checkLogs();
                    return;
                }
            }
            else{
                if (value == 1)
                    game.nextTurn();
                else if (value == 2) {
                    game.landOnSS();
                }
                else if(value == 3)
                    craftOptions();
                else {
                    game.currentShipStats();
                    checkLogs();
                    return;
                }
            }
        }
        while (value > flag || value <= 0);
    }

    private void UIAwaitResourcesConversion() {
        //checkLogs();
        System.out.println("What do you want to do next?");

        int value;
        do {
            checkLogs();
            System.out.println("1 - Convert more resources.");
            System.out.println("2 - Go back.");
            System.out.println("3 - See your ship stats.");
            System.out.print(">.");

            while (!sc.hasNextInt())
                sc.next();
            value = sc.nextInt();
            sc.nextLine();

            if(value == 3){
                game.currentShipStats();
                checkLogs();
                return;
            }

        }
        while(value > 2);

        if (value == 1)
            craftOptions();
        else if (value == 2)
            game.stopConvert();

    }

    private void UIAwaitDiceRoll() {
        checkLogs();
        System.out.println("You're about to have a random event happen.");
        System.out.println("[For Debug] Do you want to have a random event or a forced event?");
        int value, value1;
        do {
            System.out.println("1 - Random Event");
            System.out.println("2 - Forced Event");
            System.out.println("3 - See your Ship stats");


            System.out.print(">.");

            while (!sc.hasNextInt())
                sc.next();
            value = sc.nextInt();
            sc.nextLine(); //O nextInt não limpa o enter em buffer
        }
        while (value > 3 || value <= 0);

        if (value == 1)
            game.roll(1);
        else if(value == 3){
            game.currentShipStats();
            checkLogs();
            return;
        }
        else{
            do {
                System.out.println("Which event do you want forced?");
                System.out.println("1 - Crew Death");
                System.out.println("2 - Salvage Ship");
                System.out.println("3 - Cargo Loss");
                System.out.println("4 - Fuel Loss");
                System.out.println("5 - No Event");
                System.out.println("6 - Crew Rescue");

                System.out.print(">.");

                while (!sc.hasNextInt())
                    sc.next();
                value1 = sc.nextInt();
                sc.nextLine(); //O nextInt não limpa o enter em buffer
            }
            while(value1 > 6 || value1 <= 0);
            game.roll(-value1);
        }


    }

    private void UIGameOver() {
        checkLogs();
        if(game.getNumArtifacts() == 5)
            System.out.println("Congratulations. You have won the game.");
        else
            System.out.println("Sorry, you have lost the game.");

        int value;
        System.out.println("Final Score: " + game.getFinalScore());

        do{
            System.out.println("Do you want to save this score?");
            System.out.println("1 - Yes");
            System.out.println("2 - No");
            System.out.println("3 - Show other scores");

            while (!sc.hasNextInt())
                sc.next();
            value = sc.nextInt();
            sc.nextLine();

            if(value == 1){
                System.out.print("Username to save as: ");
                String username = sc.nextLine();
                game.saveScore(game.getFinalScore(), username);
            }
            else if(value == 3) {
                System.out.println(game.readScores());
                System.out.println("Do you want to save this score?\n1 - Yes\n2 - No");
                while (!sc.hasNextInt())
                    sc.next();
                value = sc.nextInt();
                sc.nextLine();

                if (value == 1) {
                    System.out.print("Username to save as: ");
                    String username = sc.nextLine();
                    game.saveScore(game.getFinalScore(), username);
                }
            }


        } while(value > 3);


        do {
            System.out.println("What do you want to do?");
            System.out.println(Colors.ANSI_GREEN + "1 - Play again." + Colors.ANSI_RESET);
            System.out.println(Colors.ANSI_RED + "2 - Exit" + Colors.ANSI_RESET);
            System.out.print(">.");

            while (!sc.hasNextInt())
                sc.next();
            value = sc.nextInt();
            sc.nextLine();

            if(value == 1)
                game.start();
            else if(value == 2)
                exit = true;
        }
        while(value > 2);
    }

    private void UILastChance() {
        if(game.getLastChance()){
            game.end();
            return;
        }

        System.out.println("You've ran out of fuel but you may still have resources to convert and make some more! Press Enter to continue");
        sc.nextLine();
        craftOptions();
        game.savePlanet();
        game.setLastChance();

    }

    //--------------------------------------------


    private void showShipsStats() {
        game.getShipsStats();
        checkLogs();
    }

    private void checkLogs() {
        if(game.getLogs().size() > 0){
            System.out.println(); //Linha

            for (String newMsg: game.getLogs()) {
                System.out.println(newMsg);
                //sc.nextLine();
            }

            game.clearLogs();
        }
    }

    private void craftOptions(){
        int conversion, resNew, resOld;
        System.out.println("Which item do you wish to craft?");
        System.out.println("1 - Convert one type of resource to another");
        System.out.println("2 - Replenish Drone armor. Current armor: " + game.getDroneArmor() + ". Costs 1 of each resource.");
        System.out.println("3 - 1 Extra Energy Shield. Current amount: " + game.getShipShield() + ". Costs 1 black, green and blue resources.");
        System.out.println("4 - 1 Extra Ammo. Current amount: " + game.getShipAmmo() + ". Costs 1 black and blue resources.");
        System.out.println("5 - 1 Extra Fuel. Current amount: " + game.getShipFuel() + ". Costs 1 black, red and green resources.");

        while (!sc.hasNextInt())
            sc.next();
        conversion = sc.nextInt();
        sc.nextLine(); //O nextInt não limpa o enter em buffer
        if(conversion == 1){
            do {
                System.out.println("Which resource do you want to craft?"); //black red blue green
                System.out.println(Colors.ANSI_BLACK + "1 - Black" + Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_RED + "2 - Red" + Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_BLUE + "3 - Blue" + Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_GREEN + "4 - Green" + Colors.ANSI_RESET);

                while (!sc.hasNextInt())
                    sc.next();
                resNew = sc.nextInt();
                sc.nextLine(); //O nextInt não limpa o enter em buffer

                System.out.println("From which resource?"); //black red blue green
                System.out.println(Colors.ANSI_BLACK + "1 - Black" + Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_RED + "2 - Red" + Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_BLUE + "3 - Blue" + Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_GREEN + "4 - Green" + Colors.ANSI_RESET);


                while (!sc.hasNextInt())
                    sc.next();
                resOld = sc.nextInt();
                sc.nextLine(); //O nextInt não limpa o enter em buffer
            }
            while(resNew == resOld);
            game.convert(resNew - 1, resOld - 1);
        }
        else if(conversion == 2){
            game.convert(0);
        }
        else if(conversion == 3){
            game.convert(1);
        }
        else if(conversion == 4){
            game.convert(2);
        }
        else if(conversion == 5){
            game.convert(3);
        }
    }
}
