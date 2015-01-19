
public class Player extends Participant implements Observer {

    public Player( String name ) {
        super( name );

    }

    @Override
    public void winner(int state) {

        if ( state == 0 ) {
            System.out.println( " Player Win ");
        } else if ( state == 1 ) {
            System.out.println( " Player Lose " );
        } else {
            System.out.println( " Draw " );
        }
    }
}
