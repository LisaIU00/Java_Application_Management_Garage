package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ChooseNumParkingView implements Observable,View{
    private Observer controller;
    private int num;

    public int getNum() {
        return num;
    }

    public ChooseNumParkingView() {
        this.displayText();
    }
    
    public ChooseNumParkingView(Object object) {}

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
        System.out.println("Inserisci il nuovo numero totale di parcheggi associati alla categoria scelta");
    }
    
    public void insertNumConfirm(int num){
        this.num = num;
        this.notifyActionPerformed("newnumparking");
    }
    
}
