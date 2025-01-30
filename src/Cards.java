public class Cards {
    double total;
    private int[] cardNumbers;

    public Cards(int[] cards){
        this.cardNumbers = cards;
        for(int i : cardNumbers){
            total += i;
        }
    }

    public int numOfFiveKind() {
        int counter = 0;
       int [] temp = {cardNumbers[1],cardNumbers[1],cardNumbers[1],cardNumbers[1],cardNumbers[1]};
        for (int i = 0; i < cardNumbers.length; i++){
            if(temp[i] == cardNumbers[i]){
                counter++;
            }
        }
        if (counter == 5){
            return 1;
        }
        else{
            return 0;
        }
    }


    public int numOfFourKind(){
        int counter = 0;
        int [] temp = {cardNumbers[1],cardNumbers[1],cardNumbers[1],cardNumbers[1],cardNumbers[1]};
        for (int i = 0; i < cardNumbers.length; i++){
            if(temp[i] == cardNumbers[i]){
                counter++;
            }
        }
        if (counter == 5){
            return 1;
        }
        else{
            return 0;
        }
    }
    }


}
