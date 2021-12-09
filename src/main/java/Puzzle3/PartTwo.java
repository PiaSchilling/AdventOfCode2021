package Puzzle3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PartTwo {

    public static void main(String[] args) {

        int oneCounterOxy;
        int zeroCounterOxy;

        int oneCounterCo2;
        int zeroCounterCo2;

        ArrayList<String> oxygenStrings = new ArrayList<>();
        ArrayList<String> co2Strings = new ArrayList<>();


        //first fill the two array lists with all binary numbers contained in the file so the lists can later be filtered
        try {
            Scanner fileScanner = new Scanner(new File("src/Puzzle3/PuzzleInput"));
            while (fileScanner.hasNext()){
                String line = fileScanner.nextLine();
                oxygenStrings.add(line);
                co2Strings.add(line);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        //OxyDing
        for (int i = 0; i < 12; i++) {

            oneCounterOxy = 0;
            zeroCounterOxy = 0;

            //go through every line
            for (int j = 0; j < oxygenStrings.size(); j++) {

                    //get from every line the number at the first place
                    int bit = Character.getNumericValue(oxygenStrings.get(j).charAt(i));

                    //count the 1 and 0s
                    if (bit == 1) {
                        oneCounterOxy++;
                    } else {
                        zeroCounterOxy++;
                    }
                }

                if (oneCounterOxy > zeroCounterOxy) {
                    int finalI = i;
                    oxygenStrings = (ArrayList<String>) oxygenStrings.stream()
                            .filter(s -> s.charAt(finalI) == '1')
                            .collect(Collectors.toList());

                } else if(zeroCounterOxy > oneCounterOxy){
                    int finalI = i;
                    oxygenStrings = (ArrayList<String>) oxygenStrings.stream()
                            .filter(s -> s.charAt(finalI) == '0')
                            .collect(Collectors.toList());

                }else {
                    int finalI = i;
                    oxygenStrings = (ArrayList<String>) oxygenStrings.stream()
                            .filter(s -> s.charAt(finalI) == '1')
                            .collect(Collectors.toList());
                }

                System.out.println(Arrays.toString(oxygenStrings.toArray()));
        }
        System.out.println(Integer.parseInt(oxygenStrings.get(0),2));


        //Co2Ding
        for (int i = 0; i < 12 && co2Strings.size() > 1; i++) {

            oneCounterCo2 = 0;
            zeroCounterCo2= 0;

            //go through every line
            for (int j = 0; j < co2Strings.size(); j++) {

                //get from every line the number at the first place
                int bit = Character.getNumericValue(co2Strings.get(j).charAt(i));

                //count the 1 and 0s
                if (bit == 1) {
                    oneCounterCo2++;
                } else {
                    zeroCounterCo2++;
                }
            }

            if (oneCounterCo2 < zeroCounterCo2) {
                int finalI = i;
                co2Strings = (ArrayList<String>) co2Strings.stream()
                        .filter(s -> s.charAt(finalI) == '1')
                        .collect(Collectors.toList());

            } else if(zeroCounterCo2 < oneCounterCo2){
                int finalI = i;
                co2Strings = (ArrayList<String>) co2Strings.stream()
                        .filter(s -> s.charAt(finalI) == '0')
                        .collect(Collectors.toList());

            }else {
                int finalI = i;
                co2Strings = (ArrayList<String>) co2Strings.stream()
                        .filter(s -> s.charAt(finalI) == '0')
                        .collect(Collectors.toList());
            }

             System.out.println(Arrays.toString(co2Strings.toArray()));

        }

        int oxy = Integer.parseInt(oxygenStrings.get(0),2);
        int co2 = Integer.parseInt(co2Strings.get(0),2);
        System.out.println(oxy * co2); //903810

    }


}
