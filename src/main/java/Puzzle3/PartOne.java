package Puzzle3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PartOne {
    public static void main(String[] args) {

        int oneCounter = 0;
        int zeroCounter = 0;

        int gammaDecimal = 0;
        int epsilonDecimal = 0;

        String binaryString = "";
        String invertedBinaryString = "";

        //go through every ziffer in the binary int
        for (int i = 0; i < 12; i++) {

            oneCounter = 0;
            zeroCounter = 0;

            //go through every line
            try (Scanner fileScanner = new Scanner(new File("src/Puzzle3/PuzzleInput"))) {
                while (fileScanner.hasNext()) {
                    String line = fileScanner.nextLine();

                    //get from every line the number at the first place
                    int bit = Character.getNumericValue(line.charAt(i));

                    //count the 1 and 0s
                    if (bit == 1) {
                        oneCounter++;
                    } else {
                        zeroCounter++;
                    }
                }

                if (oneCounter > zeroCounter) {
                    binaryString = binaryString + "1";
                    invertedBinaryString = invertedBinaryString + "0";
                } else {
                    binaryString = binaryString + "0";
                    invertedBinaryString = invertedBinaryString + "1";
                }

            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }
        }

        //convert the binaryStrings to decimal integers
        gammaDecimal = Integer.parseInt(binaryString,2);
        epsilonDecimal = Integer.parseInt(invertedBinaryString,2);

        System.out.println(epsilonDecimal * gammaDecimal);

    }

}
