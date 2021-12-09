package Puzzle6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * this works only for a small amount of days, java will run out of memory otherwise
 */
public class PartOne {

    public static void main(String[] args) {

        File puzzleInput = new File(args[0]);
        ArrayList<Integer> fishValues = new ArrayList<>();

        try(Scanner fileScanner = new Scanner(puzzleInput).useDelimiter(",")){

            //fill the list with the input values
            while (fileScanner.hasNext()){
                int temp = fileScanner.nextInt();
                fishValues.add(temp);
            }
        }catch (FileNotFoundException f){
            f.printStackTrace();
        }


        ArrayList<Integer> temp = new ArrayList<>();

        for (int day = 0; day < 14; day++) {

            temp.clear();
            temp.addAll(fishValues);

            for (int i = 0; i < temp.size(); i++) {
                if(temp.get(i) == 0){
                    fishValues.set(i,6);
                    fishValues.add(8);
                }else{
                    fishValues.set(i,(fishValues.get(i)-1));
                }
            }
        }

        System.out.println(fishValues.size());
    }
}
