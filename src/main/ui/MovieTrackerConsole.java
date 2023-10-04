package ui;

import java.io.FileNotFoundException;

// EFFECTS: Runs the program's console version
public class MovieTrackerConsole {
    public static void main(String[] args) {
        try {
            new MovieTrackerApp();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
    }
}
