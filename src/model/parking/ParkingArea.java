package model.parking;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ParkingArea {
    private String id_parking;
    private boolean occupied; 
    private String category; 
    //category: A, E, MX, MT, D, I

    public ParkingArea(String id_parking, boolean occupied, String category) {
        this.id_parking = id_parking;
        this.category = category;
        this.occupied = occupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public String getId_parking() {
        return id_parking;
    }

    public void setId_parking(String id_parking) {
        this.id_parking = id_parking;
    }

    @Override
    public String toString() {
        return "ParkingArea{" + "id_parking=" + id_parking + ", occupied=" + occupied + ", category=" + category + '}';
    }

    public String getCategory() {
        return category;
    }

    
}
