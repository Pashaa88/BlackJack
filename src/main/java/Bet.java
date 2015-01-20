
public class Bet implements Observer {

    @Override
    public void winner( String name ) {

    }

    /*private String wer;

    public Bet( Subject subject ){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update( ) {
        if(arg0 instanceof Dealer){
            Dealer obsObj = (Dealer) arg0;
            this.wer = obsObj.getName();
            anzeigen();
        }
    }

    public void anzeigen(){
        System.out.println("Bet: Yuppi, " + wer + " hat gewonnen" );
    }   */
}
