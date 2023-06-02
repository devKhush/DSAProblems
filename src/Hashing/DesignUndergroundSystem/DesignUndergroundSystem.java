package Hashing.DesignUndergroundSystem;
import java.util.HashMap;

// https://leetcode.com/problems/design-underground-system/description/

public class DesignUndergroundSystem {
    // Map to store the mapping from entry station to all exit stations. Also store
    // the total travel time for every entry and exit station pair
    // the no. of people travels for every entry and exit station pair
    HashMap<String, HashMap<String, int[]>> database;

    // Store the info when a person Checks In
    HashMap<Integer, Object[]> entriesInfo;
    public DesignUndergroundSystem() {
        database = new HashMap<>();
        entriesInfo = new HashMap<>();
    }

    public void checkIn(int id, String sourceStation, int entryTime) {
        entriesInfo.put(id, new Object[]{sourceStation, entryTime});
    }

    public void checkOut(int id, String destinationStation, int exitTime) {
        String sourceStation = (String) entriesInfo.get(id)[0];
        int entryTime = (int) entriesInfo.get(id)[1];

        database.putIfAbsent(sourceStation, new HashMap<>());
        database.get(sourceStation).putIfAbsent(destinationStation, new int[2]);

        // Add the entries time duration b.w these two stations
        database.get(sourceStation).get(destinationStation)[0] += exitTime - entryTime;
        // Increment no. of people travelled b/w these two stations
        database.get(sourceStation).get(destinationStation)[1]++;
    }

    public double getAverageTime(String startStation, String endStation) {
        int[] timings = database.get(startStation).get(endStation);
        return timings[0] / (double)timings[1];
    }
}
