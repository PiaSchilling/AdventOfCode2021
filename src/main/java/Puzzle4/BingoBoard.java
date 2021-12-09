package Puzzle4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class BingoBoard {

    private final BingoDigit [] [] bingoBoard;
    private Scanner fileScanner;
    private int boardNumber;
    private static boolean won = false;
    private boolean boardWon = false;
    private static int boardCounter = 0;

    public BingoBoard(Scanner fileScanner){
        //get the scanner from the other class because we need the scanner at the location/line on which he is also at in the other class
        //if we would instance a new scanner everytime the scanner would start at the file top everytime
        this.fileScanner = fileScanner;
        bingoBoard = fillBingoBoard();
        this.boardNumber = boardCounter;
        boardCounter++;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.printf("%3s",bingoBoard[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }


    private BingoDigit [] [] fillBingoBoard(){

        //vorne rows hinten columns
        BingoDigit[][] temp = new BingoDigit[5][5];

            //fill the multidimensional array with bingoDigits with a nested loop
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    int nextInt = fileScanner.nextInt();
                    BingoDigit nextDigit = new BingoDigit(nextInt);
                    temp[i][j] = nextDigit;
                }
            }

        return temp;
    }

    public boolean markBoard(int digit){
        for (int i = 0; i < this.bingoBoard.length; i++) {
            for (int j = 0; j < this.bingoBoard [i].length; j++) {
                if(bingoBoard[i][j].getValue() == digit){
                    bingoBoard[i][j].setMarked();
                    System.out.println("mark " + digit + " on bingoboard " + boardNumber);
                    if(checkWin()){
                        return true;
                    }
                    return false;
                }
            }
        }
        return false;
    }

    private boolean checkWin(){
        int markedCounterRow;

        //check rows
        for (int i = 0; i < this.bingoBoard.length; i++) {
            markedCounterRow = 0;
            for (int j = 0; j < this.bingoBoard [i].length; j++) {
                    if(bingoBoard[i][j].isMarked()){
                        markedCounterRow++;
                }
            }
            if(markedCounterRow == 5){
                System.out.println(this.boardNumber + "WON");
                won = true;
                boardWon = true;
                return true;
            }
        }

        int markedCounterCol;

        for (int col = 0; col < 5; col++) {
            markedCounterCol = 0;
            for (int row = 0; row < 5; row++) {
                if(bingoBoard[row][col].isMarked()){
                    markedCounterCol++;
                }
            }

            if(markedCounterCol == 5){
                System.out.println(this.boardNumber + "WON");
                won = true;
                boardWon = true;
                return true;
            }
        }

        return false;
    }


    public static boolean isWon() {
        return won;
    }

    public int getSum() {
        int sum = 0;

        for (int i = 0; i < this.bingoBoard.length; i++) {
            for (int j = 0; j < this.bingoBoard[i].length; j++) {
                if (!bingoBoard[i][j].isMarked()) {
                    sum += bingoBoard[i][j].getValue();
                }
            }
        }
        return sum;
    }

    public int getBoardNumber(){
        return boardNumber;
    }

    public boolean isBoardWon(){
        return this.boardWon;
    }

    @Override
    public String toString() {

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.printf("%3s",bingoBoard[i][j]);
                System.out.print(bingoBoard[i][j].isMarked());
            }
            System.out.println();
        }
        System.out.println();

        return "BingoBoard{" +
                "bingoBoard=" + Arrays.toString(bingoBoard) +
                '}';
    }
}
