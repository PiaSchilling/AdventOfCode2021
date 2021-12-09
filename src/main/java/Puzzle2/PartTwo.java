package Puzzle2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PartTwo {
    public static void main(String[] args) {

        int horizontalCounter = 0;
        int depthCounter = 0;
        int aimCounter = 0;

        String nextLine;

        try (Scanner fileScanner = new Scanner(new File("src/Puzzle2/PuzzleInput"))){

            while(fileScanner.hasNext()){
                nextLine = fileScanner.nextLine();
                if(nextLine.startsWith("f")){
                    int temp = Character.getNumericValue(nextLine.charAt(nextLine.length()-1));
                    horizontalCounter += temp;
                    depthCounter += aimCounter * temp;
                }else if(nextLine.startsWith("u")){
                    int temp = Character.getNumericValue(nextLine.charAt(nextLine.length()-1));
                    aimCounter -= temp;
                }else {
                    int temp = Character.getNumericValue(nextLine.charAt(nextLine.length()-1));
                    aimCounter += temp;
                }
            }

            System.out.println(horizontalCounter * depthCounter); //2117664

        }catch (FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
    }
}
