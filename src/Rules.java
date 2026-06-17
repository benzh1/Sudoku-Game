import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Rules {

    private int[][] grid;

    public Rules (int[][] grid) {
        this.grid = grid;
    }

    public Rules(GridNode[][] grid) {
        int[][] tempGrid = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == null) {
                    tempGrid[i][j] = -1;
                } else {
                    tempGrid[i][j] = grid[i][j].getNumber();
                }
            }
        }
        this.grid = tempGrid;
    }

    public boolean checkAll() {
        return checkRows() && checkColumns() && checkBoxes();
    }

    public boolean checkSingle(int i, int j) {
        //System.out.println("Column: " + checkColumn(i, j));
        //System.out.println("Row: " + checkRow(i, j));
        //System.out.println("Box: " + checkBox(i, j));
        return checkRow(i, j) && checkColumn(i, j) && checkBox(i, j);
    }

    public boolean checkColumns() {
        ArrayList<Integer> numbers;
        for (int i = 0; i < 9; i++) {
            boolean flag = true;
            numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            for (int j = 0; j < 9; j++) {
                if (grid[j][i] != -1) {
                    if (numbers.contains(grid[j][i])) {
                        numbers.remove((Integer) grid[i][j]);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkColumn(int i, int j) {
        //System.out.println("COLUMN CHECK");
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int k = 0; k < 9; k++) {
            if (grid[k][j] != -1) {
                //System.out.println("Cell: " + grid[k][j]);
                //System.out.println("List: " + numbers);
                if (numbers.contains(grid[k][j])) {
                    numbers.remove((Integer) grid[k][j]);
                } else {
                    //System.out.println("FALSE COLUMN");
                    return false;
                }
            }
        }
        //System.out.println("END COLUMN CHECK");
        return true;
    }

    public boolean checkRows() {
        ArrayList<Integer> numbers;
        for (int i = 0; i < 9; i++) {
            numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] != -1) {
                    if (numbers.contains(grid[i][j])) {
                        numbers.remove((Integer) grid[i][j]);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean checkRow(int i, int j) {
        //System.out.println("ROW CHECK");
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        for (int k = 0; k < 9; k++) {
            if (grid[i][k] != -1) {
                //System.out.println("Cell: " + grid[i][k]);
                //System.out.println("List: " + numbers);
                if (numbers.contains(grid[i][k])) {
                    numbers.remove((Integer) grid[i][k]);
                } else {
                   // System.out.println("FALSE ROW");
                    return false;
                }
            }
        }
        //printGrid();
        //System.out.println("END ROW CHECK");
        return true;
    }

    public boolean checkBoxes() {
        ArrayList<Integer> numbers;
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
                for (int j = i * 3; j < (i * 3) + 3; j++) {
                    for (int l = k * 3; l < (k * 3) + 3; l++) {
                        if (grid[j][l] != -1) {
                            if (numbers.contains(grid[i][j])) {
                                numbers.remove((Integer) grid[i][j]);
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean checkBox(int i, int j) {
        //System.out.println("BOX CHECK");
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        int columnBox = (i / 3) * 3;
        int rowBox = (j / 3) * 3;
        for (int k = columnBox; k < columnBox + 3; k++) {
            for (int l = rowBox; l < rowBox + 3; l++) {
                if (grid[k][l] != -1) {
                    //System.out.println("Cell: " + grid[k][l]);
                    //System.out.println("List: " + numbers);
                    if (numbers.contains(grid[k][l])) {
                        numbers.remove((Integer) grid[k][l]);
                    } else {
                        //System.out.println("FALSE BOX");
                        return false;
                    }
                }
            }
        }
        //System.out.println("END BOX CHECK");
        return true;
    }

    public int[][] getGrid() {
        return grid;
    }

    public void setGrid(int[][] grid) {
        this.grid = grid;
    }

    public void setGrid(GridNode[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == null) {
                    this.grid[i][j] = -1;
                } else {
                    this.grid[i][j] = grid[i][j].getNumber();
                }
            }
        }
    }

    public void printGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == -1) {
                    System.out.print(" _ ");
                } else {
                    System.out.print(" " + grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
