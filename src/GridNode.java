import java.util.ArrayList;
import java.util.Collections;

public class GridNode {
    private ArrayList<Integer> numbersTried = new ArrayList<>();
    private Integer number;

    public GridNode(Integer number) {
        this.number = number;
        numbersTried.add(number);
    }

    public GridNode(Integer number, ArrayList<Integer> numbersTried) {
        this.number = number;
        this.numbersTried = numbersTried;
    }

    public ArrayList<Integer> getNumbersTried() {
        return numbersTried;
    }

    public Integer getNumber() {
        return number;
    }

    public boolean setNumber(Integer number) {
        if (numbersTried.contains(number)) {
            return false;
        } else {
            this.number = number;
            numbersTried.add(number);
            return true;
        }
    }

    public void playNumber(Integer number) {
        this.number = number;
    }
}
