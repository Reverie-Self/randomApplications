package walkWithMe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class WalkTracker implements Serializable{
    private Map<LocalDate, Walk> walks;
    private static final long serialVersionUID = 1L;

    public WalkTracker() { walks = new HashMap<>(); }

    public void addWalk(LocalDate date, Walk walk) { walks.put(date, walk); }

    public Walk getWalk(LocalDate date) { return walks.get(date); }

    public void removeWalk(LocalDate date) { walks.remove(date);}

    public void updateWalk(LocalDate date, Walk walk) { walks.put(date, walk); }

    public boolean containsDate(LocalDate date) { return walks.containsKey(date); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<LocalDate, Walk> entry : walks.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public static WalkTracker loadFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (WalkTracker) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
            return new WalkTracker(); // Return empty tracker on failure
        }
    }

    public static void main(String[] args) throws IOException {
        Walk walk = new Walk();
        walk.setElevation(100);
        walk.setDistance(1000);
        walk.setTime(100);
        walk.setSpeed(10);
        System.out.println(walk);

        LocalDate date = LocalDate.now();
        System.out.println(date.toString());


        WalkTracker walkTracker = new WalkTracker();
        walkTracker.addWalk(date, walk);

        System.out.println(walkTracker);

        walkTracker.saveToFile("dat");
    }
}
