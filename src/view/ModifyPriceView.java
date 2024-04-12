package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ModifyPriceView implements Observable,View{
    private Observer controller;
    private double price;

    public ModifyPriceView() {
        this.displayText();
    }
    
    public ModifyPriceView(Object object) {}

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
        System.out.println("Inserisci nuova tariffa");
    }
    
    public void insertPriceConfirm(double price){
        this.price = price;
        this.notifyActionPerformed("price");
    }

    public double getPrice() {
        return price;
    }
    
    
}
