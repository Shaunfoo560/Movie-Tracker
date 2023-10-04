package ui;

import model.EventLog;
import model.Movie;
import model.MovieTracker;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.Boolean.TRUE;

// Represents the movie tracker GUI
public class MovieTrackerGUI extends JFrame {
    ImageIcon star;
    ImageIcon tick;
    private DefaultListModel movieModel;
    private JList movieList = new JList();
    private JButton addMovieButt = new JButton();
    private JButton updateMovieButt = new JButton();
    private JButton saveList = new JButton();
    private JButton loadList = new JButton();
    private JLabel projectName = new JLabel();

    private JLabel movieName = new JLabel();
    private JTextField textMovieName = new JTextField();

    private JLabel rating = new JLabel();
    private JTextField textRating = new JTextField();

    private JLabel date = new JLabel();
    private JTextField textDate = new JTextField();

    private JLabel description = new JLabel();
    private JTextField textDescription = new JTextField();

    private static final String JSON_STORE = "./data/movietracker.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private MovieTracker movieTracker;

    // EFFECTS: Initialization for the movie tracker GUI
    public MovieTrackerGUI() throws FileNotFoundException {
        super("Movie Tracker Project");
        initializer();
        movieNameInitializer();
        ratingInitializer();
        dateInitializer();
        descriptionInitializer();

        this.add(movieList());
        this.add(addMovieButt());
        this.add(updateMovieButt());
        this.add(saveList());
        this.add(loadList());
        this.add(projectName);
        this.add(movieName);
        this.add(rating);
        this.add(date);
        this.add(description);
        this.add(textMovieName);
        this.add(textRating);
        this.add(textDate);
        this.add(textDescription);

        printOnClose();

        this.setVisible(TRUE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the JSON reader, writer, the movietracker, and the images
    public void initializer() {
        this.setTitle("Movie Tracker");
        this.getContentPane().setLayout(null);
        this.setSize(900, 500);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        movieTracker = new MovieTracker("Shaun");
        star = new ImageIcon(new ImageIcon("star.png").getImage().getScaledInstance(
                120, 100, Image.SCALE_DEFAULT));

        tick = new ImageIcon(new ImageIcon("tick.png").getImage().getScaledInstance(
                120, 100, Image.SCALE_DEFAULT));

        projectName.setText("Movie Tracker");
        projectName.setFont(new Font("Arial", Font.BOLD, 22));
        projectName.setBounds(375, 10, 150, 50);
    }

    // EFFECTS: prints all events in the eventlog on application close
    public void printOnClose() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                EventLog.getInstance().iterator().forEachRemaining(
                        event -> {
                            System.out.println(event.toString());
                        });
                System.exit(0);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: Initializes the movie name section of the application
    public void movieNameInitializer() {
        movieName.setText("Movie Name");
        movieName.setHorizontalAlignment(SwingConstants.CENTER);
        movieName.setFont(new Font("Arial", Font.BOLD, 16));
        movieName.setBounds(460, 50, 400, 50);

        textMovieName.setHorizontalAlignment(SwingConstants.CENTER);
        textMovieName.setFont(new Font("Arial", Font.PLAIN, 14));
        textMovieName.setBounds(460, 93, 400, 30);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the rating section of the application
    public void ratingInitializer() {
        rating.setText("Rating");
        rating.setHorizontalAlignment(SwingConstants.CENTER);
        rating.setFont(new Font("Arial", Font.BOLD, 16));
        rating.setBounds(460, 115, 400, 50);

        textRating.setHorizontalAlignment(SwingConstants.CENTER);
        textRating.setFont(new Font("Arial", Font.PLAIN, 14));
        textRating.setBounds(460, 158, 400, 30);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the movie date section of the application
    public void dateInitializer() {
        date.setText("Date Watched");
        date.setHorizontalAlignment(SwingConstants.CENTER);
        date.setFont(new Font("Arial", Font.BOLD, 16));
        date.setBounds(460, 185, 400, 50);

        textDate.setHorizontalAlignment(SwingConstants.CENTER);
        textDate.setFont(new Font("Arial", Font.PLAIN, 14));
        textDate.setBounds(460, 228, 400, 30);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the description section of the application
    public void descriptionInitializer() {
        description.setText("Description");
        description.setHorizontalAlignment(SwingConstants.CENTER);
        description.setFont(new Font("Arial", Font.BOLD, 16));
        description.setBounds(460, 255, 400, 50);

        textDescription.setHorizontalAlignment(SwingConstants.CENTER);
        textDescription.setFont(new Font("Arial", Font.PLAIN, 14));
        textDescription.setBounds(460, 298, 400, 30);
    }

    // MODIFIES: this
    // EFFECTS: Initializes the button to add movie to the list
    private JButton addMovieButt() {
        addMovieButt.setText("Add Movie");
        addMovieButt.setBounds(460, 390, 400, 40);
        addMovieButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(addMovieButt, "Movie Added!", "",
                        JOptionPane.INFORMATION_MESSAGE, tick);
                Movie m = new Movie(textMovieName.getText(),
                        textDate.getText(),
                        Integer.parseInt(textRating.getText()),
                        textDescription.getText());

                movieTracker.addMovie(m);
                reloadList();
            }
        });
        return addMovieButt;
    }

    // MODIFIES: this
    // EFFECTS: Initializes the button to update a movie entry
    private JButton updateMovieButt() {
        updateMovieButt.setText("Update Movie");
        updateMovieButt.setBounds(460, 340, 400, 40);
        updateMovieButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(updateMovieButt, "Movie Updated!", "",
                        JOptionPane.INFORMATION_MESSAGE, tick);
                int movieIndex = movieList.getSelectedIndex();
                if (movieIndex >= 0) {
                    Movie m = movieTracker.getMovies().get(movieIndex);
                    m.setName(textMovieName.getText());
                    m.setRating(Integer.parseInt(textRating.getText()));
                    m.setDate(textDate.getText());
                    m.setDescription(textDescription.getText());
                    reloadList();
                }
            }
        });
        return updateMovieButt;
    }

    // MODIFIES: this
    // EFFECTS: Initializes the list that holds the movies you have added, also enables selection capabilities
    private JList movieList() {
        movieModel = new DefaultListModel();
        movieList.setModel(movieModel);
        movieList.setBounds(25, 60, 400, 270);
        movieList.setFont(new Font("Arial", Font.BOLD, 14));
        movieList.setBackground(Color.cyan);
        movieList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int movieIndex = movieList.getSelectedIndex();
                if (movieIndex >= 0) {
                    Movie m = movieTracker.getMovies().get(movieIndex);
                    textMovieName.setText(m.getName());
                    textRating.setText(Integer.toString(m.getRating()));
                    textDate.setText(m.getDate());
                    textDescription.setText(m.getDescription());
                }
            }
        });
        return movieList;
    }

    // MODIFIES: this
    // EFFECTS: Initializes the button that allows you to save your movie list to a JSON file
    private JButton saveList() {
        saveList.setText("Save List");
        saveList.setBounds(25, 340, 400, 40);
        saveList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(saveList, "Movie list saved!", "",
                        JOptionPane.INFORMATION_MESSAGE, star);
                try {
                    jsonWriter.open();
                    jsonWriter.write(movieTracker);
                    jsonWriter.close();
                } catch (FileNotFoundException ex) {
                    System.out.println("File could not be recorded: " + JSON_STORE);
                }
                reloadList();
            }
        });
        return saveList;
    }

    // MODIFIES: this
    // EFFECTS: Initializes the button that allows you to load your movie list from a JSON file
    private JButton loadList() {
        loadList.setText("Load List");
        loadList.setBounds(25, 390, 400, 40);
        loadList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(saveList, "Movie list loaded!", "",
                        JOptionPane.INFORMATION_MESSAGE, star);
                try {
                    movieTracker = jsonReader.read();
                } catch (IOException ex) {
                    System.out.println("File could not be read: " + JSON_STORE);
                }
                reloadList();
            }
        });
        return loadList;
    }

    // EFFECTS: reloads the page to get most updated data
    public void reloadList() {
        movieModel.removeAllElements();
        for (Movie m: movieTracker.getMovies()) {
            movieModel.addElement(m.getName());
        }
    }

    // EFFECTS: Runs the program's GUI
    public static void main(String[] args) {
        try {
            new MovieTrackerGUI().setVisible(true);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}