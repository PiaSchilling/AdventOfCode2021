package Puzzle7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class PartOne {
    public static void main(String[] args) {

        File puzzleInput = new File(args[0]);
        ArrayList<Integer> inputValues = new ArrayList<>();

        try(Scanner fileScanner = new Scanner(puzzleInput).useDelimiter(",")){

            //fill the list with the input values
            while (fileScanner.hasNext()){
                int temp = fileScanner.nextInt();
                inputValues.add(temp);
            }
        }catch (FileNotFoundException f){
            f.printStackTrace();
        }

        //find max value in the list

        int maxPosition = Collections.max(inputValues);
        int currentMaxFuel = Integer.MAX_VALUE;
        int tempFuel = 0;



        System.out.println(Arrays.toString(inputValues.toArray()));
        System.out.println(maxPosition);

        //test for every position
        for (int position = 0; position < maxPosition; position++) {
            //for every list value
            tempFuel = 0;

            for (int index = 0; index < inputValues.size(); index++) {

                tempFuel += Math.abs(inputValues.get(index)-position);
            }

            if(tempFuel < currentMaxFuel){
                currentMaxFuel = tempFuel;
            }
        }

        System.out.println(currentMaxFuel);
    }
}
