package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ShowAmountView implements Observable,View{
    private Observer controller;
    private double amount;

    public ShowAmountView(double amount) {
        this.displayText();
        this.amount = amount;
    }
    
    public ShowAmountView(Object object) {}

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
        System.out.println("L'importo da pagare è il seguente:");
    }
    
    public void showAmountConfirm(){
        this.amount = amount;
        System.out.println(amount+" €");
        this.notifyActionPerformed("amount");
    }
    
}
