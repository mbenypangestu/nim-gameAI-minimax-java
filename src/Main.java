import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String args[]) throws Exception {
        int stick, choose_move, option, win = 0, minus_value;

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

//        System.out.println("\n\n\n State : " + startState.getChildList().get(2).getState());
//        System.out.println("\n Value : " + startState.getChildList().get(2).getValue());
//        System.out.println("\n Childsize : " + startState.getChildList().get(2).getChildList().size());
//        System.out.println("\n Child : " + startState.getChildList().get(2).getChildList());
//        System.out.println("\n Minimax : " + startState.getChildList().get(2).getMinimax());

//        try {
//            System.out.println("Pilih Opsi : \n1. Main Pertama \n2. Main Kedua");
//            option = Integer.parseInt(userInput.readLine());
//
//            do {
//
//            } while (win == 0);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
