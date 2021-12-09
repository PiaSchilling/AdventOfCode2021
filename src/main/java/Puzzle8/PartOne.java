package Puzzle8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PartOne {

    public static void main(String[] args) {

        Path inputPath = Paths.get(args[0]);

        try {
            List<String> inputLines = Files.lines(inputPath)
                    .map(s -> s.substring(s.indexOf("|")))
                    .collect(Collectors.toList());

            List<String[]> spiltLines = inputLines.stream()
                    .map(s -> s.split("\\s"))
                    .collect(Collectors.toList());

            long countEasyNumbers = 0;
            for (String [] array : spiltLines){
                long count = Arrays.stream(array)
                        .filter(s -> s.length() == 2 || s.length() == 4 || s.length() == 7 || s.length() == 3)
                        .count();
                countEasyNumbers += count;
            }

            System.out.println(countEasyNumbers);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
