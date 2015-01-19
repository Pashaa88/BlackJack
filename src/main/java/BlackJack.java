import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BlackJack {

    public static void main( String[ ] args ) throws IOException {

        int i = 0;
        int strategie;
        DealerStrategy dealerStrategy = new StrategyCautious( ) ;

        do {

            System.out.println("Bitte wählen Sie eine Strategy aus! (1 - 3)");
            System.out.println("1 - Vorsichtiger Dealer");
            System.out.println("2 - BlackJack Dealer");
            System.out.println("3 - Strategischer Dealer");

            BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( System.in ) );
            strategie = Integer.parseInt(bufferedReader.readLine( ) );

            switch (strategie) {
                case 1:
                    dealerStrategy = new StrategyCautious( );
                    break;
                case 2:
                    dealerStrategy = new StrategyBlackJack( );
                    break;
                case 3:
                    dealerStrategy = new StrategyTactical( );
                    break;
                default:
                    System.err.println("Zahlen von 1 - 3 sollte man eintippen können!");
            }
        } while (strategie > 3);


        Deck card = new Deck( );
        final Dealer dealer = new Dealer( "Dealer" , dealerStrategy );
        final Player player = new Player( "Player" );

        new Bet(dealer);
        new Bet(player);

        dealer.takeCard( card.getCard( i++ ) );
        dealer.takeCard( card.getCard( i++ ) );
        player.takeCard( card.getCard( i++ ) );
        player.takeCard( card.getCard( i++ ) );

        player.printCards( );
        dealer.printDealersCard( );

        System.out.println("Weitere Karte ziehen? Y oder N");

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( System.in ) );
        String ziehen;

        while( ( ziehen = bufferedReader.readLine( ) ) != null ) {
            switch( ziehen.toLowerCase() ) {
                case "y":
                    player.takeCard( i++ );

                    break;
                case "n":
                    while ( dealer.getSum( ) <= 17 ) {
                        dealer.takeCard( i++ );
                    }
                    System.out.println( dealer.getSum( ) );
                    System.out.println( player.getSum( ) );

                    break;
                default:
                    System.err.println("Bist du blöd!");

            }
            player.printCards();
            System.out.println("Weitere Karte ziehen? Y oder N");

        }
        dealer.printCards( );
    }

}
