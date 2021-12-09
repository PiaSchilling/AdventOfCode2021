package Puzzle4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PartTwo {
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


        //mark the boards
        int currentMarkNumber = 0;
       // int currentBoard = 0;
        int index = 0;

        for (int i = 0; i < markDigits.size() && boards.size() > 1; i++) {
            //search for this number in the array
            currentMarkNumber = markDigits.get(i);

            if(currentMarkNumber == 72){
                System.out.println("P"); //testing
            }
            //index = i;

            for (int j = 0; j < boards.size(); j++) {
                System.out.println("---------------------- Try to Mark " + currentMarkNumber + " on board " );
                int boardNumber = boards.get(j).getBoardNumber();
                if(boards.get(j).markBoard(currentMarkNumber)){
                    //if board is won after marking the one digit it will be removed from the list
                    boards.remove(boards.get(j));
                    j--;
                }
            }
        }

        //compute the result
        BingoBoard winningBoard = boards.get(0);

        while(!winningBoard.isBoardWon()){
            for (int i = index; i < markDigits.size() && !winningBoard.isBoardWon(); i++) {
                currentMarkNumber = markDigits.get(i);
                winningBoard.markBoard(currentMarkNumber);

                System.out.println(winningBoard);
            }
        }

        System.out.println(winningBoard.getBoardNumber());
        System.out.println(winningBoard.getSum());
        System.out.println(currentMarkNumber);
        System.out.println(boards.size() + "size");
        System.out.println(winningBoard.getSum() * currentMarkNumber);
    }
}
