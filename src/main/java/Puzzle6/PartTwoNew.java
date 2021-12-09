package Puzzle6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class PartTwoNew {

    public static void main(String[] args) {

        File puzzleInput = new File(args[0]);
        ArrayList<Integer> fishValues = new ArrayList<>();
        long counter = 0;

        try(Scanner fileScanner = new Scanner(puzzleInput).useDelimiter(",")){

            //fill the list with the input values
            while (fileScanner.hasNext()){
                int temp =  fileScanner.nextInt();
                fishValues.add(temp);
            }
        }catch (FileNotFoundException f){
            f.printStackTrace();
        }


        ArrayList<Long> disrti = new ArrayList<>(9);
        ArrayList<Long> temp = new ArrayList<>(9);


        for (int i = 0; i < 9; i++) {
            disrti.add(0L);
            temp.add(0L);
        }

        System.out.println(Arrays.toString(disrti.toArray()));

        //rausfinden, welche zahl wie oft vorkommt, index entspricht der zahl
        for (int i = 0; i < fishValues.size(); i++) {
            int index = fishValues.get(i);
            disrti.set(index,(disrti.get(index)+1));
        }

        System.out.println(Arrays.toString(disrti.toArray()));

        for (int day = 0; day <= 256; day++) {
            Collections.fill(temp,0L);

            //shift the numbers left and add new fishies if the timer is 0
            for (int index = 0; index < disrti.size(); index++) {

                long amountOnIndex = disrti.get(index);

                if(amountOnIndex > 0){
                    switch (index){
                        case 0 -> {temp.set(6,amountOnIndex);
                                    temp.set(8,amountOnIndex);}
                        case 7 -> temp.set(6,temp.get(6)+amountOnIndex);
                        case 8 -> temp.set(7,temp.get(7)+amountOnIndex);
                        default -> temp.set(index-1,amountOnIndex);
                    }
                }
            }
            System.out.println("--------------------------");
            System.out.println(Arrays.toString(disrti.toArray()));

            for (long i : disrti){
                counter+=i;
            }
            System.out.println("Amount of fish after day " + day + ": " + counter);
            System.out.println("--------------------------");

            disrti.clear();
            disrti.addAll(temp);
            counter = 0;
        }


    }
}
