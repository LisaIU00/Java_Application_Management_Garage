package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ModifyUsernameView implements Observable,View{
    private Observer controller;
    private String user;

    public ModifyUsernameView() {
        this.displayText();
    }
    
    public ModifyUsernameView(Object object) {}

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
        System.out.println("Inserisci nuovo username");
    }
    
    public void modifyUserConfirm(String user){
        this.user = user;
        this.notifyActionPerformed("user");
    }

    public String getUser() {
        return user;
    }
    
    
}
