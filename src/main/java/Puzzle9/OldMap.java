package Puzzle9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class OldMap {


    private SpecialInt[][] map;
    ArrayList<Integer> lowPoints = new ArrayList<>();

    ArrayList<Coordinate> lowPointCoordinates = new ArrayList<>();

    HashMap<Coordinate, Integer> basisSize = new HashMap<>();

    private int height;
    private int width;

    public OldMap(int width, int height) {

        this.map = new SpecialInt[height + 2][width + 2];

        this.width = width;
        this.height = height;

        for (SpecialInt[] i : map) {
            Arrays.fill(i, new SpecialInt(0));
        }

        for (SpecialInt[] i : map) {
            System.out.println(Arrays.toString(i));
        }

        System.out.println("--------------------");

        fillBorders();

        //fill the border rows and cols with 9

    }

    private void fillBorders() {
        Arrays.fill(map[0], new SpecialInt(9));
        Arrays.fill(map[height + 1], new SpecialInt(9));

        for (int row = 0; row < height + 1; row++) {
            map[row][0] = new SpecialInt(9);
        }

        for (int row = 0; row < height + 1; row++) {
            map[row][width + 1] = new SpecialInt(9);
        }

        for (SpecialInt[] i : map) {
            System.out.println(Arrays.toString(i));
        }
    }

    public void fillMap(File file) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);

        for (int row = 1; row < height + 1; row++) {

            String line = fileScanner.nextLine();
            Scanner lineScanner = new Scanner(line).useDelimiter("");

            for (int col = 1; col < width + 1; col++) {
                int temp = lineScanner.nextInt();
                map[row][col] = new SpecialInt(temp);
            }
        }

        System.out.println("Filled map");
        for (SpecialInt[] i : map) {
            System.out.println(Arrays.toString(i));
        }
        System.out.println("END");
    }

   /* public void findLowPoints() {

        for (int row = 1; row < height + 1; row++) {
            for (int col = 1; col < width + 1; col++) {
                int currentInt = map[row][col];
                if (currentInt != 9 && map[row - 1][col] > currentInt) {
                    if (map[row][col + 1] > currentInt && map[row][col - 1] > currentInt) {
                        if (map[row + 1][col] > currentInt) {
                            lowPoints.add(currentInt);
                        }
                    }
                }
            }
        }
        System.out.println(Arrays.toString(lowPoints.toArray()));
    }*/

    public void findLowPoints() {

        for (int row = 1; row < height + 1; row++) {
            for (int col = 1; col < width + 1; col++) {
                int currentInt = map[row][col].getValue();
                if (checkAbove(row, col, currentInt) && checkBelow(row, col, currentInt) && checkLeft(row, col, currentInt) && checkRight(row, col, currentInt)) {
                    lowPoints.add(currentInt);
                    lowPointCoordinates.add(new Coordinate(row, col));
                }
            }
        }
        System.out.println(Arrays.toString(lowPoints.toArray()));
    }

    public void findBasins() {
        int counter;
        Coordinate temp;
        int coordRow;
        int coordCol;

        boolean blockStepUp;
        boolean blockStepDown;
        boolean blockStepLeft;
        boolean blockStepRight;

        for (int i = 0; i < lowPointCoordinates.size(); i++) {

            temp = lowPointCoordinates.get(i);
            coordRow = temp.getRow();
            coordCol = temp.getCol();
            counter = 0;

            blockStepUp = false;
            blockStepDown = false;
            blockStepLeft = false;
            blockStepRight = false;

            while (stepUp(coordRow,coordRow) || stepLeft(coordRow,coordCol) || stepDown(coordRow,coordCol) || stepRight(coordRow,coordCol)){
                if (stepUp(coordRow,coordCol)){
                    if(map[coordRow][coordCol].getSteppedOver() == 0){
                        counter++;
                    }
                    map[coordRow][coordCol].incrementSteppedOver();
                    coordRow--;
                }else if(stepLeft(coordRow,coordCol)){
                    if(map[coordRow][coordCol].getSteppedOver() == 0){
                        counter++;
                    }
                    map[coordRow][coordCol].incrementSteppedOver();
                    coordCol--;
                }else if(stepDown(coordRow,coordCol)){
                    if(map[coordRow][coordCol].getSteppedOver() == 0){
                        counter++;
                    }
                    map[coordRow][coordCol].incrementSteppedOver();
                    coordRow++;
                }else if(stepRight(coordRow,coordCol)){
                    coordCol++;
                    if(map[coordRow][coordCol].getSteppedOver() == 0){
                        counter++;
                    }
                    map[coordRow][coordCol].incrementSteppedOver();
                    coordRow++;
                }
            }
            System.out.println(counter);
        }
    }

    private boolean checkAbove(int currentRow, int currentCol, int currentInt) {
        return currentInt < map[currentRow - 1][currentCol].getValue();
    }

    private boolean checkBelow(int currentRow, int currentCol, int currentInt) {
        return currentInt < map[currentRow + 1][currentCol].getValue();
    }

    private boolean checkLeft(int currentRow, int currentCol, int currentInt) {
        return currentInt < map[currentRow][currentCol - 1].getValue();
    }

    private boolean checkRight(int currentRow, int currentCol, int currentInt) {
        return currentInt < map[currentRow][currentCol + 1].getValue();
    }


    /**
     * can only step up, left or right if the new position is not a nine
     *
     * @return if it can be moved
     */
   /* private boolean stepUp(int currentRow, int currentCol) {
        if(map[currentRow - 1][currentCol] != 9){
            map[currentRow - 1][currentCol] = 9;
            return true;
        }
        return false;
    }

    private boolean stepDown(int currentRow, int currentCol) {
        if(map[currentRow + 1][currentCol] != 9){
            map[currentRow + 1][currentCol] = 9;
            return true;
        }
        return false;
    }

    private boolean stepLeft(int currentRow, int currentCol) {
        if(map[currentRow][currentCol - 1] != 9){
            map[currentRow][currentCol - 1] = 9;
            return true;
        }
        return false;
    }

    private boolean stepRight(int currentRow, int currentCol) {
        if(map[currentRow][currentCol + 1] != 9){
            map[currentRow][currentCol + 1] = 9;
            return true;
        }
        return false;
    }*/


    /**
     * can only step up, left or right if the new position is not a nine
     *
     * @return if it can be moved
     */
    private boolean stepUp(int currentRow, int currentCol) {
        return map[currentRow - 1][currentCol].getValue() != 9 && !map[currentRow - 1][currentCol].isBlocked();
    }

    private boolean stepDown(int currentRow, int currentCol) {
        return map[currentRow + 1][currentCol].getValue() != 9 && !map[currentRow - 1][currentCol].isBlocked();
    }

    private boolean stepLeft(int currentRow, int currentCol) {
        return map[currentRow][currentCol - 1].getValue() != 9 && !map[currentRow - 1][currentCol].isBlocked();
    }

    private boolean stepRight(int currentRow, int currentCol) {
        return map[currentRow][currentCol + 1].getValue() != 9 && !map[currentRow - 1][currentCol].isBlocked();
    }

    /**
     * counts how many neighbours a coordinate has which have not the value 9
     * @param coordinate
     * @return
     */
    private int checkNeighbours(Coordinate coordinate){
        int row = coordinate.getRow();
        int col = coordinate.getCol();

        int counter = 0;

        if (stepUp(row,col)){
            counter++;
        }else if (stepRight(row,col)){
            counter++;
        }else if(stepLeft(row,col)){
            counter++;
        }else if (stepDown(row,col)){
            counter++;
        }

        return counter;
    }


    public int computeRiskLevel() {

        int sum = 0;

        for (int point : lowPoints) {
            sum += (point + 1);
        }
        return sum;
    }


    /**
     * //wenn col null darf man nicht col-1 prüfen
     *                 if(col == 0){
     *                     if(map[row][col+1] > map[row][col]){
     *                         if(map[row +1][col] > map[row][col] ){
     *                             lowPoints.add(map[row][col]);
     *                         }
     *                     }
     *                 }
     */
}
