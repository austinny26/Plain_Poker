import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;

public class PokerRunner {
    public static void main(String[] args) {

        // step 1: create a file object
        File f = new File("src/input_file");

        HashMap<String, Integer> bigCards = new HashMap<String, Integer>();
        bigCards.put("Jack", 11);
        bigCards.put("Queen", 12);
        bigCards.put("King", 13);
        bigCards.put("Ace", 14);

        String fileData = "";
        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String currentLine = s.nextLine();
                fileData += currentLine + "\n";
            }

            // a String array where every item in the array is a line from the file

            String[] fileArray = fileData.split("\n");


            for (String line : fileArray) {
                // split by space, now we have a list of String numbers
                String newLine = line.replace("|",",");
                String [] stringNumbers = newLine.split(",");


                // create an array of integers
                int[] numbers = new int[stringNumbers.length - 1];

                // convert string numbers into integers
                try {
                    for (int i = 0; i < numbers.length; i++) {
                        try {
                            numbers[i] = Integer.parseInt(stringNumbers[i]);
                        } catch (NumberFormatException e) {
                            if (Objects.equals(stringNumbers[i], "Jack")) {
                                numbers[i] = bigCards.get("Jack");
                            }
                            if (Objects.equals(stringNumbers[i], "Queen")) {
                                numbers[i] = bigCards.get("Queen");
                            }
                            if (Objects.equals(stringNumbers[i], "King")) {
                                numbers[i] = bigCards.get("King");
                            }
                            if (Objects.equals(stringNumbers[i], "Ace")) {
                                numbers[i] = bigCards.get("Ace");
                            }
                        }
                    }
                }catch (ArrayIndexOutOfBoundsException _){}
                System.out.println(Arrays.toString(numbers));
                Cards a = new Cards(numbers);
                System.out.println(a.numOfFourKind());
                System.out.println(a.numOfFiveKind());
            }
        }
        catch (FileNotFoundException fe) {
            System.out.println("File was not found");
            System.exit(1);
        }


    }
}