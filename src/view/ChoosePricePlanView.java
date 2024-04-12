package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ChoosePricePlanView implements Observable, View {

    private Observer controller;
    private String plan;

    public ChoosePricePlanView() {
        this.displayText();
    }
    
    public ChoosePricePlanView(Object object) {}

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
        System.out.println("Scegli il piano tariffario da modificare");
    }

    public void choosePlanConfirm(String type) {
        this.plan = type;
        System.out.println("type");
        this.notifyActionPerformed("plan");
    }

    public String getPlan() {
        return plan;
    }

    
}
