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
        Node bestPlay = findBestPlay(game.getCurrentPlayer(),game);
        game.getMatchesInRows().set(bestPlay.getnRow(),game.getMatchesInRows().get(bestPlay.getnRow())-(bestPlay.getParentMatches()-bestPlay.getMatches()));
        System.out.println(getName()+" a enlevé " + (bestPlay.getParentMatches()-bestPlay.getMatches()) + " allumette"+(((bestPlay.getParentMatches()-bestPlay.getMatches())>1)?"s":"")+" de la rangée " + (bestPlay.getnRow()+1));
    }

    static Node buildGameTree (int matches, int nRow, int parentMatches,ArrayList<Integer> matchesInRows, Game game, Player player)
    {
        Node n = new Node();
        n.setnRow(nRow);
        n.setParentMatches(parentMatches);
        n.setMatches(matches);
        n.setPlayer(game.returnPlayerOpposite(player));
        ArrayList<Integer> matchesInRowsToModify;
        for (int i = 1;i < n.maxMatchesInTurn(matchesInRows); i++){
            for(int row:n.rowsInWhichMatchesCanBeRemoved(matchesInRows, i)){
                matchesInRowsToModify = (ArrayList<Integer>) matchesInRows.clone();
                matchesInRowsToModify.set(row, matchesInRowsToModify.get(row) - i);
                n.childNodes.add(buildGameTree(matches-i, row, matches, matchesInRowsToModify, game, n.getPlayer()));
            }
        }
        return n;
    }

    public static int computeMinimax(Node node, Game game){
        int res;
        if(node.matches == 0)
            return (node.getPlayer().equals(game.getPlayer1()))?1:-1;
        else
        if(node.getPlayer().equals(game.getPlayer1())){
            res = -1;
            for(Node n:node.getChildNodes())
                res = Math.max(res,computeMinimax(n,game));
        }
        else{
            res = 1;
            for(Node n:node.getChildNodes())
                res = Math.max(res,computeMinimax(n,game));
        }

        node.setMiniMax(res);
        return res;
    }

    public static Node findBestPlay(Player player, Game game){
        Node root = buildGameTree(game.getMatches(),0,game.getMatches(),(ArrayList<Integer>) game.getMatchesInRows().clone(), game, game.player1);
        Node moveTo = new Node();
        moveTo.setMiniMax(-2);
        //node.preBuildGameTree(matchesInRows, game.getCurrentPlayer(), game.getMatches());
        System.out.println("root node children :" + root.getChildNodes().size());

        ArrayList<Integer> childNodeValues = new ArrayList<Integer>();
        moveTo = root.getChildNodes().get(0);
        for(Node n:root.getChildNodes()){
            childNodeValues.add(computeMinimax(n,game));
            if(player.equals(game.getPlayer1()) && n.getMiniMax()>moveTo.getMiniMax())
                moveTo = n;
            else
                if(player.equals(game.getPlayer2()) && n.getMiniMax()<moveTo.getMiniMax())
                    moveTo = n;
        }
        return moveTo;
    }



}
