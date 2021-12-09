package Puzzle8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PartTwo {
    public static void main(String[] args) {

        File puzzleInput = new File(args[0]);
        ArrayList<String> lines = new ArrayList<>();

        try(Scanner fileScanner = new Scanner(puzzleInput)){

            //fill the list with the input values
            while (fileScanner.hasNext()){
                String temp = fileScanner.nextLine();
                lines.add(temp);
            }
        }catch (FileNotFoundException f){
            f.printStackTrace();
        }

        int counter = 0;
        for (String line : lines){
            Helper helper = new Helper(line);
            String temp = helper.decode();
            System.out.println(temp);

            counter += Integer.parseInt(temp);
        }

        System.out.println(counter);

    }
}
