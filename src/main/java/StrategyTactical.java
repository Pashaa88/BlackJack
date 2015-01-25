
public class StrategyTactical implements DealerStrategy {

    @Override
    public String takeDealerCard( Player player, Dealer dealer , Deck card , int i ) {

        while ( player.getSum( ) > dealer.getSum( ) && dealer.getSum( ) < 21 ) {

            dealer.takeCard( card.getCard( i++ ) );
            if ( dealer.getSum( ) > 21 ) {
                dealer.changeAss( dealer );
            }
        }

        player.printCards( );
        dealer.printCards( );

        return dealer.checkResult( player );

    }
}
