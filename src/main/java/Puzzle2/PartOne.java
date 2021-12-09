package Puzzle2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PartOne {
    public static void main(String[] args) {

        ArrayList<String> forwards = new ArrayList<>();
        ArrayList<String> ups = new ArrayList<>();
        ArrayList<String> downs = new ArrayList<>();

        int forwardCounter = 0;
        int verticalCounter = 0;

        String nextLine;

        try (Scanner fileScanner = new Scanner(new File("src/Puzzle2/PuzzleInput"))){

             // filter the file into the different arrayLists
            while(fileScanner.hasNext()){
                nextLine = fileScanner.nextLine();
                if(nextLine.startsWith("f")){
                    forwards.add(nextLine);
                }else if(nextLine.startsWith("u")){
                    ups.add(nextLine);
                }else {
                    downs.add(nextLine);
                }
            }

            for (String forward : forwards){
                int temp = Character.getNumericValue(forward.charAt(forward.length()-1));
                forwardCounter += temp;
            }

            for (String up : ups){
                int temp = Character.getNumericValue(up.charAt(up.length()-1));
                verticalCounter -= temp;
            }

            for (String down : downs){
                int temp = Character.getNumericValue(down.charAt(down.length()-1));
                verticalCounter += temp;
            }

            System.out.println(forwardCounter * verticalCounter); //2117664

        }catch (FileNotFoundException fileNotFoundException){
            fileNotFoundException.printStackTrace();
        }
    }
}
