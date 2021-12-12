package Puzzle9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Map {

    //2d array for the map
    private final SpecialInt[][] map;
    //holds the int value of all low points
    private final ArrayList<Integer> lowPoints = new ArrayList<>();
    //holds the low points as coordinates
    private final ArrayList<Coordinate> lowPointCoordinates = new ArrayList<>();
    //holds all basins with its sizes
    private final ArrayList<Integer> basinSizes = new ArrayList<>();

    //height and width of the map
    private final int height;
    private final int width;

    public Map(int width, int height) {

        this.map = new SpecialInt[height + 2][width + 2];

        this.width = width;
        this.height = height;

        for (SpecialInt[] i : map) {
            Arrays.fill(i, new SpecialInt(0));
        }

        fillBorders();
    }

    /**
     * fills the borders of the map with 9s (makes it easier later on)
     * dont have to take care of index out of bounds
     */
    private void fillBorders() {
        Arrays.fill(map[0], new SpecialInt(9));
        Arrays.fill(map[height + 1], new SpecialInt(9));

        for (int row = 0; row < height + 1; row++) {
            map[row][0] = new SpecialInt(9);
        }

        for (int row = 0; row < height + 1; row++) {
            map[row][width + 1] = new SpecialInt(9);
        }
    }

    /**
     * fills the map with the content of the file
     * @param file -- the file which is used for the puzzleinput
     * @throws FileNotFoundException if the file is not found
     */
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
    }

    /**
     * finds the lowpoints of the map and saves them as coordinates in a list
     */
    public void findLowPoints() {

        for (int row = 1; row < height + 1; row++) {
            for (int col = 1; col < width + 1; col++) {
                int currentInt = map[row][col].getValue();
                if (checkAbove(row, col, currentInt) && checkBelow(row, col, currentInt) && checkLeft(row, col, currentInt) && checkRight(row, col, currentInt)) {
                    lowPoints.add(currentInt);
                    Coordinate newCoord = new Coordinate(row,col);
                    lowPointCoordinates.add(newCoord);
                }
            }
        }
        System.out.println(Arrays.toString(lowPoints.toArray()));
    }


    /**
     * checks if a neighbour is now a low point
     * @param coordinate -- the coordinate which neighbour should be checked
     * @return -- true if a neighbour is a low point
     */
    private boolean checkNeighbours(Coordinate coordinate){
        int row = coordinate.getRow();
        int col = coordinate.getCol();
        int currentInt = map[row][col].getValue();
        if (checkAbove(row, col, currentInt) && checkBelow(row, col, currentInt) && checkLeft(row, col, currentInt) && checkRight(row, col, currentInt)) {
            return true;
        }
        return false;
    }


    // methods for checking if the point above, left, right or below is a low point
    private boolean checkAbove(int currentRow, int currentCol, int currentInt) {
        return currentInt != 9 && currentInt <= map[currentRow - 1][currentCol].getValue();
    }

    private boolean checkBelow(int currentRow, int currentCol, int currentInt) {
        return currentInt != 9 && currentInt <= map[currentRow + 1][currentCol].getValue();
    }

    private boolean checkLeft(int currentRow, int currentCol, int currentInt) {
        return currentInt != 9 && currentInt <= map[currentRow][currentCol - 1].getValue();
    }

    private boolean checkRight(int currentRow, int currentCol, int currentInt) {
        return currentInt != 9 && currentInt <= map[currentRow][currentCol + 1].getValue();
    }

    //help methods for moving up, down. left, right
    private Coordinate stepUp(int currentRow, int currentCol) {
        return new Coordinate(currentRow-1,currentCol);
    }

    private Coordinate stepDown(int currentRow, int currentCol) {
        return new Coordinate(currentRow+1,currentCol);
    }

    private Coordinate stepLeft(int currentRow, int currentCol) {
        return new Coordinate(currentRow,currentCol-1);
    }

    private Coordinate stepRight(int currentRow, int currentCol) {
        return new Coordinate(currentRow,currentCol+1);
    }

    /**
     * find all points which belong to a basin and save them in to a list
     */
    private void findBasins() {

        ArrayList<Coordinate> basisPoints;

        for (Coordinate c : lowPointCoordinates){

            basisPoints = new ArrayList<>();
            basisPoints.add(c);

            System.out.println("Low Point Coordinate, row " + c.getRow() + ", col " + c.getCol());

            for (int i = 0; i < basisPoints.size(); i++) {

                int row = basisPoints.get(i).getRow();
                int col = basisPoints.get(i).getCol();

                if(map[row][col].getValue() != 9){
                    //set the current lowPoint 9, so the neighbours get lowPoints
                    map[row][col] = new SpecialInt(9);

                    if(checkNeighbours(stepUp(row,col))){
                        Coordinate temp1 = stepUp(row,col);
                        if(!basisPoints.contains(temp1)){
                            basisPoints.add(temp1);
                        }
                        System.out.println("Added " + map[stepUp(row,col).getRow()][stepUp(row,col).getCol()].getValue() + " --- on row " + stepUp(row,col).getRow() + ", col " + stepUp(row,col).getCol());
                    }
                    if(checkNeighbours(stepDown(row,col))){
                        Coordinate temp1 = stepDown(row,col);
                        if(!basisPoints.contains(temp1)){
                            basisPoints.add(temp1);
                        }
                        System.out.println("Added " + map[stepDown(row,col).getRow()][stepDown(row,col).getCol()].getValue() + " --- on row " + stepDown(row,col).getRow() + ", col " + stepDown(row,col).getCol());
                    }
                    if(checkNeighbours(stepLeft(row,col))){
                        Coordinate temp1 = stepLeft(row,col);
                        if(!basisPoints.contains(temp1)){
                            basisPoints.add(temp1);
                        }
                        System.out.println("Added " + map[stepLeft(row,col).getRow()][stepLeft(row,col).getCol()].getValue() + " --- on row " + stepLeft(row,col).getRow() + ", col " + stepLeft(row,col).getCol());
                    }
                    if (checkNeighbours(stepRight(row,col))){
                        Coordinate temp1 = stepRight(row,col);
                        if(!basisPoints.contains(temp1)){
                            basisPoints.add(temp1);
                        }
                        System.out.println("Added " + map[stepRight(row,col).getRow()][stepRight(row,col).getCol()].getValue() + " --- on row " + stepRight(row,col).getRow() + ", col " + stepRight(row,col).getCol());
                    }
                }
            }
            basinSizes.add(basisPoints.size());
        }
    }

    /**
     * computes to result for partTwo
     * get the three biggest basins and multiply their size
     * @return the result
     */
    public int getBasinResults(){

        findBasins();
        Collections.sort(basinSizes);

        int startIndex = basinSizes.size()-3;
        List<Integer> sublist = basinSizes.subList(startIndex,basinSizes.size());

        int counter = 1;
        for (Integer i: sublist){
            counter *= i;
        }

        return counter;
    }


    /**
     * computes the result for part one
     * sums the height of all lowpoints +1
     * @return
     */
    public int computeRiskLevel() {

        int sum = 0;

        for (int point : lowPoints) {
            sum += (point + 1);
        }
        return sum;
    }
}
