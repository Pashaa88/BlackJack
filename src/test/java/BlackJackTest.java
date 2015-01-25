import org.junit.Test;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class BlackJackTest {

    @Test
    public void StrategyTest( ) throws Exception {

        // Create different dealer with different strategies
        Context contextMrX = new Context( new StrategyCautious( ) );
        Dealer MrX = new Dealer( "Mr. X" , contextMrX );

        Context contextBigJay = new Context( new StrategyBlackJack( ) );
        Dealer BigJay = new Dealer( "Big Jay" , contextBigJay );

        Context contextMissDangerous = new Context( new StrategyTactical( ) );
        Dealer MissDangerous = new Dealer( "Miss Dangerous" , contextMissDangerous );

        // Test the names of dealer
        assertEquals( "Mr. X" , MrX.getName( ) );
        assertEquals( "Big Jay" , BigJay.getName( ) );
        assertEquals( "Miss Dangerous" , MissDangerous.getName( ) );

        // Test the strategy of dealer
        assertEquals( "Cautious Strategy" , contextMrX.getDealerStrategy( ).getStrategyName( ) );
        assertEquals( "BlackJack Strategy" , contextBigJay.getDealerStrategy( ).getStrategyName( ) );
        assertEquals( "Tactical Strategy" , contextMissDangerous.getDealerStrategy( ).getStrategyName( ) );

    }

    @Test
    public void CautiousStrategyTest( ) throws Exception {

        // Create Dealer with cautious strategy and a player
        Context context = new Context( new StrategyCautious( ) );
        Dealer dealer = new Dealer( "Dealer" , context );
        Player player = new Player( "Player" );

        // Check if strategy
        for ( int i = 0 ; i < 1000 ; i++ ) {

            Deck card = new Deck( );
            int cardNumber = 0;

            dealer.takeCard( card.getCard( cardNumber++ ) );
            dealer.takeCard(card.getCard(cardNumber++));

            context.executeStrategy(player, dealer, card, cardNumber);

            assertFalse(dealer.getSum() < 17);

            dealer.clearCards( );
            player.clearCards( );
        }
    }

    @Test
    public void BlackJackStrategyTest( ) throws Exception {

        Context context = new Context( new StrategyBlackJack( ) );
        Dealer dealer = new Dealer( "Dealer" , context );
        Player player = new Player( "Player" );

        for ( int i = 0 ; i < 1000 ; i++ ) {

            Deck card = new Deck( );
            int cardNumber = 0;

            dealer.takeCard( card.getCard( cardNumber++ ) );
            dealer.takeCard( card.getCard( cardNumber++ ) );

            context.executeStrategy( player , dealer , card , cardNumber );

            assertFalse( dealer.getSum( ) < 21 );

            dealer.clearCards( );
            player.clearCards( );
        }
    }

    @Test
    public void TacticalStrategyTest( ) throws Exception {

        Context context = new Context( new StrategyBlackJack( ) );
        Dealer dealer = new Dealer( "Dealer" , context );
        Player player = new Player( "Player" );

        for ( int i = 0 ; i < 1000 ; i++ ) {

            Deck card = new Deck( );
            int cardNumber = 0;

            dealer.takeCard( card.getCard( cardNumber++ ) );
            dealer.takeCard( card.getCard( cardNumber++ ) );
            player.takeCard( card.getCard( cardNumber++ ) );
            player.takeCard( card.getCard( cardNumber++ ) );

            context.executeStrategy( player , dealer , card , cardNumber );

            assertFalse( player.getSum( ) > dealer.getSum( ) && dealer.getSum( ) < 21 );

            dealer.clearCards( );
            player.clearCards( );
        }
    }

    @Test
    public void ObserverTest( ) throws Exception {

        Grabber grabber = new Grabber( );

        Dealer dealer = new Dealer( "Dealer" , new Context( new StrategyBlackJack( ) ) );
        Player player = new Player( "Player" );

        assertEquals( 0 , grabber.getParticipants( ).size( ) );

        grabber.register( dealer );

        assertEquals( 1, grabber.getParticipants( ).size( ) );

        grabber.register( player );

        assertEquals( 2 , grabber.getParticipants( ).size( ) );

        Bettor bettor1 = new Bettor( );
        grabber.register( bettor1 );

        assertEquals( 3 , grabber.getParticipants( ).size( ) );

        Bettor bettor2 = new Bettor( );
        grabber.register( bettor2 );

        assertEquals( 4 , grabber.getParticipants( ).size( ) );

        grabber.unregister( bettor1 );

        assertEquals( 3 , grabber.getParticipants( ).size( ) );

    }



}
