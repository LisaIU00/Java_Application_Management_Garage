package model.vehicle;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */



public class Vehicle {

    private String plate;
    private Type type;
    public static enum Type{ LC, MXC, MDC, MNC, MX, MT} 
    
    public Vehicle (String plate, Type type){
        this.plate = plate; 
        this.type = type;
    }
    
    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getType() {
        if (type.equals(Type.LC)) {
            return "LC";
        }
        if (type.equals(Type.MXC)) {
            return "MXC";
        }
        if (type.equals(Type.MDC)) {
            return "MDC";
        }
        if (type.equals(Type.MNC)) {
            return "MNC";
        }
        if (type.equals(Type.MX)) {
            return "MX";
        }
        if (type.equals(Type.MT)) {
            return "MT";
        }
        return "";
    }

}
