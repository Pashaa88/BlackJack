
public class Context {

    DealerStrategy dealerStrategy;

    public Context( DealerStrategy dealerStrategy ) {

        this.dealerStrategy = dealerStrategy;
    }

    public String executeStrategy( Player player , Dealer dealer , Deck card , int i) {
        return dealerStrategy.takeDealerCard( player , dealer , card , i );
    }

}
