import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class Participant extends Observable {

    private final String name;
    private final List<Integer> cards = new ArrayList<Integer>( );
    private volatile Context context;


    public Participant( String name ) {

        this.name = name;

    }

    public Participant( String name , Context context ) {

        this.name = name;
        this.context = context;
    }

    public String getName( ) {

        return name;
    }

    public List<Integer> getCards( ) {

        return cards;
    }

    public void takeCard( int cards ) {

        this.cards.add( cards );

    }

    public void getStatus( ) {

    }

    public int getSum( ) {

        int sum = 0;

        for ( Integer card : cards ) {
            sum = sum + card;
        }

        return sum;
    }

    public void printCards( ) {

        System.out.println( name + ": " + Collections.unmodifiableList(cards) + " - in Summe " + getSum( ) );
    }

    public void printDealersCard( ) {

        System.out.println( name + ": [" + cards.get( 0 ) + ", X]" );

    }

    public void checkCards( int playerSum , int dealerSum ) {

        if ( dealerSum > 21 && playerSum < 21 ) {

        }
        else if ( dealerSum < 21 && playerSum > 21 ) {

        }
        else if ( dealerSum == 21 && playerSum == 21 ) {

        }
    }

    public String checkResult( int playerSum , int dealerSum ) {

        if ( dealerSum > playerSum ) {

            return name;

        }
        else  {

        }

        return name;
    }
}
