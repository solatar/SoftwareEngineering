
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ohtuesimerkki.Statistics;
import ohtuesimerkki.Player;
import ohtuesimerkki.Reader;

public class StatisticsTest {
    
    Statistics statistics;
    
    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<>();
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    };  
    
   
    @Before
    public void setUp() {
        statistics = new Statistics(readerStub);
    }
    
    @Test 
    public void returnNullIfPlayerNotFound() {
        assertNull(statistics.search("Koivu"));
    }
    
    @Test
    public void returnPlayerIfFound() {
        Player player = statistics.search("Gretzky");
        String result = player.toString();
        assertEquals("Gretzky              EDM 35 + 89 = 124", result);
    }
    
    @Test
    public void playersInSameTeamAreFound() {
        List<Player> team = statistics.team("EDM");
        assertEquals("3", Integer.toString(team.size()));
    }
    
    @Test
    public void topScorerIsFound() {
        List<Player> scorers = statistics.topScorers(1);
        Player player = statistics.search("Gretzky");
        assertTrue(scorers.contains(player));
    }
    
    @Test
    public void topPointmakersAreFound() {
        List<Player> scorers = statistics.topScorers(2, SortBy.POINTS);
        Player player = statistics.search("Yzerman");
        assertTrue(scorers.contains(player));
    }    
    
    @Test
    public void topGoalmakerIsFound() {
        List<Player> scorers = statistics.topScorers(1, SortBy.GOALS);
        Player player = statistics.search("Lemieux");
        assertTrue(scorers.contains(player));
    }
    
    @Test
    public void topAssistingIsFound() {
        List<Player> scorers = statistics.topScorers(1, SortBy.ASSISTS);
        Player player = statistics.search("Gretzky");
        assertTrue(scorers.contains(player));
    }
    

}
