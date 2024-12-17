package walkWithMe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class WalkTrackerApp extends JFrame {
    private WalkTracker walkTracker = new WalkTracker();
    private JTextArea displayArea;
    private JTextField dateField, elevationField, distanceField, timeField, speedField;
    private static final String FILE_NAME = "walkWithMe/walks.dat";

    public WalkTrackerApp() {
        setTitle("Walk Tracker");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Fields
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        dateField = new JTextField();
        inputPanel.add(dateField);

        inputPanel.add(new JLabel("Elevation (m):"));
        elevationField = new JTextField();
        inputPanel.add(elevationField);

        inputPanel.add(new JLabel("Distance (m):"));
        distanceField = new JTextField();
        inputPanel.add(distanceField);

        inputPanel.add(new JLabel("Time (min):"));
        timeField = new JTextField();
        inputPanel.add(timeField);

        inputPanel.add(new JLabel("Speed (m/min):"));
        speedField = new JTextField();
        inputPanel.add(speedField);

        add(inputPanel, BorderLayout.NORTH);

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Walk");
        JButton removeButton = new JButton("Remove Walk");
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(loadButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Event Listeners
        addButton.addActionListener(e -> addWalk());
        removeButton.addActionListener(e -> removeWalk());
        saveButton.addActionListener(e -> saveWalks());
        loadButton.addActionListener(e -> loadWalks());

        loadWalks(); // Load existing data on startup
    }

    private void addWalk() {
        try {
            LocalDate date = LocalDate.parse(dateField.getText());
            if (walkTracker.containsDate(date)) {
                JOptionPane.showMessageDialog(this, "Walk already exists for this date.");
                return;
            }

            double elevation = Double.parseDouble(elevationField.getText());
            double distance = Double.parseDouble(distanceField.getText());
            double time = Double.parseDouble(timeField.getText());
            double speed = Double.parseDouble(speedField.getText());

            walkTracker.addWalk(date, new Walk(elevation, distance, time, speed));
            refreshDisplay();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check your values.");
        }
    }

    private void removeWalk() {
        try {
            LocalDate date = LocalDate.parse(dateField.getText());
            if (walkTracker.containsDate(date)) {
                walkTracker.removeWalk(date);
                refreshDisplay();
            } else {
                JOptionPane.showMessageDialog(this, "No walk found for the given date.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid date format.");
        }
    }

    private void saveWalks() {
        walkTracker.saveToFile(FILE_NAME);
    }

    private void loadWalks() {
        walkTracker = WalkTracker.loadFromFile(FILE_NAME);
        refreshDisplay();
    }

    private void refreshDisplay() {
        displayArea.setText(walkTracker.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WalkTrackerApp().setVisible(true));
    }
}

