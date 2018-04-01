import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String args[]) throws Exception {
        int stick, choose_move;

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Masukkan jumlah stick : ");
        stick       = Integer.parseInt(userInput.readLine());

        System.out.print("Main : \n1. Pertama \n2. Kedua \nPilih (1 / 2) : ");
        choose_move = Integer.parseInt(userInput.readLine());

        State startState    = new State(stick);
        Tree tree           = new Tree(startState);
        tree.build();

        Game game = new Game(startState, choose_move);
        game.start();
    }
}
