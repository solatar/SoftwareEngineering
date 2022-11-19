package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.http.client.fluent.Request;

public class Main {
    public static void main(String[] args) throws IOException {
        String url = "https://nhlstatisticsforohtu.herokuapp.com/players";
        
        String bodyText = Request.Get(url).execute().returnContent().asString();
                
        System.out.println("json-muotoinen data:");

        Gson mapper = new Gson();
        Player[] players = mapper.fromJson(bodyText, Player[].class);
        ArrayList<Player> finPlayers = new ArrayList<>();
        System.out.println("Players from FIN");
        for (Player player : players) {
            if (player.getNationality().equals("FIN")) {
                player.setPoints(player.getGoals() + player.getAssists());
                finPlayers.add(player);                
            }
        } 
        Collections.sort(finPlayers);
        for (Player player : finPlayers) {
            System.out.println(player);
        }         
    }    
}
