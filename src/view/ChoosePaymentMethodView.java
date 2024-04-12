package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ChoosePaymentMethodView implements Observable,View{
    private Observer controller;

    public ChoosePaymentMethodView() {
        this.displayText();
        this.displayButton("Contante");
        this.displayButton("Carta");
    }
    public ChoosePaymentMethodView(Object object) {}

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
        System.out.println("Scegli il metodo di pagamento");
    }
    
    public void paymentMethodClicked(){
        this.notifyActionPerformed("paymentMethod");
    }
    
}
