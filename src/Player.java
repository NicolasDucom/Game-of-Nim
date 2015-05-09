import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Nicolas on 5/4/2015.
 */
public class Player {

    boolean ai;
    String name;

    public Player( boolean ai ,String name){
        this.ai = ai;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAi() {
        return ai;
    }

    public void setAi(boolean ai) {
        this.ai = ai;
    }

    public void play(Game game){
        if(isAi())
            playAsAi(game);
        else
            playAsHuman(game.getMatchesInRows());
    }

    public void playAsHuman(ArrayList<Integer> matchesInRows){
        Scanner in = new Scanner(System.in);
        int row, matches;
        System.out.println("Sur quelle rangee voulez-vous prendre une allumette ?");
        row = in.nextInt();
        row--;
        while(!(row >= 0 && row < matchesInRows.size()) || !(matchesInRows.get(row) > 0)){
            System.out.println("Veuillez selectionner une rangee valide");
            row = in.nextInt();
        }

        System.out.println("Combien d'allumettes voulez-vous prendre sur la rangée "+(row+1)+"?");
        matches = in.nextInt();

        while((matches > matchesInRows.get(row)) ||  (matchesInRows.get(row) <= 0)){
            System.out.println("Veuillez selectionner un nombre d'allumettes valide");
            matches = in.nextInt();
        }

        matchesInRows.set(row, matchesInRows.get(row) - matches);
        System.out.println(getName()+" a enlevé " + matches + " allumette"+((matches>1)?"s":"")+" de la rangée " + (row + 1));
    }

    public void playAsAi(Game game){
        System.out.println("Building game tree...");
        ArrayList<Integer> matchesInRows = (ArrayList<Integer>) game.getMatchesInRows().clone();
        Node root = buildGameTree(game.getMatches(),matchesInRows, game, game.player1);

        //node.preBuildGameTree(matchesInRows, game.getCurrentPlayer(), game.getMatches());
        System.out.println("root node children :"+root.getChildNodes().size());
        root.printGameTree();
    }

    static Node buildGameTree (int matches,ArrayList<Integer> matchesInRows, Game game, Player player)
    {
        Node n = new Node();
        n.setMatches(matches);
        n.setPlayer(game.returnPlayerOpposite(player));
        ArrayList<Integer> matchesInRowsToModify;
        for (int i = 1;i < n.maxMatchesInTurn(matchesInRows); i++){
            for(int row:n.rowsInWhichMatchesCanBeRemoved(matchesInRows, i)){
                matchesInRowsToModify = (ArrayList<Integer>) matchesInRows.clone();
                matchesInRowsToModify.set(row, matchesInRowsToModify.get(row) - i);
                n.childNodes.add(buildGameTree(matches-1, matchesInRowsToModify, game, n.getPlayer()));
            }
        }
        return n;
    }
}
