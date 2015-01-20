
public class Player extends Participant implements Observer {

    public Player( String name ) {
        super( name );

    }

    @Override
    public void winner( String name ) {

        System.out.println( name + " Wins ");

    }
}
