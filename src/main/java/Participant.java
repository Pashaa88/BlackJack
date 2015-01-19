import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class Participant extends Observable {

    private final String name;
    private final List<Integer> cards = new ArrayList<Integer>( );
    private volatile DealerStrategy dealerStrategy;

    public Participant( String name ) {

        this.name = name;
    }

    public Participant( String name , DealerStrategy dealerStrategy ) {

        this.name = name;
        this.dealerStrategy = dealerStrategy;
    }

    public String getName( ) {

        return name;
    }

    public void takeCard( int cards ) {

        this.cards.add( cards );
        checkCards( );
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

        System.out.println( name + ": [" + cards.get( 1 ) + ", X]" );

    }

    public void checkCards( ) {

        if (getSum( ) > 21 ) {
            System.out.println( name + " Busted!");
            inform();
        }
        else if ( getSum( ) == 21 ) {
            System.out.println( name + " Black Jack");
            inform();
        }
    }

    private void inform( ){

        setChanged();
        notifyObservers(name);
    }
}
