package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ChooseSubscriptionView implements Observable,View{
    private Observer controller;
    private boolean annual;

    public ChooseSubscriptionView() {
        this.displayText();
        this.displayButton("Annuale");
        this.displayButton("Mensile");
    }
    
    public ChooseSubscriptionView(Object object) {}

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
        System.out.println("Scegli il tipo di abbonamento");
    }
    
    public void chooseSubscriptionConfirm(String type){
        annual = false;
        if(type.equals("annual")){
            annual = true;
        }
        this.notifyActionPerformed("typeSubmit");
    }

    public boolean isAnnual() {
        return annual;
    }
    
}
