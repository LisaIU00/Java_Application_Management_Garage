package view;

import support.Observable;
import support.Observer;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ChooseSpecialVehicleView implements Observable, View {

    private Observer controller;
    private String category;

    public ChooseSpecialVehicleView() {
        this.displayText();
    }
    
    public ChooseSpecialVehicleView(Object object) {}

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
        System.out.println("Scegli il sottotipo veicolo");
    }

    public void insertSubcategoryConfirm(String type) {
        this.category = selectType(type);
        this.notifyActionPerformed("subcategory");
    }

    private String selectType(String type) {
        switch (type) {
            case "disabile":
                return "D";
            case "donna incinta":
                return "I";
            case "elettrica":
                return "E";
            default: return "N";
        }
    }

    public String getCategory() {
        return category;
    }
    
    
}
