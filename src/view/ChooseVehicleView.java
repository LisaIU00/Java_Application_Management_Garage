package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ChooseVehicleView implements Observable, View {

    private Observer controller;
    private String category;

    public ChooseVehicleView() {
        this.displayText();
    }
    
    public ChooseVehicleView(Object object) {}

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
        System.out.println("Scegli il tipo veicolo");
    }

    public void insertCategoryConfirm(String type) {
        this.category = selectType(type);
        System.out.println(type);
        this.notifyActionPerformed("category");
    }

    private String selectType(String type) {
        switch (type) {
            case "auto mini":
                return "MNC";
            case "auto luxury":
                return "LC";
            case "auto media":
                return "MDC";
            case "auto grande":
                return "MXC";
            case "maxi":
                return "MX";
            case "moto":
                return "MT";
        }
        return null;
    }

    public String getCategory() {
        return category;
    }
    
    
}
