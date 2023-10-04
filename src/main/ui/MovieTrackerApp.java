package ui;

import model.Movie;
import model.MovieTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represents the movie tracker application
public class MovieTrackerApp {
    private static final String JSON_STORE = "./data/movietracker.json";
    private MovieTracker movieTracker;
    private Scanner userInput;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: Initializes the movie tracker
    public MovieTrackerApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runMovieTracker();
    }

    // MODIFIES: this
    // EFFECTS: takes all input that user enters and processes it accordingly
    private void runMovieTracker() {
        boolean quit = false;
        String reply;
        userInput = new Scanner(System.in);
        userInput.useDelimiter("\n");
        movieTracker = new MovieTracker("Shaun");

        while (!quit) {
            menu();
            reply = userInput.next();
            if (reply.equals("exit")) {
                quit = true;
            } else if (reply.equals("add")) {
                addingMovie();
            } else if (reply.equals("view")) {
                movieDetails();
            } else if (reply.equals("save")) {
                saveMovieTracker();
            } else if (reply.equals("load")) {
                loadMovieTracker();
            } else {
                System.out.println("Sorry, that is not a valid option");
            }
        }

        System.out.println("See you again next time!");
    }

    // EFFECTS: Prints out starting options for the user to choose from
    private void menu() {
        System.out.println();
        System.out.println("Welcome to the movie tracker app!");
        System.out.println("Enter 'add' to add a movie to the list");
        System.out.println("Enter 'view' to view a list of movies that you have seen");
        System.out.println("Enter 'save' to save the movie tracker list to file");
        System.out.println("Enter 'load' to load a movie tracker list from file");
        System.out.println("Enter 'exit' to close the app");
    }

    // MODIFIES: this
    // EFFECTS: Allows the user to add a movie to the movie tracker by keying in the appropriate information
    private void addingMovie() {
        System.out.println("\nEnter the name of the movie");
        String name = userInput.next();
        System.out.println("\nEnter the date that you watched the movie");
        String date = userInput.next();
        System.out.println("\nEnter your rating 1-5 for the movie");
        int rating = userInput.nextInt();
        System.out.println("\nEnter a brief description of the movie");
        String desc = userInput.next();

        Movie m = new Movie(name, date, rating, desc);
        movieTracker.addMovie(m);
    }

    // MODIFIES: this
    // EFFECTS: prints out options for the user to choose from to allow the user to access
    //          information from a particular movie
    private void movieDetails() {
        movieList();
        String name = userInput.next();
        System.out.println();
        if (movieTracker.getMovieNames().contains(name)) {
            subMenu();
            String reply = userInput.next();
            if (reply.equals("description")) {
                System.out.println();
                System.out.println("Description: " + movieTracker.getMovieDescription(name));
            } else if (reply.equals("rating")) {
                System.out.println();
                System.out.println("Rating: " + movieTracker.getMovieRating(name) + "/5");
            } else if (reply.equals("date")) {
                System.out.println();
                System.out.println("Date watched: " + movieTracker.getMovieDate(name));
            } else {
                System.out.println();
                System.out.println("You entered an invalid option");
            }
        } else {
            System.out.println("Sorry, there is no such movie");
        }
    }

    // EFFECTS: Prints out menu of possible attributes to view about a movie
    private void subMenu() {
        System.out.println("Enter 'description' for a description of this movie");
        System.out.println("Enter 'rating' to see the rating of this movie");
        System.out.println("Enter 'date' to retrieve the date that you watched this movie");
    }

    // EFFECTS: prints out a list of all the movie names in the movie tracker
    private void movieList() {
        System.out.println();
        System.out.println(movieTracker.getMovieNames());
        System.out.println();
        System.out.println("Enter the name of a movie to view more details about it!");
    }

    // EFFECTS: Saves the movie tracker list to the file
    private void saveMovieTracker() {
        try {
            jsonWriter.open();
            jsonWriter.write(movieTracker);
            jsonWriter.close();
            System.out.println("Saved " + movieTracker.getUserName() + "'s movie list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("File could not be recorded: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads a movie tracker list from file
    private void loadMovieTracker() {
        try {
            movieTracker = jsonReader.read();
            System.out.println("Loaded " + movieTracker.getUserName() + "'s movie list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("File could not be read: " + JSON_STORE);
        }
    }
}


