import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Nicolas on 5/4/2015.
 */
public class Player {

    boolean ai; //Est-ce que le joueur est une IA ?
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

    /**
     * Si le joueur est un humain -> interface de selection
     * @param matchesInRows
     */
    public void playAsHuman(ArrayList<Integer> matchesInRows){
        Scanner in = new Scanner(System.in);
        int row, matches;
        System.out.println("Sur quelle rangee voulez-vous prendre une allumette ?");
        row = in.nextInt();

        while(!(row > 0 && row <= matchesInRows.size()) || !(matchesInRows.get(row-1) > 0)){
            System.out.println("Veuillez selectionner une rangee valide");
            row = in.nextInt();
        }
        row--;

        System.out.println("Combien d'allumettes voulez-vous prendre sur la rangée "+(row+1)+"?");
        matches = in.nextInt();

        while((matches > matchesInRows.get(row)) ||  (matchesInRows.get(row) <= 0)){
            System.out.println("Veuillez selectionner un nombre d'allumettes valide");
            matches = in.nextInt();
        }

        matchesInRows.set(row, matchesInRows.get(row) - matches);
        System.out.println(getName()+" a enlevé " + matches + " allumette"+((matches>1)?"s":"")+" de la rangée " + (row + 1));
    }

    /**
     * Si le joueur est une IA, on trouve la meilleur option de jeu
     * @param game
     */
    public void playAsAi(Game game){
        Node bestMove= findBestMove(game.getCurrentPlayer(), game);
        game.getMatchesInRows().set(bestMove.getnRow(),game.getMatchesInRows().get(bestMove.getnRow())-(bestMove.getParentMatches()-bestMove.getMatches()));
        System.out.println(getName() + " a enlevé " + (bestMove.getParentMatches() - bestMove.getMatches()) + " allumette" + (((bestMove.getParentMatches() - bestMove.getMatches()) > 1) ? "s" : "") + " de la rangée " + (bestMove.getnRow() + 1));
    }

    /**
     * Creation d'arbre de jeu (recursif), liste toutes les options de jeu possible (y compris la selection d'un meme nombre d'allumettes sur des rangees differentes)
     * @param matches
     * @param nRow
     * @param parentMatches
     * @param matchesInRows
     * @param game
     * @param player
     * @return
     */
    static Node buildGameTree (int matches, int nRow, int parentMatches,ArrayList<Integer> matchesInRows, Game game, Player player)
    {
        Node n = new Node();
        n.setnRow(nRow);
        n.setParentMatches(parentMatches);
        n.setMatches(matches);
        n.setPlayer(game.returnPlayerOpposite(player));
        ArrayList<Integer> matchesInRowsToModify;
        for (int i = 1;i <= n.maxMatchesInTurn(matchesInRows); i++){ //du minimum au maximum nombre d'allumettes qui peuvent etre enlevees
            for(int row:n.rowsInWhichMatchesCanBeRemoved(matchesInRows, i)){ //rangees sur lequelles un nombre d'allumettes peuvent etre enlevées
                matchesInRowsToModify = (ArrayList<Integer>) matchesInRows.clone(); //depassement memoir sur clones ?
                matchesInRowsToModify.set(row, matchesInRowsToModify.get(row) - i);
                n.getChildNodes().add(buildGameTree(matches-i, row, matches, matchesInRowsToModify, game, n.getPlayer()));
            }
        }
        return n;
    }

    /**
     * Attribution des points MinMax sur tous les noeuds de l'arbre
     * @param node
     * @param game
     * @return
     */
    public static int computeMinimax(Node node, Game game){
        int res;
        if(node.getMatches() == 0)
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
                res = Math.min(res,computeMinimax(n,game));
        }

        node.setMiniMax(res);
        return res;
    }

    /**
     * Retourne le noeud correspondant a la meilleur option de jeu
     * @param player
     * @param game
     * @return
     */
    public static Node findBestMove(Player player, Game game){
        Node root = buildGameTree(game.getMatches(),0,game.getMatches(),(ArrayList<Integer>) game.getMatchesInRows().clone(), game, game.getCurrentPlayer()); //Creation de l'arbre
        Node moveTo;
        moveTo = root.getChildNodes().get(0);
        for(Node n:root.getChildNodes()){
            computeMinimax(n, game); //Attribution des points MinMax sur tous les noeud fils du noeud pere
            if(player.equals(game.getPlayer1()) && n.getMiniMax() > moveTo.getMiniMax())
                moveTo = n;
            else
                if(player.equals(game.getPlayer2()) && n.getMiniMax() < moveTo.getMiniMax())
                    moveTo = n;
        }
        return moveTo;
    }



}
