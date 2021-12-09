package Puzzle1;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PartOne {
    public static void main(String[] args) {

        int counter = 0;
        ArrayList<Integer> values = new ArrayList<>();

        /**
         * Fill an array with all the values
         */
        try (Scanner fileScanner = new Scanner(new File("src/Puzzle1/PuzzleInput"))){
            while (fileScanner.hasNext()) {
                values.add(fileScanner.nextInt());
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        /**
         * check the array for increasing values
         */

        for (int i = 1; i < values.size(); i++) {
            if(values.get(i)>values.get(i-1)){
                counter++;
            }
        }

        System.out.println(counter); //1226

    }
}
