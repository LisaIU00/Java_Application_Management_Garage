package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ModifyParametersView implements Observable,View{
    private Observer controller;

    public ModifyParametersView() {
        this.displayButton("Modifica password");
        this.displayButton("Modifica username");
        this.displayButton("Torna indietro");
    }
    
    public ModifyParametersView(Object object) {}

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
    
    public void pswButtonClicked(){
        this.notifyActionPerformed("psw");
    }
    
    public void userButtonClicked(){
        this.notifyActionPerformed("user");
    }
    
    public void goBackButtonClicked(){
        this.notifyActionPerformed("goback");
    }
    
}
