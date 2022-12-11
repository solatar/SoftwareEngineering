
package statistics.matcher;

import statistics.Player;

public class PlaysIn implements Matcher {
    Matcher matcher;
    private String team;

    public PlaysIn(String team) {
        this.team = team;
    }        
    
    @Override
    public boolean matches(Player p) {
        return p.getTeam().contains(team);
    }
    
}
