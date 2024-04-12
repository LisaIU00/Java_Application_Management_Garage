package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ModifyGarageView implements Observable,View{
    private Observer controller;

    public ModifyGarageView() {
        this.displayButton("Riorganizza");
        this.displayButton("Ristruttura");
    }
    
    public ModifyGarageView(Object object) {}

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
    public void displayText() {}
    
    public void reorganizeButtonClicked(){
        this.notifyActionPerformed("riorganizza");
    }
    
    public void restructureButtonClicked(){
        this.notifyActionPerformed("ristruttura");
    }
    
}
