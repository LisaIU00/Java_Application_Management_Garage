package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ShowParkingAreaView implements Observable,View{
    private Observer controller;
    private String parkingArea;

    public ShowParkingAreaView(String parkingArea) {
        this.displayText();
        this.parkingArea = parkingArea;
    }

    public ShowParkingAreaView(Object object) {}

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
        System.out.println("Il tuo posto è al numero:");
    }
    
    public void showParkingConfirm(){
        System.out.println(parkingArea);
        this.notifyActionPerformed("showParking");
    }
    
}
