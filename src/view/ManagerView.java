package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ManagerView implements Observable,View{
    private Observer controller;

    public ManagerView() {
        this.displayButton("Modifica piano tariffario");
        this.displayButton("Modica garage");
        this.displayButton("Modifica credenziali");
        this.displayButton("Esci");
    }
    
    public ManagerView(Object object) {}

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
    public void displayText(){}    
    
    public void modifyPriceButtonClicked(){
        this.notifyActionPerformed("modifyPrice");
    }
    
    public void modifyGarageButtonClicked(){
        this.notifyActionPerformed("modifyGarage");
    }
    public void modifyParametersButtonClicked(){
        this.notifyActionPerformed("modifyParameters");
    }
    public void exitButtonClicked(){
        this.notifyActionPerformed("exit");
    }
}
