import org.junit.Test;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;

public class BlackJackTest {

    @Test
    public void DealerStrategyTest( ) throws Exception {

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

        // Check if dealer play with minimum 17
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

        // Create dealer with blackjack strategy and a player
        Context context = new Context( new StrategyBlackJack( ) );
        Dealer dealer = new Dealer( "Dealer" , context );
        Player player = new Player( "Player" );

        // Check if dealer play with minimum 21
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

        // Create dealer with tactical strategy and a player
        Context context = new Context( new StrategyTactical( ) );
        Dealer dealer = new Dealer( "Dealer" , context );
        Player player = new Player( "Player" );

        // Check if dealer has more then player and less then 21
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
    public void ObserverRegisteredTest( ) throws Exception {

        // Check if length of arraylist is correct with register and unregister player, dealer, bettor
        Grabber grabber = new Grabber( );
        Dealer dealer = new Dealer( "Dealer" , new Context( new StrategyBlackJack( ) ) );
        Player player = new Player( "Player" );

        assertEquals( 0 , grabber.getParticipants( ).size( ) );

        grabber.register( dealer );

        assertEquals( 1, grabber.getParticipants( ).size( ) );
        assertEquals( "Dealer" , grabber.getParticipants( ).get( 0 ).getName( ) );

        grabber.register( player );

        assertEquals(2, grabber.getParticipants().size());
        assertEquals("Dealer", grabber.getParticipants().get(0).getName());
        assertEquals("Player", grabber.getParticipants().get(1).getName());

        Bettor bettor1 = new Bettor( "Bettor1" );
        grabber.register(bettor1);

        assertEquals(3, grabber.getParticipants().size());
        assertEquals("Dealer", grabber.getParticipants().get(0).getName());
        assertEquals("Player", grabber.getParticipants().get(1).getName());
        assertEquals("Bettor1", grabber.getParticipants().get(2).getName());

        Bettor bettor2 = new Bettor( "Bettor2" );
        grabber.register( bettor2 );

        assertEquals( 4 , grabber.getParticipants( ).size( ) );
        assertEquals( "Dealer" , grabber.getParticipants( ).get( 0 ).getName( ) );
        assertEquals( "Player" , grabber.getParticipants( ).get( 1 ).getName( ) );
        assertEquals( "Bettor1" , grabber.getParticipants( ).get( 2 ).getName( ) );
        assertEquals( "Bettor2" , grabber.getParticipants( ).get( 3 ).getName() );

        grabber.unregister( bettor1 );

        assertEquals( 3 , grabber.getParticipants().size() );
        assertEquals( "Dealer" , grabber.getParticipants( ).get( 0 ).getName( ) );
        assertEquals( "Player" , grabber.getParticipants( ).get( 1 ).getName( ) );
        assertEquals( "Bettor2" , grabber.getParticipants( ).get( 2 ).getName( ) );
    }

    @Test
    public void ObserverWinnerTest( ) throws Exception {

        // Check if the Observer inform the correct winner to Listener
        Context context = new Context( new StrategyCautious( ) );
        Dealer dealer = new Dealer( "Dealer" , context );
        Player player = new Player( "Player" );
        Bettor bettor = new Bettor( "Bettor" );
        Deck card = new Deck( );
        Grabber grabber = new Grabber( );
        int cardNumber = 0;

        grabber.register( dealer );
        grabber.register( player );
        grabber.register( bettor );

        dealer.takeCard( card.getCard( cardNumber++ ) );
        dealer.takeCard( card.getCard( cardNumber++ ) );
        player.takeCard( card.getCard( cardNumber++ ) );
        player.takeCard( card.getCard( cardNumber++ ) );

        context.executeStrategy( player , dealer , card , cardNumber );

        grabber.setState( dealer.checkResult( player ) );

        dealer.printCards( );
        player.printCards( );

        if ( ( player.getSum( ) < 22 && player.getSum( ) > dealer.getSum( ) ) || dealer.getSum( ) > 21 ) {

            assertEquals( "Player" , grabber.getName( ) );
        } else if ( player.getSum( ) == dealer.getSum( ) ) {

            assertEquals( "No one" , grabber.getName( ) );
        } else {

            assertEquals( "Dealer" , grabber.getName( ) );
        }
    }

    @Test
    public void DealerBuilderTest( ) throws Exception {

        // Check if builder has the last given name and strategy in dealer
        Context context = new Context( new StrategyTactical( ) );
        DealerBuilder dealerBuilder = new DealerBuilder( );
        dealerBuilder.setName( "Max" );
        dealerBuilder.setName( "Moritz" );
        dealerBuilder.setContext( context );
        dealerBuilder.setName("Michael");
        context = new Context( new StrategyBlackJack( ) );
        dealerBuilder.setContext( context );

        Dealer dealer = dealerBuilder.create( );

        assertEquals( "Michael" , dealer.getName( ) );
        assertEquals( "BlackJack Strategy" , context.getDealerStrategy( ).getStrategyName( ) );

    }

    @Test
    public void DeckCardTest( ) throws Exception {

        // Check if taken card is correct card of deck
        Player player = new Player( "Player" );
        Deck card = new Deck( );

        int cardNumber = 0;

        for ( int i = 0 ; i < card.getDeck( ).length ; i++ ) {

            player.takeCard( card.getCard( cardNumber++ ) );

            assertEquals( (long)card.getCard( i ) , (long)player.getCard( ).get( i ) );
        }

        assertEquals( 52 , player.getCards( ).size( ) );
        assertEquals( 380 , player.getSum( ) );


    }
}
