import java.util.ArrayList;

/**
 * Created by Nicolas on 5/8/2015.
 */
public class Node {
    int matches;
    int parentMatches;
    int nRow;
    int miniMax;
    Player player;
    ArrayList<Node> childNodes;

    public Node(int matches){
        this.matches = matches;
        childNodes = new ArrayList<Node>();
    }

    public Node(){
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

    public int getnRow() {
        return nRow;
    }

    public void setnRow(int nRow) {
        this.nRow = nRow;
    }

    public int getParentMatches() {
        return parentMatches;
    }

    public void setParentMatches(int parentMatches) {
        this.parentMatches = parentMatches;
    }

    public int getMiniMax() {
        return miniMax;
    }

    public void setMiniMax(int miniMax) {
        this.miniMax = miniMax;
    }

    /**
     * Retourne la plus grande rangee (nombre max d'allumettes qui peuvent etre enlevées en un tour...)
     * @param matchesInRows
     * @return
     */
    public static int maxMatchesInTurn(ArrayList<Integer> matchesInRows){
        int max = 0;
        for(int i:matchesInRows){
            if(i>max){
                max = i;
            }
        }
        return max;
    }

    /**
     * retourne toutes les rangées ou le nombre d'allumettes peut etre enlevé
     * @param matchesInRows
     * @param nMatches
     * @return
     */
    public static ArrayList<Integer> rowsInWhichMatchesCanBeRemoved(ArrayList<Integer> matchesInRows, int nMatches){
        ArrayList<Integer> rows = new  ArrayList<Integer>();
        for(int i = 0; i<matchesInRows.size(); i++){
            if(matchesInRows.get(i)>=nMatches){
                rows.add(i);
            }
        }
        return rows;
    }


    public void printGameTree(){
        System.out.println("Node");
        System.out.println(this.getMatches());
        for(Node n:this.getChildNodes()){
            System.out.println(" n ["+n.getPlayer().getName()+"]: " + n.getMatches());
            n.printGameTree();
        }
    }

    /**
     * Distance verticale entre deux Noeuds
     * @param childNode
     * @param targetNode
     * @param n
     * @return
     */
    public int verticalDistanceFromNode(Node childNode, Node targetNode,int n){
       for(Node node:targetNode.getChildNodes()){
        if(node.equals(childNode)){
            return n;
        } else {
            verticalDistanceFromNode(childNode,node,n++);
        }
       }
        return -1;
    }

}
