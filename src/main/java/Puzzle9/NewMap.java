package Puzzle9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class NewMap {

    private SpecialInt[][] map;
    private ArrayList<Integer> lowPoints = new ArrayList<>();

    private ArrayList<Coordinate> lowPointCoordinates = new ArrayList<>();
    private ArrayList<Integer> basinSizes = new ArrayList<>();

    private int height;
    private int width;

    private int basisCounter = 0;

    public NewMap(int width, int height) {

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

    public void findBasins() {
        int temp = 0;

        ArrayList<Coordinate> basisPoints;
        for (Coordinate c : lowPointCoordinates){
            temp++;
            basisPoints = new ArrayList<>();
            //add erster low point zur liste
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
            System.out.println(Arrays.toString(basisPoints.toArray()));
            System.out.println(basisPoints.size());

            basinSizes.add(basisPoints.size());
        }

        //Arrays.sort(basinSizes.toArray());
        Collections.sort(basinSizes);
        System.out.println("Number of basins " + basinSizes.size());
        System.out.println(basinSizes.toString());

        int startIndex = basinSizes.size()-3;
        List<Integer> sublist = basinSizes.subList(startIndex,basinSizes.size());

        System.out.println(Arrays.toString(sublist.toArray()));

        int counter = 1;
        for (Integer i: sublist){
            counter *= i;
        }

        System.out.println(counter);
    }

    /**
     *checks if a neighbour is now a low point
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



    private Coordinate stepUp(int currentRow, int currentCol) {
        int value = map[currentRow-1][currentCol].getValue();
        return new Coordinate(currentRow-1,currentCol);
    }

    private Coordinate stepDown(int currentRow, int currentCol) {
        int value = map[currentRow+1][currentCol].getValue();
        return new Coordinate(currentRow+1,currentCol);
    }

    private Coordinate stepLeft(int currentRow, int currentCol) {
        int value = map[currentRow][currentCol-1].getValue();
        return new Coordinate(currentRow,currentCol-1);
    }

    private Coordinate stepRight(int currentRow, int currentCol) {
        int value = map[currentRow][currentCol+1].getValue();
        return new Coordinate(currentRow,currentCol+1);
    }


    private boolean isNine(SpecialInt specialInt){
        return specialInt.getValue() == 9;
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
