package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class SignInView implements Observable,View{
    private Observer controller;
    private String username;
    private String password;
    
    public SignInView() {
        this.displayText();
        this.displayButton("Conferma");
        this.displayButton("Torna indietro");
    }
    
    public SignInView(Object o) {}
    
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
        controller.retriveData(this,action);   
    }
    
    @Override
    public void displayText(){
        System.out.println("Inserisci le tue credenziali");
    }
    
    public void submitButtonClicked(String username,String password){
        this.setUsername(username);
        this.setPassword(password);
        notifyActionPerformed("submitData");
    }
    
    public void goBackButtonClicked(){
        notifyActionPerformed("goback");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

}
