package Puzzle5;

import java.util.Arrays;

public class Board {

    //top left is 0,0 and right bottom 9,9
    private final int[][] boardArray;

    /**
     * @param size the size of the board to make it scale able
     */
    public Board(int size){
        this.boardArray = new int[size][size];

        //fill the whole array with zeros
        for (int [] array : boardArray){
            Arrays.fill(array,0);
        }
    }

    /**
     * decides if a line is a horizontal or vertical line and calls the corresponding methode
     * @param line the line which should be marked on the board
     */
    public void markLine(Line line){
      if(line instanceof HorizontalLine){
          markHorizontalLine((HorizontalLine) line);
      }else if(line instanceof  VerticalLine){
          markVerticalLine((VerticalLine) line);
      }else if(line instanceof  DiagonalLine){
          markDiagonalLine((DiagonalLine) line);
      }
    }

    /**
     * marks horizontalLines on the board
     * start at the beginning coordinate and than mark so many digits of the board until the line length is reached
     * @param line object of type line (here horizontal line)
     */
    private void markHorizontalLine(HorizontalLine line){
        Coordinate beginningCoordinate = line.getBeginningCoordinate();
        int yLevel = line.getYLevel();
        int xBeginning = beginningCoordinate.getX();
        int lineLength = line.getLineLength();

        for (int x = xBeginning; x <= xBeginning + lineLength; x++) {
            boardArray[yLevel][x]++;
        }
    }

    /**
     * marks vertical lines on the board
     * start at the beginning coordinate and than mark so many digits of the board until the line length is reached
     * @param line object of type line (here vertical line)
     */
    private void markVerticalLine(VerticalLine line){
        Coordinate beginningCoordinate = line.getBeginningCoordinate();
        int xLevel = line.getXLevel();
        int yBeginning = beginningCoordinate.getY();
        int lineLength = line.getLineLength();

        for (int y = yBeginning; y <= yBeginning + lineLength; y++) {
            boardArray[y][xLevel]++;
        }
    }

    /**
     * marks diagonal lines on the board
     * @param line
     */
    private void markDiagonalLine(DiagonalLine line){

        Coordinate beginningCoordinate = line.getBeginningCoordinate();
        Coordinate endCoordinate = line.getEndCoordinate();
        int gradient = line.getGradient();

        int yBeginning = beginningCoordinate.getY();
        int xBeginning = beginningCoordinate.getX();
        int yEnd = endCoordinate.getY();
        int col = xBeginning;

        //if steigung is positive
        if(gradient == 1){
            //eins nach oben
            for (int row = yBeginning; row <= yEnd; row++) {
                boardArray[row][col]++;
                col++;
            }
        }else if(gradient == -1){
            for (int row = yBeginning; row <= yEnd; row++) {
                boardArray[row][col]++;
                col--;
            }
        }
    }

    /**
     * counts the numbers which are greater than 2
     * @return the result
     */
    public int computeResult(){

        for(int [] row : boardArray){
            System.out.println(Arrays.toString(row));
        }

        int counter = 0;
        for(int [] row : boardArray){
            for (int col : row){
                if(col >= 2){
                    counter++;
                }
            }
        }
        return counter;
    }
}
