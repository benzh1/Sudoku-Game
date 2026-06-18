Sudoku game where the play grid is output to the console and you enter numbers using coordinates corresponding to the grid when prompted

The numbers at the top and sides of the grid separated by the boundary dashes indicate the coordinates for each cell in the grid
An underscore in the grid indicates a cell that needs to be filled
Below the grid the line of text output stating "numbers remaining" tells you which numbers have not all been filled in on the sudoku grid
Below the "numbers remaining" text will be a first prompt to enter the x-coordinate, column, of the cell you want to fill
Once that is entered correct there will be a second prompt to enter the y-coordiante, row, of the cell you want to fill
After the coordinates have been entered correctly you will be prompted to enter the number you want to fill that cell with
If that number fits the puzzle correctly it will be entered into the grid, otherwise the grid will not change and you will have to try that turn again

To select different difficulties at the moment you need to change the main.java file itself with the Main class and main method
To do this you need to create a new instance of the Grid class using the constructor with an integer argument
The valid arguments for the constructor are the integers 0, 1 and 2 - each number corresponds to an increasing difficulty level
