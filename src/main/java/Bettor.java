
public class Bettor implements Observer {

    @Override
    public void winner( String name ) {

        System.out.println( "Hey Bettor, " + name + " Wins " );

    }

}
