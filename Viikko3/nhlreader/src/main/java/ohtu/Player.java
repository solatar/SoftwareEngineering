
package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String team;
    private String nationality;
    private int goals;
    private int assists;
    private int points;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }    

    public String getNationality() {
        return nationality;
    }
    
    public String getTeam() {
        return team;
    }

    public int getGoals() {
        return goals;
    }

    public int getAssists() {
        return assists;
    }

    public int getPoints() {
        return this.points;
    }    

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return this.name + " team " + this.team + " " + this.goals + " + " + this.assists + " = " + this.points;
    }

    @Override
    public int compareTo(Player t) {
        return t.getPoints()-this.getPoints();
    }
    
}
