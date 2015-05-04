import java.util.ArrayList;

/**
 * Created by Nicolas on 5/4/2015.
 */
public class Game {
    int matches;
    ArrayList<Integer> matchesInRows;
    Player player1, player2;

    public Game(Player p1 ,Player p2){
        player1 = p1;
        player2 = p2;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public ArrayList<Integer> getMatchesInRows() {
        return matchesInRows;
    }

    public void setMatchesInRows(ArrayList<Integer> matchesInRows) {
        this.matchesInRows = matchesInRows;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setMatchesByRowCount(int rows){
        ArrayList<Integer> matchesInRows = new ArrayList<Integer>();
        for(int i = 0; i < rows; i++){
            matchesInRows.add(1+2*i);
            matches += 1+2*i;
        }
        setMatchesInRows(matchesInRows);
    }

    public void printMatchesInRows(){
        for(int i = 0; i < matchesInRows.size(); i++){
            String lineOutput = "";
            lineOutput += i+1+":";
            for(int j = 0; j < matchesInRows.get(i); j++){
                lineOutput += " |";
            }
            System.out.println(lineOutput);
        }
    }


}
