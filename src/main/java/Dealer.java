
public class Dealer extends Participant implements Observer {

    private final String name;

    public Dealer( String name , DealerStrategy dealerStrategy ) {

        super( name , dealerStrategy );
        this.name = name;

    }

    public void printDealersCard( ) {

        System.out.println( name + ": [" + getCards( ).get( 0 ) + ", X]" );

    }

    @Override
    public void winner( String name ) {

        System.out.println( name + " Wins " );

    }
}
