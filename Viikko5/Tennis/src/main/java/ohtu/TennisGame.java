package ohtu;

public class TennisGame {  
    private String player1Name;
    private String player2Name;  
    private int player1Score;
    private int player2Score;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.player1Score = 0;
        this.player2Score = 0;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            player1Score += 1;
        else
            player2Score += 1;
    }

    public String getScore() {
        String state = "";
        if (player1Score < 4 && player2Score < 4 && !(player1Score + player2Score == 6)) {
            String[] scoreNames = new String[]{"Love", "Fifteen", "Thirty", "Forty"}; 
            state = scoreNames[player1Score];
            return (player1Score == player2Score) ? state + "-All" : state + "-" + scoreNames[player2Score];
        } else {
            if (player1Score == player2Score)
                return "Deuce";
            state = player1Score > player2Score ? player1Name : player2Name;
            return ((player1Score-player2Score)*(player1Score-player2Score) == 1) ? "Advantage " + state : "Win for " + state;
        }
    }

    public static void main(String[] args) {
        TennisGame game = new TennisGame("Martina", "Steffi");
        System.out.println(game.getScore());    
        game.wonPoint("Martina");
        System.out.println(game.getScore());    
        game.wonPoint("Martina");
        System.out.println(game.getScore());    
        game.wonPoint("Steffi");
        System.out.println(game.getScore());    
        game.wonPoint("Martina");
        System.out.println(game.getScore());    
        game.wonPoint("Steffi");
        System.out.println(game.getScore());
        game.wonPoint("Steffi");
        System.out.println(game.getScore());
    }    
}