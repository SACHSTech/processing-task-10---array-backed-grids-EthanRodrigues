import processing.core.PApplet;

 /**
 * This program creates an interactive grid with data from a two-dimensional array.
 * @author: Ethan Rodrigues
 */
public class Sketch extends PApplet {
  // declare width, height and margins of the cells in the grid
  int CELL_WIDTH = 20;
  int CELL_HEIGHT = 20;
  int MARGIN = 5;

  // set the row and column count
  int ROW_COUNT = 10;
  int COLUMN_COUNT = 10;

  // set the screen width and height limitations for the grid
  int SCREEN_WIDTH = (CELL_WIDTH + MARGIN) * COLUMN_COUNT + MARGIN;
  int SCREEN_HEIGHT = (CELL_HEIGHT + MARGIN) * ROW_COUNT + MARGIN;

  // set the two dimensional array for the row and columns
  int[][] intGrid = new int[ROW_COUNT][COLUMN_COUNT];

  int intSelectedCell;

  public void settings() {
    // screen size
    size(SCREEN_WIDTH, SCREEN_HEIGHT);
  }

  public void setup() {
    // set background colour
    background(0, 0, 0);

  }

  public void draw() {

    // display the grid over the width and height of the screen
    for (int intColumn = 0; intColumn < COLUMN_COUNT; intColumn++) {
      for (int intRow = 0; intRow < ROW_COUNT; intRow++) {

        // change colour based on the status of the cell
        if (intGrid[intRow][intColumn] == 1) {

          fill(0, 255, 0);
          rect(MARGIN + (intColumn * (CELL_WIDTH + MARGIN)), MARGIN + (intRow * (CELL_HEIGHT + MARGIN)), CELL_WIDTH, CELL_HEIGHT);

        }

        else {

          fill(255, 255, 255);
          rect(MARGIN + (intColumn * (CELL_WIDTH + MARGIN)), MARGIN + (intRow * (CELL_HEIGHT + MARGIN)), CELL_WIDTH, CELL_HEIGHT);
        }
      }
    }
  }

  /**
   * When the mouse is pressed, the cell changes colour
   */
  public void mousePressed() {

    // changes the colour of the clicked cell and the sourrounding cells
    for (int intColumn = 0; intColumn < COLUMN_COUNT; intColumn++) {
      for (int intRow = 0; intRow < ROW_COUNT; intRow++) {
        
        // finds the cell location of the cell pressed based on the mouseX and moyseY values
        if (mouseX / (CELL_WIDTH + MARGIN) == intColumn && mouseY / (CELL_HEIGHT + MARGIN) == intRow) {

          // change colour of cell above
          if (intRow > 0 && intGrid[intRow-1][intColumn] == 0) {

            intGrid[intRow-1][intColumn] = 1;
            intSelectedCell++;
          }
          
          else if (intRow > 0 && intGrid[intRow-1][intColumn] == 1) {

            intGrid[intRow - 1][intColumn] = 0;
            intSelectedCell--;
          }

          // change colour of cell below
          if (intRow < ROW_COUNT - 1 && intGrid[intRow + 1][intColumn] == 0) {

            intGrid[intRow + 1][intColumn] = 1;
            intSelectedCell++;
          }

          else if (intRow < ROW_COUNT - 1 && intGrid[intRow+1][intColumn] == 1) {

            intGrid[intRow + 1][intColumn] = 0;
            intSelectedCell--;
          
          }

          // change colour of cell to the left
          if (intColumn > 0 && intGrid[intRow][intColumn - 1] == 0) {

            intGrid[intRow][intColumn - 1] = 1;
            intSelectedCell++;
          }

          else if (intColumn > 0 && intGrid[intRow][intColumn - 1] == 1) {
          
            intGrid[intRow][intColumn - 1] = 0;
            intSelectedCell--;
          }

          // change colour of cell to the left
          if (intColumn < COLUMN_COUNT - 1 && intGrid[intRow][intColumn+1] == 0) {

            intGrid[intRow][intColumn + 1] = 1;
            intSelectedCell++;
          }

          else if (intColumn < COLUMN_COUNT - 1 && intGrid[intRow][intColumn+1] == 1) {

            intGrid[intRow][intColumn + 1] = 0;
            intSelectedCell--;
          }
          
          // change colour of cell pressed
          if (intGrid[intRow][intColumn] == 0) {

            intGrid[intRow][intColumn] = 1;
            intSelectedCell++;
          }

          else if (intGrid[intRow][intColumn] == 1) {

            intGrid[intRow][intColumn] = 0;
            intSelectedCell--;
          }

          // print number of cells selected to the screen
          println( "Total of " + intSelectedCell + " cells are selected");
          
        }
      }
    }

    // prints out number of cells selected in each row
    for (int intRow = 0; intRow < ROW_COUNT; intRow++) {
      
      // counts green cells in row
      int rowCounter = 0;

      // counts the number of continuous cells in a row
      int intCurrentContinuous = 0;

      // largest count of continuous cells in a row
      int intMaximum = 0;

      // loops through each column in a row
      for (int column = 0; column < COLUMN_COUNT; column++) {
        
        // updates row and continuous counter
        if (intGrid[intRow][column] == 1) {
          rowCounter++;
          intCurrentContinuous++;

          // replace maxiumum with new highest current continuous
          if (intCurrentContinuous > intMaximum) {
            intMaximum = intCurrentContinuous;
          }
        }

        // reset continuous counter if green cell is not detected consecutively 
        else {
          intCurrentContinuous = 0;
        }
      }

      // prints out number of continuous blocks if there are more than 2 in a row
      if (intMaximum > 2) {
        System.out.println("There are " + intMaximum + " continuous blocks selected on row " + intRow + ".");
      }

      System.out.println("Row " + intRow + " has " + rowCounter + " cells selected");
    }

    // prints out number of cells selected in each column
    for (int intColumn = 0; intColumn < ROW_COUNT; intColumn++) {
      
      // counts number of green cells in a column
      int intColumnCount= 0;
      for (int intRow = 0; intRow < COLUMN_COUNT; intRow++) {
        if (intGrid[intRow][intColumn] == 1) {
          intColumnCount++;
        }
      }
      System.out.println("Column " + intColumn + " has " + intColumnCount + " cells selected");
    }
  }
}