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

    /**
     * On doit savoir qui vient de jouer pour alterner de joueur sur le prochain tour
     * @param lastPlayertoHavePlayed
     */
    public void setLastPlayertoHavePlayed(Player lastPlayertoHavePlayed) {
        this.lastPlayertoHavePlayed = lastPlayertoHavePlayed;
        if(getLastPlayertoHavePlayed().equals(getPlayer1()))
            setCurrentPlayer(getPlayer2());
        else
            setCurrentPlayer(getPlayer1());
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

    /**
     * On a notre nombre de rangées, on leur attribue un nombre d'allumettes
     * @param rows
     */
    public void setMatchesByRowCount(int rows){
        ArrayList<Integer> matchesInRows = new ArrayList<Integer>();
        for(int i = 0; i < rows; i++){
            matchesInRows.add(1+2*i);
        }
        setMatchesInRows(matchesInRows);
        setMatches();
    }

    public void printMatchesInRows(){
        for(int i = 0; i < getMatchesInRows().size(); i++){
            String lineOutput = "";
            lineOutput += i+1+":";
            for(int j = 0; j < getMatchesInRows().get(i); j++){
                lineOutput += " |";
            }
            System.out.println(lineOutput);
        }
    }

    /**
     * On change de joueur et on rejoue
     */
    public void nextTurn(){
        printMatchesInRows();
        if (this.getLastPlayertoHavePlayed() == null)
            this.setLastPlayertoHavePlayed(this.getPlayer2());
        if (getLastPlayertoHavePlayed().equals(getPlayer2())){
            getPlayer1().play(this);
            setMatches();
            setLastPlayertoHavePlayed(getPlayer1());
        } else if(getLastPlayertoHavePlayed().equals(getPlayer1())){
            getPlayer2().play(this);
            setMatches();
            setLastPlayertoHavePlayed(getPlayer2());
        }
    }

    /**
     * Le dernier joueur a avoir joué a perdu
     */
    public void printWinningPlayer(){
        System.out.println("Le "+getCurrentPlayer().getName()+" a gagné");
    }


}
