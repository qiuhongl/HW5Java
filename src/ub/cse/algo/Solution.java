package ub.cse.algo;

import java.util.ArrayList;
import java.util.Comparator;

public class Solution {
    
    private int _n_rallies;
    private ArrayList<int[]> _rallies;
    
    public Solution (int n_rallies, ArrayList<int[]> rallies){
        _n_rallies = n_rallies;
        _rallies = rallies;
    }
    
    /**
     * You should fill this in on your own. Rallies is list of tuples, in the form (rally duration, rally deadline).
     * Your output should also be a list of tuples, of the form (rally id, start time of rally). If no possible
     * schedule exists, you should return an empty list.
     * @return
     */
    public ArrayList<int[]> getSchedule(){
        // Initialize the output list
        ArrayList<int[]> schedule = new ArrayList<>();

        // Initialize a list of tuples in the form (rally id, rally duration, rally deadline)
        ArrayList<int[]> rallyList = new ArrayList<>();
        for (int i = 0; i < _rallies.size(); i++) {
            int[] info = new int[] {i, _rallies.get(i)[0], _rallies.get(i)[1]};
            rallyList.add(info);
        }

        // Sort them in an increasing order of the deadline
        rallyList.sort(Comparator.comparingInt(rally -> rally[2]));

        // Set the start time
        int f = 0;

        // If there is no way to finish any one of the rally before its deadline, then we will set this to true.
        boolean nuke = false;

        // Run a greedy algorithm (Scheduling to Minimize Lateness)
        for (int[] rally : rallyList) {
            int[] plan = new int[] {rally[0], f};
            int duration = rally[1];
            int deadline = rally[2];
            f += duration;

            if (f > deadline) {
                nuke = true;
                break;
            }

            schedule.add(plan);
        }

        if (nuke) {
            return new ArrayList<>();
        }

        return schedule;
    }
}
