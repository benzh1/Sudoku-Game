import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Grid {

    private GridNode[][] grid = new GridNode[9][9];
    private GridNode[][] playGrid;
    private ArrayList<Integer> numbersToPlay = new ArrayList<>();
    private int difficulty;
    private int blankCells;

    public Grid() {
        //grid = fillGridA();
    }

    public Grid(int i) {
        fillGrid(0, 0);
        playGrid = copy(grid);
        if (i == 0) {
            // easy
            generatePlayGrid(15);
            difficulty = 0;
            blankCells = 15;
        } else if (i == 1) {
            // medium
            generatePlayGrid(30);
            difficulty = 1;
            blankCells = 30;
        } else if (i == 2) {
            // hard
            generatePlayGrid(45);
            difficulty = 2;
            blankCells = 45;
        } else {
            grid = new GridNode[9][9];
        }
        play();
    }

    public void generatePlayGrid(int difficulty) {
        int count = 0;
        int x, y;
        while (count < difficulty) {
            do {
                x = ThreadLocalRandom.current().nextInt(0, 9);
                y = ThreadLocalRandom.current().nextInt(0, 9);
            } while (playGrid[y][x].getNumber() == -1);
            numbersToPlay.add(grid[y][x].getNumber());
            playGrid[y][x].setNumber(-1);
            count++;
        }
    }

    public void fillGrid(int y, int x) {
        Rules checker = new Rules(grid);
        ArrayList<Integer> numbers;
        for (int i = y; i < grid.length; i++) {
            for (int j = x; j < grid[i].length; j++) {
                //System.out.println("Current coords: i: " + i + " j: " + j);
                numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
                Collections.shuffle(numbers);
                GridNode node = grid[i][j];
                boolean flag = false;
                for (int k = 0; k < 9; k++) {
                    //System.out.println("FINDING NUMBERS");
                    Integer number = numbers.getFirst();
                    numbers.removeFirst();
                    if (node != null) {
                        //System.out.println("NOT NULL NODE");
                        //System.out.println("SETTING NEW NUMBER: " + number);
                        if(!node.setNumber(number)) {
                            //System.out.println("CONTINUING");
                            if (k == 8) {
                                flag = true;
                            }
                            continue;
                        }
                        //System.out.println("NEW NUMBER: " + node.getNumber());
                    } else {
                        node = new GridNode(number);
                    }
                    grid[i][j] = node;
                    checker.setGrid(grid.clone());
                    //printGrid();
                    if (checker.checkSingle(i, j)) {
                        //System.out.println("ROW CHECK WORK");
                        //printGrid();
                        break;
                    } else if (numbers.isEmpty()) {
                        backTrack(i, j);
                        return;
                    }
                }
                if (numbers.isEmpty() && flag) {
                    backTrack(i, j);
                    return;
                }
            }
            if (x != 0) {
                x = 0;
            }
        }
    }

    public void backTrack(int i, int j) {
        grid[i][j] = null;
        if (j == 0) {
            fillGrid(i - 1, 8);
        } else {
            fillGrid(i, j - 1);
        }
    }

    public void play() {
        boolean blankFlag, wrongVal;
        int xCoord, yCoord, val;
        while (blankCells > 0) {
            printPlayGrid();
            System.out.println();
            System.out.print("Numbers remaining: ");
            for (int i = 1; i < 10; i++) {
                if (numbersToPlay.contains(i)) {
                    System.out.print(" " + i + " ");
                }
            }
            System.out.println();
            do {
                blankFlag = false;
                wrongVal = false;
                int[] input = getInput();
                xCoord = input[0] - 1;
                yCoord = input[1] - 1;
                val = input[2];
                if (getPlayGrid()[yCoord][xCoord].getNumber() != -1) {
                    blankFlag = true;
                } else if (getGrid()[yCoord][xCoord].getNumber() != val) {
                    wrongVal = true;
                }
            } while (blankFlag || wrongVal);
            numbersToPlay.remove((Integer) val);
            getPlayGrid()[yCoord][xCoord].playNumber(val);
            blankCells--;
        }
    }

    private int[] getInput() {
        Scanner input = new Scanner(System.in);
        int[] coords = new int[3];
        int xCoord;
        int yCoord;
        int val;
        do {
            System.out.print("Enter x-coordinate: ");
            xCoord = input.nextInt();
        } while (xCoord < 1 || xCoord > 9);
        coords[0] = xCoord;
        do {
            System.out.print("Enter y-coordinate: ");
            yCoord = input.nextInt();
        } while (yCoord < 1 || yCoord > 9);
        coords[1] = yCoord;
        do {
            System.out.print("Enter number: ");
            val = input.nextInt();
        } while (val < 1 || val > 9);
        coords[2] = val;
        return coords;
    }

    public GridNode[][] getGrid() {
        return grid;
    }

    public GridNode[][] getPlayGrid() {
        return playGrid;
    }

    public int getBlankCells() {
        return blankCells;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void printGrid() {
        System.out.print("   |");
        for (int i = 1; i < 10; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
        System.out.print("----");
        for (int i = 0; i < 9; i++) {
            System.out.print("---");
        }
        System.out.println();
        int k = 1;
        for (int i = 0; i < 9; i++) {
            System.out.print(" " + k + " |");
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == null) {
                    System.out.print(" _ ");
                } else {
                    System.out.print(" " + grid[i][j].getNumber() + " ");
                }
            }
            System.out.println();
            k++;
        }
    }

    public void printPlayGrid() {
        System.out.print("   |");
        for (int i = 1; i < 10; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.println();
        System.out.print("----");
        for (int i = 0; i < 9; i++) {
            System.out.print("---");
        }
        System.out.println();
        int k = 1;
        for (int i = 0; i < 9; i++) {
            System.out.print(" " + k + " |");
            for (int j = 0;j < 9; j++) {
                if (playGrid[i][j].getNumber() == -1) {
                    System.out.print(" _ ");
                } else {
                    System.out.print(" " + playGrid[i][j].getNumber() + " ");
                }
            }
            System.out.println();
            k++;
        }
    }

    public GridNode[][] copy(GridNode[][] copyGrid) {
        GridNode[][] tempGrid = new GridNode[copyGrid.length][copyGrid[0].length];
        for (int i = 0; i < copyGrid.length; i++) {
            for (int j = 0; j < copyGrid[i].length; j++) {
                tempGrid[i][j] = new GridNode(copyGrid[i][j].getNumber(), copyGrid[i][j].getNumbersTried());
            }
        }
        return tempGrid;
    }
}
