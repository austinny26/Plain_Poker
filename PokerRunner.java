import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class PokerRunner {
    public static void main(String[] args) {
        File f = new File("src/input_file");

        HashMap<String, Integer> bigCards = new HashMap<>();
        bigCards.put("Jack", 11);
        bigCards.put("Queen", 12);
        bigCards.put("King", 13);
        bigCards.put("Ace", 14);

        List<Cards> handsList = new ArrayList<>();

        try {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String currentLine = s.nextLine();
                String[] parts = currentLine.split("\\|");
                String handString = parts[0];
                int bidValue = Integer.parseInt(parts[1]);

                String[] stringNumbers = handString.split(",");
                int[] numbers = new int[stringNumbers.length];

                for (int i = 0; i < numbers.length; i++) {
                    try {
                        numbers[i] = Integer.parseInt(stringNumbers[i]);
                    } catch (NumberFormatException e) {
                        numbers[i] = bigCards.getOrDefault(stringNumbers[i], 0);
                    }
                }

                handsList.add(new Cards(numbers, bidValue));
            }

            Collections.sort(handsList);

            int rank = 1;
            for (Cards hand : handsList) {
                hand.getBidValue(rank++);
            }
            for (Cards hand : handsList) {
                hand.getBidValueN(rank++);
            }



            System.out.println(Cards.getStatistics());

        } catch (FileNotFoundException fe) {
            System.out.println("File was not found");
            System.exit(1);
        }
    }
}
