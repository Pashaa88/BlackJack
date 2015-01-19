
import java.util.Observable;
import java.util.Observer;

public class Bet implements Observer {
    Observable observable;
    private String text;
    private String wer;


    public Bet(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        if(arg0 instanceof Dealer){
            Dealer obsObj = (Dealer) arg0;
            this.wer = obsObj.getName();
            anzeigen();
        }
    }
    public void anzeigen(){
        System.out.println("Bet: Yuppi, " + wer + " hat gewonnen" );
    }
}
