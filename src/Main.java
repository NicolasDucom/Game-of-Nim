import java.io.Console;
import java.util.Scanner;
import java.util.Set;

public class Main {


    public static void main(String[] args) {
        Console console = System.console();
        int input;
        Game game;
        Player player1,player2;
        Scanner in = new Scanner(System.in);
        System.out.println("~~~Jeu de Nim~~~");
        System.out.println("Selectionner types de joueurs:");
        System.out.println("1. Humain vs Humain");
        System.out.println("2. Humain vs IA");
        System.out.println("3. IA vs IA");
        System.out.println("Choisir:");
        input = in.nextInt();

        while(!(1 <= input && input <= 3)){
            System.out.println(input);
            System.out.println("Veuillez selectionner une option valide");
            System.out.println("Choisir:");
            input = in.nextInt();
        }

        switch(input){
            case 1:
                player1 = new Player(false);
                player2 = new Player(false);
                break;
            case 2:
                player1 = new Player(false);
                player2 = new Player(true);
                break;
            case 3:
                player1 = new Player(true);
                player2 = new Player(true);
                break;
            default:
                player1 = new Player(false);
                player2 = new Player(false);
        }


        game = new Game(player1,player2);

        System.out.println("Nombre de rangees d'allumettes ?");
        System.out.println("Choisir:");
        input = in.nextInt();

        while(input < 1){
            System.out.println("Veuillez selectionner un nombre de rangees supérieur a 0");
            System.out.println("Choisir:");
            input = in.nextInt();
        }


        game.setMatchesByRowCount(input);

        while(game.getMatches()>0){
            game.nextTurn();
        }

        game.printWinningPlayer();


    }
}
