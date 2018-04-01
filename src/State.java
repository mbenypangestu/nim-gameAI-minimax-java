import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;

public class State {
    private List<Integer>   state;
    private List<State>     childList;
    private int             minimax;
    private int             value;
    private boolean         has_child = false;
    private State           parent    = null;

    public State(Integer... state) {
        this.state      = new ArrayList<>(Arrays.asList(state));
        this.childList  = new ArrayList<>();
    }

    public State(State parent, List<Integer> remaining_item, Integer... state) {
        parent.childList.add(this);
        this.setParent(parent);

        this.state      = new ArrayList<>(Arrays.asList(state));
        this.state.addAll(remaining_item);

        this.childList  = new ArrayList<>();
    }

    public List<Integer> getState() {
        return state;
    }

    public List<Integer> getRemainingItemState(Integer item_remove) {
        List<Integer> itemRemaining = new ArrayList<>(this.state);

        itemRemaining.remove(item_remove);
        return itemRemaining;
    }

    public State getParent() {
        return parent;
    }

    public void setParent(State parent) {
        this.parent = parent;
    }

    public List<State> getChildList() {
        return childList;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getMinimax() {
        return minimax;
    }

    public void setMinimax(int minimax) {
        this.minimax = minimax;
    }

    public boolean isStateHasChild() {
        if (this.state.size() != 0) {
            for (int item_state : this.state) {
                if (item_state / 2 > 1 || (item_state % 2 > 0 && item_state != 1)) {
                    this.has_child = true;
                    break;
                }
            }
        }

        return this.has_child;
    }

    public int getMaxChildFactor(int number) {
        if (number % 2 == 1) return number / 2;
        else return number / 2 - 1;
    }

    @Override
    public String toString() {
        return state.toString();
    }
}
