import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grabber implements Subject {

    private List<Observer> observers = new ArrayList<Observer>( );
    private String name;

    public List<Observer> getParticipants( ) {
        return Collections.unmodifiableList(observers);
    }

    public void setState( String name ) {

        this.name = name;
        notifyObserver( );
    }

    public String getName( ) {

        return name;
    }

    @Override
    public void register( Observer o ) {

        observers.add( o );
    }

    @Override
    public void unregister( Observer o ) {

        int observerIndex = observers.indexOf( o );
        observers.remove( observerIndex );
    }

    @Override
    public void notifyObserver( ) {

         for ( Observer observer : observers ) {
             observer.winner( getName( ) );
         }
    }
}
