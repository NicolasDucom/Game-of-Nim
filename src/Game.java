import java.util.ArrayList;

/**
 * Created by Nicolas on 5/4/2015.
 */
public class Game {
    int matches;
    ArrayList<Integer> matchesInRows;
    Player player1;
    Player player2;
    Player lastPlayertoHavePlayed;
    Player currentPlayer;

    public Game(Player p1 ,Player p2){
        player1 = p1;
        player2 = p2;
        lastPlayertoHavePlayed = null;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches() {
        matches = 0;
        for(int i:getMatchesInRows()){
            matches += i;
        }
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

    public Player getLastPlayertoHavePlayed() {
        return lastPlayertoHavePlayed;
    }

    public void setLastPlayertoHavePlayed(Player lastPlayertoHavePlayed) {
        this.lastPlayertoHavePlayed = lastPlayertoHavePlayed;
        if(getLastPlayertoHavePlayed().equals(player1))
            setCurrentPlayer(player2);
        else
            setCurrentPlayer(player1);
    }

    public Player returnPlayerOpposite(Player player){
        if(player.equals(player1))
            return player2;
        else
            return player1;

    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setMatchesByRowCount(int rows){
        ArrayList<Integer> matchesInRows = new ArrayList<Integer>();
        for(int i = 0; i < rows; i++){
            matchesInRows.add(1+2*i);
        }
        setMatchesInRows(matchesInRows);
        setMatches();
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

    public void nextTurn(){
        printMatchesInRows();
        if (this.getLastPlayertoHavePlayed() == null)
            this.setLastPlayertoHavePlayed(this.getPlayer2());
        if (lastPlayertoHavePlayed.equals(player2)){
            player1.play(this);
            setMatches();
            setLastPlayertoHavePlayed(player1);
        } else if(lastPlayertoHavePlayed.equals(player1)){
            player2.play(this);
            setMatches();
            setLastPlayertoHavePlayed(player2);
        }
    }

    public void printWinningPlayer(){
        System.out.println("Le "+(lastPlayertoHavePlayed.equals(player1)?"Joueur 2":"Joueur 1")+" a gagné");
    }


}
