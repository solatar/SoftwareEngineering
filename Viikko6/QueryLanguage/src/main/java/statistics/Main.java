package statistics;

import statistics.matcher.*;

public class Main {
    public static void main(String[] args) {
        String url = "https://studies.cs.helsinki.fi//nhlstats/2021-22/players.txt";

        Statistics stats = new Statistics(new PlayerReaderImpl(url));

        QueryBuilder query = new QueryBuilder();
        QueryBuilder query2 = new QueryBuilder();
        QueryBuilder query3 = new QueryBuilder();
        Matcher m1 = query.hasAtLeast(10, "assists").hasFewerThan(5, "goals").playsIn("PHI").matcher();       
        Matcher m2 = query2.playsIn("EDM").hasAtLeast(50, "points").matcher();        
        Matcher m3 = query3.oneOf(m1, m2).matcher();
    
        for (Player player : stats.matches(m3)) {
            System.out.println( player );
        }
    }
}
