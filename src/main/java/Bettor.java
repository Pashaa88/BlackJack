
public class Bettor implements Observer {

    final public String name;

    public Bettor( String name ) {
        this.name = name;
    }

    @Override
    public String getName( ) {

        return this.name;
    }

    @Override
    public void winner( String winnerName ) {

        System.out.println( "Hey Bettor, " + winnerName + " Wins " );
    }

}
