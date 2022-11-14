package ohtuesimerkki;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Statistics {
      
    private List<Player> players;    

    public Statistics(Reader reader) {
        players = reader.getPlayers();   
    }

    public Player search(String name) {
        for (Player player : players) {
            if (player.getName().contains(name)) {
                return player;
            }
        }
        return null;
    }

    public List<Player> team(String teamName) {
        ArrayList<Player> playersOfTeam = new ArrayList<Player>();        
        for (Player player : players) {
            if ( player.getTeam().equals(teamName)) {
                playersOfTeam.add(player);
            }
        }        
        return playersOfTeam;
    }

    public List<Player> topScorers(int howMany) {
        Comparator<Player> pointComparator = (p1, p2) -> p1.getPoints() - p2.getPoints();
        players.sort(pointComparator.reversed());
        ArrayList<Player> topScorers = new ArrayList<Player>();
        Iterator<Player> playerIterator = players.iterator();
        
        while (howMany>=0) {
            topScorers.add(playerIterator.next());            
            howMany--;
        }        
        return topScorers;
    }
    
    public List<Player> topScorers(int howMany, SortBy value) {
        if (value == SortBy.POINTS) {
            Comparator<Player> pointComparator = (p1, p2) -> p1.getPoints() - p2.getPoints();
            players.sort(pointComparator.reversed());
        }
        if (value == SortBy.GOALS) {
            Comparator<Player> goalComparator = (p1, p2) -> p1.getGoals() - p2.getGoals();
            players.sort(goalComparator.reversed());
        }    
        if (value == SortBy.ASSISTS) {
            Comparator<Player> assistsComparator = (p1, p2) -> p1.getAssists() - p2.getAssists();
            players.sort(assistsComparator.reversed());
        }        
        ArrayList<Player> top = new ArrayList<Player>();
        Iterator<Player> playerIterator = players.iterator();        
        while (howMany>=0) {
            top.add( playerIterator.next() );            
            howMany--;
        }        
        return top;        
    }
}
