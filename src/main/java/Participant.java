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

    public void changeAss( Participant participant ) {

        if ( participant.cards.contains( 11 ) == true ) {

            participant.cards.set( participant.cards.indexOf( 11 ) , 1 );
            participant.printCards( );
        }
    }

    public String checkCards( Participant participant ) {

        if ( this.getSum( ) > 21 ) {
            changeAss( this );
        }

        if ( participant.getSum( ) > 21 ) {
            changeAss( participant );
        }

        if ( this.getSum() > 21 && participant.getSum() < 21 ) {
            this.printCards();
            return participant.getName();
        } else if ( this.getSum() < 21 && participant.getSum() > 21 ) {
            this.printCards( );
            return this.getName();
        } else if ( this.getSum( ) == 21 && participant.getSum( ) != 21 ) {
            this.printCards( );
            return this.getName( );
        } else if ( participant.getSum( ) == 21 && this.getSum( ) != 21 ) {
            this.printCards( );
            return participant.getName( );
        } else if ( participant.getSum( ) == 21 && this.getSum( ) == 21 ) {
            this.printCards( );
            return "No one";
        }

        return "";
    }

    public String checkResult( Participant participant ) {

        if ( this.getSum( ) > 21 ) {

            return participant.getName( );
        } else if ( this.getSum( ) == 21 ) {

            return this.getName( );
        } else if ( this.getSum() < 21 ) {

            if ( participant.getSum( ) > this.getSum( ) ) {

                return participant.getName( );
            } else if ( participant.getSum( ) < this.getSum( ) ) {

                return this.getName( );
            } else if ( participant.getSum( ) == this.getSum( ) ) {

                return "No one";
            }
        }

        return "No one";
    }
}
