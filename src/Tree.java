import java.util.ConcurrentModificationException;

public class Tree {
    public static int layer = 0;
    private State startState;

    public Tree(State stateStart) {
        this.startState = stateStart;
    }

    public void build() {
        if (this.startState != null) this.minimize(this.startState);
    }

    public void maximize(State state) {
        state.setMinimax(1);
        this.setState(state);
    }

    public void minimize(State state) {
        state.setMinimax(-1);
        this.setState(state);
    }

    public void setState(State state) {
        if (state.isStateHasChild()) this.createChildState(state);
        else {
//            System.out.println("Minimax : " + state.getMinimax());
            this.setStaticEvaluationValue(state);
//            System.out.println("| End");
        }
    }

    public void setStaticEvaluationValue(State state) {
        State parent = null;

        if (state.getParent() != null)
            parent = state.getParent();

        if (!state.isStateHasChild()) {
            if (state.getMinimax() > 0) state.setValue(0);
            else state.setValue(1);
        } else {
            if (state.getChildList().size() == 1)
                state.setValue(state.getChildList().get(0).getValue());
            else {
                for (State childState : state.getChildList()) {
                    if (state.getMinimax() < 0) {
                        if (childState.getValue() == 0) state.setValue(0);
                    } else if (state.getMinimax() > 0) {
                        if (childState.getValue() == 1) state.setValue(1);
                    }
                }
            }
        }
//        System.out.println(state.getValue());

        if (parent != null)
            this.setStaticEvaluationValue(parent);
    }

    public void createChildState(State parent) {
        int maxchild_factor;

        try {
            if (parent.getState().size() > 0) {
//                System.out.println(" | Parent " + parent + " : Minimax : " + parent.getMinimax());

                for (int item_state : parent.getState()) {
                    maxchild_factor = parent.getMaxChildFactor(item_state);

                    for (int i = 1; i <= maxchild_factor; i++) {
                        State child_state =
                                new State(parent, parent.getRemainingItemState(item_state), item_state - i, i);

//                        System.out.println(" ---> " + child_state + " ");

                        if (parent.getMinimax() > 0) this.minimize(child_state);
                        else this.maximize(child_state);
                    }
                }
            }
        } catch (ConcurrentModificationException e) {
        }
    }
}
