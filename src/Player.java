import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Nicolas on 5/4/2015.
 */
public class Player {

    boolean ai;

    public Player( boolean ai){
        this.ai = ai;
    }

    public boolean isAi() {
        return ai;
    }

    public void setAi(boolean ai) {
        this.ai = ai;
    }

    public void play(ArrayList<Integer> matchesInRows){
        if(isAi())
            playAsAi(matchesInRows);
        else
            playAsHuman(matchesInRows);
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
        System.out.println("le joueur a enlevé " + matches + " allumettes de la rangée " + (row + 1));
    }

    public void playAsAi(ArrayList<Integer> matchesInRows){
        System.out.println("AI play not implemented yet");
    }
}
