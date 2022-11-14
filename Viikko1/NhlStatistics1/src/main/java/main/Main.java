package main;

import ohtuesimerkki.PlayerReader;
import ohtuesimerkki.Statistics;
import ohtuesimerkki.Player;
import ohtuesimerkki.SortBy;


public class Main {
    
    public static void main(String[] args) {
        Statistics stats = new Statistics(new PlayerReader("https://studies.cs.helsinki.fi/nhlstats/2021-22/players.txt"));
          
        System.out.println("Philadelphia Flyers");
        for (Player player : stats.team("PHI") ) {
            System.out.println( player );
        }
        
        System.out.println("Top scorers by points");
        for (Player player : stats.topScorers(10) ) {
            System.out.println( player );
        }

        System.out.println("");

        System.out.println("Top scorers by points, with parameter");
        for (Player player : stats.topScorers(10, SortBy.POINTS)) {
            System.out.println( player );
        }

        System.out.println("");

        System.out.println("Top scorers by goals");
        for (Player player : stats.topScorers(10, SortBy.GOALS)) {
            System.out.println( player );
        }

        System.out.println("");

        System.out.println("Top scorers by assists");
        for (Player player : stats.topScorers(10, SortBy.ASSISTS)) {
            System.out.println( player );
        }         
    }
}
