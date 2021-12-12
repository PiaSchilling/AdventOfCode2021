package Puzzle8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Helper {

    private String oneString;
    private String fourString;
    private String sevenString;
    private String eightString;
    private String nineString;
    private String sixString;
    private String zeroString;
    private String twoString;
    private String threeString;
    private String fiveString;

    private char aSegment;
    private char gSegment;
    private char cSegment;
    private char fSegment;
    private char dSegment;
    private char bSegment;
    private char eSegment;

    HashMap<String,Integer> codeStrings = new HashMap<String, Integer>();

    String allCurrentlyFound;

    ArrayList<String> sixSegmentStrings = new ArrayList<>();
    ArrayList<String> fiveSegmentStrings = new ArrayList<>();

    private String line;

    private String sampleString;
    private String outputString;

    public Helper(String line){

        this.line = line;

        splitString();
        findUniques();
        findASegment();
        findGSegment();
        findCSegment();
        findFSegment();
        findDSegment();
        findBSegment();
        findESegment();
        remainingStrings();

        decode();
    }

    /**
     * splits the input line in the sample part and in the output part
     */
    private void splitString(){
        this.sampleString = line.substring(0,line.indexOf("|"));
        this.outputString = line.substring(line.indexOf("|")+1).trim();
    }


    public void findUniques(){
        List<String> list = Arrays
                .stream(sampleString.split("\\s"))
                .collect(Collectors.toList());

        for (String s : list){
            int length = s.length();
            switch (length){
                case 2 -> oneString = s;
                case 3 -> sevenString = s;
                case 4 -> fourString = s;
                case 5 -> fiveSegmentStrings.add(s);
                case 6 -> sixSegmentStrings.add(s);
                case 7 -> eightString = s;
                default -> System.out.println("No matching case found");
            }
        }
        codeStrings.put(orderString(oneString),1);
        codeStrings.put(orderString(sevenString),7);
        codeStrings.put(orderString(fourString),4);
        codeStrings.put(orderString(eightString),8);
    }


    private char findDistinctChar(String stringA, String stringB){

        char distinctChar = ' ';
        int counter = 0;

        String longerString = stringA.length() > stringB.length() ? stringA : stringB;
        String shorterString = stringA.length() < stringB.length() ? stringA : stringB;

        for (int i = 0; i < longerString.length(); i++) {
            char currentChar = longerString.charAt(i);

            int indexInStringB = shorterString.indexOf(currentChar);

            if(indexInStringB == -1){
                distinctChar = currentChar;
                counter++;
            }
        }
        if(counter == 1){
            return distinctChar;
        }else if(counter > 1){
            return 'X';
        }
        return 'X';
    }

    private char findMissingSegment(String stringA, String stringB){

        char distinctChar = ' ';
        int counter = 0;

        String longerString = stringA.length() < stringB.length() ? stringA : stringB;
        String shorterString = stringA.length() > stringB.length() ? stringA : stringB;

        for (int i = 0; i < longerString.length(); i++) {
            char currentChar = longerString.charAt(i);

            int indexInStringB = shorterString.indexOf(currentChar);

            if(indexInStringB == -1){
                distinctChar = currentChar;
                counter++;
            }
        }
        if(counter == 1){
            return distinctChar;
        }else if(counter > 1 || counter == 0){
            return 'X';
        }
        return 'X';
    }

    private void findASegment(){
            aSegment = findDistinctChar(oneString,sevenString);
    }

    private void findGSegment(){
        String helperString = fourString + aSegment;
        for (String s : sixSegmentStrings){
            if('X' != findDistinctChar(helperString,s)){
                gSegment = findDistinctChar(helperString,s);
                nineString = s;
                sixSegmentStrings.remove(nineString);
                codeStrings.put(orderString(nineString),9);
                return;
            }
        }
    }

    private void findCSegment(){
        for (String s: sixSegmentStrings){
            char temp = findMissingSegment(oneString,s);
            if('X' != temp){
                cSegment = temp;
                sixString = s;
                sixSegmentStrings.remove(sixString);
                zeroString = sixSegmentStrings.get(0);

                codeStrings.put(orderString(sixString),6);
                codeStrings.put(orderString(zeroString),0);
                return;
            }
        }
    }

    private void findFSegment(){
        int indexOfCSegment = oneString.indexOf(cSegment);
        if(indexOfCSegment == 0){
            fSegment = oneString.charAt(1);
        }else{
            fSegment = oneString.charAt(0);
        }
    }

    private void findDSegment(){
        allCurrentlyFound = Character.toString(aSegment) + gSegment + cSegment + fSegment;
        for (String s : fiveSegmentStrings){
            char temp = findDistinctChar(allCurrentlyFound,s);
            if('X' != temp){
                dSegment = temp;
                threeString = s;
                fiveSegmentStrings.remove(threeString);
                codeStrings.put(orderString(threeString),3);

                return;
            }
        }
    }

    private void findBSegment(){
        allCurrentlyFound += dSegment;
        bSegment = findDistinctChar(allCurrentlyFound,nineString);
    }

    private void findESegment(){
        allCurrentlyFound += bSegment;
        eSegment = findDistinctChar(allCurrentlyFound,eightString);
    }


    public String decode(){
        List<String> list = Arrays
                .stream(outputString.split("\\s"))
                .collect(Collectors.toList());

        String result = "";

        for (String s : list){
            int temp = codeStrings.get(orderString(s));
            result += temp;
        }
        return result;
    }


    private void remainingStrings(){
        twoString = Character.toString(aSegment) + cSegment + dSegment + eSegment + gSegment;
        fiveString = Character.toString(aSegment) + bSegment + dSegment + fSegment + gSegment;

        codeStrings.put(orderString(twoString),2);
        codeStrings.put(orderString(fiveString),5);
    }

    private String orderString(String string){
        char[] charArray = string.toCharArray();
        Arrays.sort(charArray);

        String sortedString = "";
        for (char c : charArray){
            sortedString += c;
        }
        return sortedString;
    }
}
