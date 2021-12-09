package Puzzle6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class PartTwo {

    public static void main(String[] args) {

        long counter = 0;

        File puzzleInput = new File(args[0]);
        ArrayList<Short> fishValues = new ArrayList<>();

        try(Scanner fileScanner = new Scanner(puzzleInput).useDelimiter(",")){

            //fill the list with the input values
            while (fileScanner.hasNext()){
                short temp = (short) fileScanner.nextInt();
                fishValues.add(temp);
            }
        }catch (FileNotFoundException f){
            f.printStackTrace();
        }


        ArrayList<Short> temp;


        for (int a = 0; a < fishValues.size(); a++) {

            temp = new ArrayList<>();
            short testValue = fishValues.get(a);
            temp.add(testValue);

            for (short day = 0; day < 256; day++) {
                System.out.println(day);

                for (int j = 0; j < temp.size(); j++) {

                    if(temp.get(j) == 0){
                        temp.add((short) 9);
                        temp.set(j, (short) 6);
                    }else{
                        temp.set(j, (short) (temp.get(j)-1));
                    }
                }

            }
            //System.out.println(Arrays.toString(temp.toArray()));
            counter += temp.size();
        }



        //System.out.println(Arrays.toString(temp.toArray()));
        System.out.println(counter);
    }
}
