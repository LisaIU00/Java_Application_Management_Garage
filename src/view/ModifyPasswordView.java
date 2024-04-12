package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ModifyPasswordView implements Observable,View{
    private Observer controller;
    private String psw;

    public ModifyPasswordView() {
        this.displayText();
    }
    
    public ModifyPasswordView(Object object) {}

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
        System.out.println("Inserisci nuova password");
    }
    
    public void modifyPswConfirm(String psw){
        this.psw = psw;
        this.notifyActionPerformed("psw");
    }

    public String getPsw() {
        return psw;
    }
    
    
}