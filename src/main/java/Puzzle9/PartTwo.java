package Puzzle9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PartTwo {
    public static void main(String[] args) {
        File file = new File(args[0]);

        int width = 0;
        int height = 0;
        int lineCounter = 0;
        String line = "";

        //get width and height
        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNext()){
                lineCounter++;
                line = fileScanner.nextLine();
            }

            height = lineCounter;
            width = line.length();

            System.out.println(height);
            System.out.println(width);

        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        NewMap map = new NewMap(width,height);


        try {
            map.fillMap(file);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        map.findLowPoints();
        System.out.println(map.computeRiskLevel());

        map.findBasins();
    }
}

