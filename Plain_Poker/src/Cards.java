import java.util.*;

public class Cards implements Comparable<Cards> {
    private int[] cardNumbers;
    private int bid;
    private HashMap<Integer, Integer> frequencyMap;

    private static int totalBidValue = 0;
    private static int totalBidValueJ = 0;
    private static int fiveOfAKindCount = 0;
    private static int fourOfAKindCount = 0;
    private static int fullHouseCount = 0;
    private static int threeOfAKindCount = 0;
    private static int twoPairCount = 0;
    private static int onePairCount = 0;
    private static int highCardCount = 0;


    private static final int JACK = 11; // Jack is treated as wild

    public Cards(int[] cards, int bid) {
        this.cardNumbers = cards;
        this.bid = bid;
        this.frequencyMap = new HashMap<>();

        for (int num : cardNumbers) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Regular classification
        classifyHandNormal();
    }


    private void classifyHandNormal() {
        int pairCount = 0;
        boolean hasThree = false;

        for (int count : frequencyMap.values()) {
            if (count == 5) {
                fiveOfAKindCount++;
                return;
            } else if (count == 4) {
                fourOfAKindCount++;
                return;
            } else if (count == 3) {
                hasThree = true;
            } else if (count == 2) {
                pairCount++;
            }
        }

        if (hasThree && pairCount == 1) {
            fullHouseCount++;
        } else if (hasThree) {
            threeOfAKindCount++;
        } else if (pairCount == 2) {
            twoPairCount++;
        } else if (pairCount == 1) {
            onePairCount++;
        } else {
            highCardCount++;
        }
    }


    public int getHandRankJ(boolean jacksWild) {
        HashMap<Integer, Integer> freqMap = new HashMap<>(frequencyMap);
        int jackCount = freqMap.getOrDefault(JACK, 0);
        if (jacksWild) freqMap.remove(JACK);

        int maxCount = 0;
        for (int count : freqMap.values()) {
            maxCount = Math.max(maxCount, count);
        }

        if (jacksWild) maxCount += jackCount;

        if (maxCount == 5) return 7;
        if (maxCount == 4) return 6;
        if (Collections.frequency(freqMap.values(), 3) == 1 && Collections.frequency(freqMap.values(), 2) == 1) return 5;
        if (maxCount == 3) return 4;
        if (Collections.frequency(freqMap.values(), 2) == 2) return 3;
        if (maxCount == 2) return 2;
        return 1;
    }

    public int compareTo(Cards other) {
        int rankComparison = Integer.compare(this.getHandRankJ(true), other.getHandRankJ(true));
        if (rankComparison != 0) {
            return rankComparison;
        }

        for (int i = 0; i < this.cardNumbers.length; i++) {
            int thisCard = this.cardNumbers[i] == JACK ? -1 : this.cardNumbers[i];
            int otherCard = other.cardNumbers[i] == JACK ? -1 : other.cardNumbers[i];
            int cardComparison = Integer.compare(thisCard, otherCard);
            if (cardComparison != 0) {
                return cardComparison;
            }
        }
        return 0;
    }


    public void getBidValue(int rank) {
        int bidValue = this.bid * rank;
        totalBidValueJ += bidValue;
    }

    public void getBidValueN(int rank) {
        int bidValue = this.bid * rank;
        totalBidValue += bidValue;
    }


    public static String getStatistics() {
        return "Number of five of a kind hands: " + fiveOfAKindCount + "\n" +
                "Number of full house hands: " + fullHouseCount + "\n" +
                "Number of four of a kind hands: " + fourOfAKindCount + "\n" +
                "Number of three of a kind hands: " + threeOfAKindCount + "\n" +
                "Number of two pair hands: " + twoPairCount + "\n" +
                "Number of one pair hands: " + onePairCount + "\n" +
                "Number of high card hands: " + highCardCount + "\n" +
                "Total Bid Value: " + totalBidValue + "\n" +
                "Total Bid Value With Jacks Wild: " + totalBidValueJ;
    }
}
