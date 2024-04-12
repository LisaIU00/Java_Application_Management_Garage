package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class InsertLicencePlateView implements Observable,View{
    private Observer controller;
    private String plate;

    public InsertLicencePlateView() {
        this.displayText();
    }

    public InsertLicencePlateView(Object obj){}
    
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
        System.out.println("Inserisci targa");
    }
    
    public void insertPlateConfirm(String plate){
        System.out.println(plate);
        this.plate = plate;
        this.notifyActionPerformed("plate");
    }

    public String getPlate() {
        return plate;
    }
    
    
    
}

