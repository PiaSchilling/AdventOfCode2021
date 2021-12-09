package Puzzle5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PartTwo {

    public static void main(String[] args) {

        //keeps the lines (horizontal and verticals)
        ArrayList<Line> horizontalLines = new ArrayList<>();
        ArrayList<Line> verticalLines = new ArrayList<>();
        ArrayList<Line> diagonalLines = new ArrayList<>();

        Board board = new Board(1000);

        //get the inputFile as an commandLine argument
        File inputFile = new File(args[0]);

        // ---------- --------- --------- ----------- get the file in put and sort it -------- ---------- ---------- ----------- ------------
        try {
            Scanner fileScanner = new Scanner(inputFile);
            Scanner lineScanner;


            while (fileScanner.hasNext()){
                String line = fileScanner.nextLine();
                //replace the arrow and whitespaces with comas so alwo multiple digit-numbers can be extracted by using the coma as delimeter
                line = line.replaceAll("[->\\s]",",");
                lineScanner = new Scanner(line).useDelimiter(",");

                int x1 = 0;
                int y1 = 0;
                int x2 = 0;
                int y2 = 0;

                int counter = 0;

                while (lineScanner.hasNext()){
                    String nextChar = lineScanner.next();
                    if(isNumeric(nextChar)){

                        //extract the digits of one line and save them in variables
                        switch (counter) {
                            case 0 -> x1 = Integer.parseInt(nextChar);
                            case 1 -> y1 = Integer.parseInt(nextChar);
                            case 2 -> x2 = Integer.parseInt(nextChar);
                            case 3 -> y2 = Integer.parseInt(nextChar);
                        }

                        counter++;
                    }
                }

                //create coordinate objects out of the x and y variables
                Coordinate coordinate1 = new Coordinate(x1,y1);
                Coordinate coordinate2 = new Coordinate(x2,y2);

                //check if they form horizontal or vertical lines, if so they are saved in the matching list
                if(x1 == x2){
                    VerticalLine verticalLine = new VerticalLine(coordinate1,coordinate2);
                    verticalLines.add(verticalLine);
                }else if(y1 == y2){
                    HorizontalLine horizontalLine = new HorizontalLine(coordinate1,coordinate2);
                    horizontalLines.add(horizontalLine);
                }else{
                    DiagonalLine diagonalLine = new DiagonalLine(coordinate1,coordinate2);
                    diagonalLines.add(diagonalLine);
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        // ----------- ---------- ----------- ---------- mark the lines on the board ------- ----------- ---------- ------------ -----------

        for (Line line : horizontalLines){
            board.markLine(line);
        }

        for (Line line : verticalLines){
            board.markLine(line);
        }

        for (Line line : diagonalLines){
            board.markLine(line);
        }

        System.out.println(board.computeResult());
    }

    /**
     * check whether a string is a numeric value
     * @param string the string which should be tested
     * @return true if the String can successfully be parsed to an integer
     */
    public static boolean isNumeric(String string){
        try{
            int value = Integer.parseInt(string);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
