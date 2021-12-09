package Puzzle4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PartOne {
    public static void main(String[] args) {

        ArrayList<BingoBoard> boards = new ArrayList<>();
        ArrayList<Integer> markDigits = new ArrayList<>();


        try (Scanner fileScanner = new Scanner(new File("src/Puzzle4/PuzzleInput"))) {

            //get the first line with all the digits which will be marked later
            String firstLine = fileScanner.nextLine();
            Scanner lineScanner = new Scanner(firstLine);

            while (lineScanner.hasNext()) {
                lineScanner.useDelimiter("\\D");
                markDigits.add(lineScanner.nextInt());
            }

            System.out.println(Arrays.toString(markDigits.toArray()));

            while (fileScanner.hasNext()) {
                // fill an array list with all boards contained by the file
                if (fileScanner.nextLine().isBlank()) {
                    //handover the scanner so we also handover the current location of the scanner
                    BingoBoard board = new BingoBoard(fileScanner);
                    boards.add(board);
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }


        //mark the boards and stop if one board has won
        int currentMarkNumber = 0;
        int currentBoard = 0;

        for (int i = 0; i < markDigits.size() && !BingoBoard.isWon(); i++) {
            currentMarkNumber = markDigits.get(i);

            for (int j = 0; j < boards.size() && !BingoBoard.isWon(); j++) {
                boards.get(j).markBoard(currentMarkNumber);
                currentBoard = j;
            }
        }

        System.out.println(currentMarkNumber);

        //compute the result
        BingoBoard winningBoard = boards.get(currentBoard);
        System.out.println(winningBoard.getSum());
        System.out.println(winningBoard.getSum() * currentMarkNumber);
    }
}
