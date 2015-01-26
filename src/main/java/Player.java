
public class Player extends Participant implements Observer {

    public Player( String name ) {
        super( name );

    }

    @Override
    public void winner( String winnerName ) {

        System.out.println( "Hey Player, " + winnerName + " Wins ");

    }
}
