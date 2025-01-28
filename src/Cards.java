public class Cards {
    int total;
    private int[] cardNumbers;

    public Cards(int[] cards){
        this.cardNumbers = cards;
        for(int i : cardNumbers){
            total += cardNumbers[i];
        }
    }

    public int numOfFiveKind() {
        if (total / cardNumbers[1] == 5) {
            return 1;
        }
        else{
            return 0;
        }
    }

    public int numOfFourKind(){
        int temp1 = cardNumbers[1];
        int temp2 = cardNumbers[2];
        int counter2 = 0;
        int counter1 = 0;
        for(int i : cardNumbers){
            if (cardNumbers[i] != temp1){
                temp1 = cardNumbers[i];
            }
            else {
                counter1 ++;
            }
        }
        for(int i : cardNumbers){
            if (cardNumbers[i] != temp2){
                temp2 = cardNumbers[i];
            }
            else {
                counter2 ++;
            }
        }
        if ((counter1 == 4) || (counter2 == 4)){
            return 1;
        }
        else{
            return 0;
        }
    }


}
