import java.util.ArrayList;

/**
 * Created by Nicolas on 5/8/2015.
 */
public class Node {
    int matches;
    Player player;
    Node parentNode;
    ArrayList<Node> childNodes;

    public Node(int matches, Node parentNode){
        this.matches = matches;
        this.parentNode = parentNode;
        childNodes = new ArrayList<Node>();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(ArrayList<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public int getMatches() {
        return matches;
    }

    public void setMatches(int matches) {
        this.matches = matches;
    }

    public int maxMatchesInTurn(ArrayList<Integer> matchesInRows){
        int max = 0;
        for(int i:matchesInRows){
            if(i>max){
                max = i;
            }
        }
       // System.out.println("maxMatchesInTurn:"+max);
        return max;
    }

    public void removeMatchesFromBiggestRow(ArrayList<Integer> matchesInRows, int nMatches){
        int biggestRow = 0;
        int max = 0;
        for(int i = 0; i < matchesInRows.size(); i++){
            if(matchesInRows.get(i)>max) {
                biggestRow = i;
                max = matchesInRows.get(i);
            }
        }
        //System.out.println("Removing "+max+" from row "+(biggestRow+1));
        matchesInRows.set(biggestRow, matchesInRows.get(biggestRow) - nMatches);
    }

    public Node buildGameTree(ArrayList<Integer> matchesInRows, Player player, int matches, Node parentNode, int n){
        Node node;
        if(parentNode!=null){
            node = parentNode;
        }
        else {
        node = new Node(matches,null);
        }
        ArrayList<Integer> matchesInRowsToModify;
        int matchesToModify;
        for(int i = 1; i<=maxMatchesInTurn(matchesInRows); i++){
            matchesInRowsToModify = (ArrayList<Integer>) matchesInRows.clone();
            matchesToModify = (int) matches-i;
            //System.out.println(matchesToModify);
            removeMatchesFromBiggestRow(matchesInRowsToModify,i);
            if(matches > 0){
                node.childNodes.add(buildGameTree(matchesInRowsToModify, player, matchesToModify, null, n++));
            } else {
                System.out.println(n);
                return node;
            }
        }

        return node;
    }
}
