import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BlackJack {

    public static void main( String[ ] args ) throws IOException {

        int i = 0;
        int strategie;
        Context context = new Context( new StrategyCautious( ) );
        Grabber subject = new Grabber( );
        String decision;

        do {

            System.out.println("Bitte wählen Sie eine Strategy aus! (1 - 3)");
            System.out.println("1 - Vorsichtiger Dealer");
            System.out.println("2 - BlackJack Dealer");
            System.out.println("3 - Strategischer Dealer");

            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( System.in ) );
            strategie = Integer.parseInt(bufferedReader.readLine( ) );

            switch (strategie) {
                case 1:
                    context = new Context( new StrategyCautious( ) );
                    break;
                case 2:
                    context = new Context( new StrategyBlackJack( ) );
                    break;
                case 3:
                    context = new Context( new StrategyTactical( ) );
                    break;
                default:
                    System.err.println("Zahlen von 1 - 3 sollte man eintippen können!");
            }
        } while (strategie > 3);

        Deck card = new Deck( );
        final Dealer dealer = new Dealer( "Dealer" , context );
        final Player player = new Player( "Player" );
        final Bettor bettor = new Bettor( );

        subject.register( dealer );
        subject.register( player );
        subject.register( bettor );

        dealer.takeCard( card.getCard( i++ ) );
        dealer.takeCard( card.getCard( i++ ) );
        player.takeCard( card.getCard( i++ ) );
        player.takeCard( card.getCard( i++ ) );

        player.printCards( );
        dealer.printDealersCard( );

        decision = dealer.checkCards( player );

        if ( decision != "" ) {
            subject.setState( decision );
            return;
        }

        System.out.println( "Weitere Karte ziehen? Y oder N" );

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( System.in ) );
        String ziehen;

        while( ( ziehen = bufferedReader.readLine( ) ) != null ) {

            switch( ziehen.toLowerCase() ) {
                case "y":
                    player.takeCard( card.getCard( i++ ) );

                    break;
                case "n":

                    subject.setState( context.executeStrategy( player , dealer , card , i ) );

                    return;

                default:
                    System.err.println("Bist du blöd!");
            }
            System.out.println( "Weitere Karte ziehen? Y oder N" );

            player.printCards( );
            decision = dealer.checkCards( player );

            if ( decision != "" ) {
                subject.setState( decision );
                return;
            }
        }
    }
}
