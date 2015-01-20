
public class Dealer extends Participant implements Observer {

    private final String name;

    public Dealer( String name , Context context ) {

        super( name , context );
        this.name = name;

    }

    public void printDealersCard( ) {

        System.out.println( name + ": [" + getCards( ).get( 0 ) + ", X]" );

    }

    @Override
    public void winner( String name ) {

        System.out.println( "Hey Dealer, " + name + " Wins " );

    }
}
