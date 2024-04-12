package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class GarageMainView implements Observable,View{
    private Observer controller;

    public GarageMainView() {
        this.displayText();
        this.displayButton("Entra");
        this.displayButton("Esci");
        this.displayButton("Login");
    }
    
    public GarageMainView(Object obj){}

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
        System.out.println("Benvenuti nel garage di GiuLi");
    }
    
    public void signInButtonClicked(){
        this.notifyActionPerformed("signin");
    }
    
    public void enterButtonClicked(){
        this.notifyActionPerformed("enter");
    }
    
    public void exitButtonClicked(){
        this.notifyActionPerformed("exit");
    }
    
}
