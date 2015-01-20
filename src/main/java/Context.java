
public class Context {

    private DealerStrategy dealerStrategy;

    public Context( DealerStrategy dealerStrategy ) {

        this.dealerStrategy = dealerStrategy;
    }

    public int executeStrategy( ) {
        return dealerStrategy.takeCard( );
    }

}
