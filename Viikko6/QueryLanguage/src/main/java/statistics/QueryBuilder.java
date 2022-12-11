package statistics;

import statistics.matcher.All;
import statistics.matcher.And;
import statistics.matcher.HasAtLeast;
import statistics.matcher.Matcher;
import statistics.matcher.Or;
import statistics.matcher.PlaysIn;
import statistics.matcher.HasFewerThan;

public class QueryBuilder implements Matcher {    
    Matcher matcher;
    Matcher[] matchers;
    
    public QueryBuilder() {
        this.matcher = new All();
    }

    public Matcher matcher() {
        System.out.println("matcher called");
        return this.matcher;
    }  
    
    public QueryBuilder playsIn(String team) {
        this.matcher = new And(matcher, new PlaysIn(team));
        return this;
    }

    public QueryBuilder hasAtLeast(int value, String category) {
        this.matcher = new And(matcher, new HasAtLeast(value, category));
        return this;
    }

    public QueryBuilder hasFewerThan(int value, String category) {
        this.matcher = new And(matcher, new HasFewerThan(value, category));
        return this;
    }

    public QueryBuilder oneOf(Matcher...matchers) {
        this.matcher = new Or(matchers);
        return this;
    }

    @Override
    public boolean matches(Player p) {
        return p.equals(p);
    }    
}
