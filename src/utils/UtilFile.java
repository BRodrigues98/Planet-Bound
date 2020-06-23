package utils;

import java.io.*;

public class UtilFile {
    public static BufferedReader openFileTextRead(String nome) throws FileNotFoundException {
        try {
            return new BufferedReader(
                    new FileReader(nome));
        } catch (FileNotFoundException e) {
            System.out.println("Error opening file: " + nome);
            throw e;
        }
    }

    public static PrintWriter openFileTextWrite(String nome) throws IOException {
        try {
            return new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(nome, true)));
        } catch(IOException e) {
            System.out.println("Error opening file: " + nome);
            throw e;
        }
    }
}
