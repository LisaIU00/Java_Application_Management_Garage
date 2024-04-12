package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ErrorDialogView implements Observable,View{
    private Observer controller;

    public ErrorDialogView(Object object) {}

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
    
    public void wrongPlate(){
        System.out.println("ERRORE: LA TARGA INSERITA NON E' CORRETTA");
        this.notifyActionPerformed("wrongplate");
    }
    
    public void errorPlate(){
        System.out.println("ERRORE: IL VEICOLO E' GIA' PRESENTE NEL GARAGE");
        this.notifyActionPerformed("errorPlate");
    }
    
    public void subscriberPlate(){
        System.out.println("BENTORNATO, LA TUA TARGA E' ASSOCIATA AD UN ABBONAMENTO ATTIVO");
        this.notifyActionPerformed("subscriberPlate");
    }
    
    public void fullParking(){
        System.out.println("ERRORE: I POSTI NELLA CATEGORIA RICHIESTA SONO TERMINATI");
        this.notifyActionPerformed("fullParking");
    }
    
    public void runOutSubscription(){
        System.out.println("ERRORE: GLI ABBONAMENTI DISPONIBILI SONO TERMINATI");
        this.notifyActionPerformed("runOutSubscription");
    }
    
    public void numParking(){
        System.out.println("ERRORE: IL NUMERO DI POSTI INSERITO NON E' CORRETTO");
        this.notifyActionPerformed("numParking");
    }
    
    public void errorUsername(){
        System.out.println("ERRORE: IL NUOVO USERNAME INSERITO NON E' CORRETTO");
        this.notifyActionPerformed("errorUsername");
    }
    
    public void errorPsw(){
        System.out.println("ERRORE: LA NUOVA PASSWORD INSERITA NON E' CORRETTA");
        this.notifyActionPerformed("errorPsw");
    }
    
    public void errorPrice(){
        System.out.println("ERRORE: LA NUOVA TARIFFA INSERITA NON E' CORRETTA");
        this.notifyActionPerformed("errorPrice");
    }
    
    public void errorSignIn(){
        System.out.println("ERRORE: LE CREDENZIALI INSERITE NON SONO CORRETTE");
        this.notifyActionPerformed("errorSignIn");
    }
    
    public void errorSetMax(){
        System.out.println("ERRORE: IL NUMERO MINIMO DI POSTEGGI DEVE ESSERE MAGGIORE O UGUALE AL NUMERO DI CATEGORIE");
        this.notifyActionPerformed("errorSetMax");
    }
    
    public void notCorresponding(){
        System.out.println("IL NUMERO MASSIMO DI POSTEGGI DEVE CORRISPONDERE ALLA SOMMA DEI POSTEGGI PER CATEGORIA");
        this.notifyActionPerformed("notCorresponding");
    }
    
}

