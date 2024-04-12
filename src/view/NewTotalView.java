package view;
import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class NewTotalView implements Observable,View{
    private Observer controller;
    private int max;

    public int getMax() {
        return max;
    }

    public NewTotalView() {
        this.displayText();
    }
    
    public NewTotalView(Object object) {}

    @Override
    public void addController(Observer channel) {
       this.controller = channel;
    }
    
    @Override
    public void removeController() {
        this.controller = null;
    }

    @Override
    public void notifyActionPerformed(String action) {
        controller.retriveData(this, action);
    }

    @Override
    public void displayText() {
        System.out.println("Inserisci il nuovo numero totale di parcheggi");
    }
    
    public void maxConfirm(int max){
        this.max = max;
        this.notifyActionPerformed("newtotal");
    }
    
}
