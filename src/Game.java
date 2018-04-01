import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Game {
    private State currentState;
    private String currentMove;
    private BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));;

    public Game(State state, int move) {
        this.currentState = state;

        if (move == 1) this.currentMove = "human";
        else this.currentMove = "agent";
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public void start() {
        if (currentMove.equals("human"))
            this.humanMove();
        else
            this.agentMove();
    }

    public void humanMove() {
        int choice;
        State choosen_state;
        System.out.println("\n\n---------- Human Move ----------");
        this.showCurrentState();

        if (!this.isCurrentStateHasChild())
            this.showWin("agent");
        else {
            try {
                System.out.print("Pilih State : ");
                choice = Integer.parseInt(userInput.readLine());
                choosen_state = this.getCurrentState().getChildList().get(choice - 1);

                this.setCurrentState(choosen_state);
                System.out.println("Choosen state" + choosen_state);
                this.agentMove();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void agentMove() {
        State choosen_state = null;
        System.out.println("\n\n---------- Agent Move ----------");
        this.showCurrentState();

        if (!this.isCurrentStateHasChild())
            this.showWin("human");
        else {
            try {
                choosen_state = this.getCurrentState().getChildList().get(0);
                if (this.getCurrentState().getMinimax() > 0) {
                    for (State childState : this.getCurrentState().getChildList()) {
                        if (childState.getValue() == 1)
                            choosen_state = childState;
                    }
                } else {
                    for (State childState : this.getCurrentState().getChildList()) {
                        if (childState.getValue() == 0)
                            choosen_state = childState;
                    }
                }

                this.setCurrentState(choosen_state);
                System.out.println("Choosen state" + choosen_state);
                this.humanMove();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void showCurrentState() {
        int i = 1;

        System.out.println("------------ State -------------");
        for (State state : this.getCurrentState().getChildList()) {
            System.out.println( (i++) + ". " + state);
        }
        System.out.println("--------------------------------");
    }

    public boolean isCurrentStateHasChild() {
        if (this.currentState.getChildList().size() > 0)
            return true;
        return false;
    }

    public void showWin(String playerWin) {
        if (playerWin.equals("human"))
            System.out.println("\n----------- Congrats. You win ! -----------");
        else
            System.out.println("\n----------- Sorry. You lose ! -----------");
    }
}
