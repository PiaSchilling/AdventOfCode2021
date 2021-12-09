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

        System.out.println(codeStrings.size());

        decode();
    }

    /**
     * splits the input line in the sample part and in the output part
     */
    private void splitString(){
        this.sampleString = line.substring(0,line.indexOf("|"));
        this.outputString = line.substring(line.indexOf("|")+1).trim();

        System.out.println(sampleString);
        System.out.println(outputString);
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

        System.out.println("1 " + oneString);
        System.out.println("7 " + sevenString);
        System.out.println("4 " + fourString);
        System.out.println("5 seg " + Arrays.toString(fiveSegmentStrings.toArray()));
        System.out.println("6 seg " + Arrays.toString(sixSegmentStrings.toArray()));
        System.out.println("8 " + eightString);

        codeStrings.put(orderString(oneString),1);
        codeStrings.put(orderString(sevenString),7);
        codeStrings.put(orderString(fourString),4);
        codeStrings.put(orderString(eightString),8);

    }

    /**
     * finds the char which is present in the one String but not in the other one
     * @param stringA
     * @param stringB
     * @return the distinct char, X when no distinct char found
     */
    private char findDistinctChar(String stringA, String stringB) throws IllegalArgumentException{

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
        throw new IllegalArgumentException("No distinct char found for String " + stringA + " and " + stringB);
    }

    private char findMissingSegment(String stringA, String stringB) throws IllegalArgumentException{

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
        throw new IllegalArgumentException("No distinct char found for String " + stringA + " and " + stringB);
    }

    private void findASegment(){
        try{
            aSegment = findDistinctChar(oneString,sevenString);
            System.out.println("aSegment found " + aSegment);
        }catch (IllegalArgumentException i){
            System.out.println(i.getMessage());
        }
    }

    private void findGSegment(){
        String helperString = fourString + aSegment;

        for (String s : sixSegmentStrings){
            if('X' != findDistinctChar(helperString,s)){
                gSegment = findDistinctChar(helperString,s);
                nineString = s;
                sixSegmentStrings.remove(nineString);

                codeStrings.put(orderString(nineString),9);

                System.out.println("gSegment found " + gSegment);
                System.out.println("nineString found " + nineString);
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

                System.out.println("cSegment found " + cSegment);
                System.out.println("sixString found " + sixString);
                System.out.println("zeroString found " + zeroString);

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
        System.out.println("fSegment found " + fSegment);
    }

    private void findDSegment(){
        allCurrentlyFound = Character.toString(aSegment) + Character.toString(gSegment) + Character.toString(cSegment) + Character.toString(fSegment);
        System.out.println("Currently found segments " + allCurrentlyFound);

        for (String s : fiveSegmentStrings){
            char temp = findDistinctChar(allCurrentlyFound,s);
            if('X' != temp){
                dSegment = temp;
                threeString = s;
                fiveSegmentStrings.remove(threeString);

                System.out.println("dSegment found " + dSegment);
                System.out.println("threeString found " + threeString);

                codeStrings.put(orderString(threeString),3);

                return;
            }
            System.out.println(temp);
        }
    }

    private void findBSegment(){
        allCurrentlyFound += dSegment;
        bSegment = findDistinctChar(allCurrentlyFound,nineString);

        System.out.println("bSegment found " + bSegment);
    }

    private void findESegment(){
        allCurrentlyFound += bSegment;

        eSegment = findDistinctChar(allCurrentlyFound,eightString);
        System.out.println("eSegment found " + eSegment);
    }


    public String decode(){
        List<String> list = Arrays
                .stream(outputString.split("\\s"))
                .collect(Collectors.toList());

        String result = "";

        for (String s : list){
            if(codeStrings.get(orderString(s)) == null){
                System.out.println("NULL");
            }
            int temp = codeStrings.get(orderString(s));
            result += temp;
        }
        return result;
    }


    private void remainingStrings(){
        twoString = Character.toString(aSegment) + Character.toString(cSegment) + Character.toString(dSegment) + Character.toString(eSegment)+ Character.toString(gSegment);
        fiveString = Character.toString(aSegment) + Character.toString(bSegment) + Character.toString(dSegment) + Character.toString(fSegment)+ Character.toString(gSegment);

        if(fiveString.equals("bdefg")){
            System.out.println("STOP");
        }

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
