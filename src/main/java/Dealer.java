import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {

    private final List<Integer> cards = new ArrayList<Integer>( );
    private final String name;
    //private volatile DealerStrategy dealerStrategy;

    public Dealer( String name , DealerStrategy dealerStrategy ) {

        super( name , dealerStrategy );
        this.name = name;
        //this.dealerStrategy = dealerStrategy;
    }

    /*private void inform( ){
        setChanged();
        notifyObservers(name);
    }

    public String getName(){
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

        System.out.println( name + ": " + Collections.unmodifiableList( cards ) + " - in Summe " + getSum( ) );

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

    }  */

}
