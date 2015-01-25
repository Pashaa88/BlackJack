import java.util.*;

public class Deck {

    private final Integer[ ] deck;

    public Deck() {

        deck = new Integer[ ] {
                2 , 2 , 2 , 2 ,
                3 , 3 , 3 , 3 ,
                4 , 4 , 4 , 4 ,
                5 , 5 , 5 , 5 ,
                6 , 6 , 6 , 6 ,
                7 , 7 , 7 , 7 ,
                8 , 8 , 8 , 8 ,
                9 , 9 , 9 , 9 ,
                10 , 10 , 10 , 10 ,
                10 , 10 , 10 , 10 ,
                10 , 10 , 10 , 10 ,
                10 , 10 , 10 , 10 ,
                11 , 11 , 11 , 11
        };

        Collections.shuffle( Arrays.asList( deck ) );

    }

    public int getCard( int cardNumber ) {

        return deck[ cardNumber ];
    }


}
