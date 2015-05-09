import java.util.ArrayList;

/**
 * Created by Nicolas on 5/8/2015.
 */
public class Node {
    int matches;
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

    public static int maxMatchesInTurn(ArrayList<Integer> matchesInRows){
        int max = 0;
        for(int i:matchesInRows){
            if(i>max){
                max = i;
            }
        }
        System.out.println("maxMatchesInTurn:"+max);
        return max;
    }


    public static ArrayList<Integer> rowsInWhichMatchesCanBeRemoved(ArrayList<Integer> matchesInRows, int nMatches){
        ArrayList<Integer> rows = new  ArrayList<Integer>();
        for(int i = 0; i<matchesInRows.size(); i++){
            if(matchesInRows.get(i)>nMatches)
                rows.add(i);
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

/*    public void preBuildGameTree(ArrayList<Integer> matchesInRows, Player player, int matches){
        ArrayList<Integer> matchesInRowsToModify,matchesInRowsToModifyForEachRow;
        int matchesToModify;
        System.out.println("root :" + maxMatchesInTurn(matchesInRows));
        for(int i = 1; i <= maxMatchesInTurn(matchesInRows); i++){
            matchesInRowsToModify = (ArrayList<Integer>) matchesInRows.clone();
            matchesToModify = (int) matches-i;
            System.out.println(matchesToModify);
            for(int row:rowsInWhichMatchesCanBeRemoved(matchesInRowsToModify,i)){
                System.out.println("prebuild child:"+row);
                matchesInRowsToModifyForEachRow = (ArrayList<Integer>) matchesInRowsToModify.clone();
                matchesInRowsToModifyForEachRow.set(row, matchesInRowsToModifyForEachRow.get(row) - i);
                if(matchesToModify >= 0){
                    this.buildGameTree(matchesInRowsToModifyForEachRow, player, matchesToModify, this, this);
                }
            }
        }
    }*/

    public int verticalDistanceFromNode(Node childNode, Node targetNode,int n){
       for(Node node:targetNode.childNodes){
        if(node.equals(childNode)){
            return n;
        } else {
            verticalDistanceFromNode(childNode,node,n++);
        }
       }
        return -1;
    }

    /*public void buildGameTree(ArrayList<Integer> matchesInRows, Player player, int matches, Node parentNode, Node root){
        Node node;
        node = new Node(matches);
        parentNode.childNodes.add(node);

        System.out.println("Vertical distance from root :" + verticalDistanceFromNode(this,root,0));
        ArrayList<Integer> matchesInRowsToModify,matchesInRowsToModifyForEachRow;
        int matchesToModify;
        for(int i = 1; i <= maxMatchesInTurn(matchesInRows); i++){
            matchesInRowsToModify = (ArrayList<Integer>) matchesInRows.clone();
            matchesToModify = (int) matches-i;
            System.out.println(matchesToModify);
            for(int row:rowsInWhichMatchesCanBeRemoved(matchesInRowsToModify,i)){
                matchesInRowsToModifyForEachRow = (ArrayList<Integer>) matchesInRows.clone();
                matchesInRowsToModifyForEachRow.set(row, matchesInRowsToModifyForEachRow.get(row) - i);
                //removeMatchesFromBiggestRow(matchesInRowsToModify,i);
                if(matchesToModify > 0){
                    node.buildGameTree(matchesInRowsToModifyForEachRow, player, matchesToModify,node, root);
                }
            }

        }

    }*/


}
