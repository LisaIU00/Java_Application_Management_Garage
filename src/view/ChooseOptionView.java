package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ChooseOptionView implements Observable,View{
    private Observer controller;
    private boolean annual;

    public ChooseOptionView() {
        this.displayButton("Crea abbonamento");
        this.displayButton("Accedi al garage");
    }
    
    public ChooseOptionView(Object object) {}

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
    
    public void createSubmitClicked(){
        this.notifyActionPerformed("submit");
    }
    
    public void enterGarageClicked(){
        this.notifyActionPerformed("enterGarage");
    }

}
