package Puzzle1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PartTwo {
    public static void main(String[] args) {

        int counter = 0;
        int threeSumPrevious = 0;
        int threeSum;
        ArrayList<Integer> values = new ArrayList<>();

        /**
         * Fill an array with all the values
         */
        try (Scanner fileScanner = new Scanner(new File("src/Puzzle1/PuzzleInput"))){
            while (fileScanner.hasNext()) {
                // System.out.println(fileScanner.nextLine());
                values.add(fileScanner.nextInt());
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        /**
         * check the array for increasing values
         */

        for (int i = 0; i < values.size() - 2; i++) {
            threeSum = 0;
            for (int j = i; j < i + 3 ; j++) {
                threeSum += values.get(j);
            }
            if(threeSum > threeSumPrevious){
                counter++;
            }
            threeSumPrevious = threeSum;
        }

        System.out.println(counter); //1253 but the answer is 1253 bc the first tripplet is always larger than 0

    }
}
