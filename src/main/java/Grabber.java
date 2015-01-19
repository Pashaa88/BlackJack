import java.util.ArrayList;
import java.util.List;

public class Grabber implements Subject {

    private List<Observer> observers = new ArrayList<Observer>( );
    private int state;

    public void setState( int state ) {

        this.state = state;
        notifyObserver( );
    }

    public int getState( ) {

        return state;
    }

    @Override
    public void register(Observer o) {

        observers.add( o );
    }

    @Override
    public void unregister( Observer o ) {

        int observerIndex = observers.indexOf( o );
        observers.remove( observerIndex );

    }

    @Override
    public void notifyObserver() {

         for ( Observer observer : observers ) {
             observer.winner( state );
         }

    }
}
