
public class DealerBuilder {

    private String name;
    private Context context;

    public DealerBuilder( ) {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Dealer create( ) {
        return new Dealer( name , context );
    }
}
