package model.subscription;

import java.time.LocalDateTime;
import model.vehicle.Vehicle;
import model.parking.ParkingArea;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class SubscriberVehicle {
    private boolean annual; 
    private LocalDateTime deadline; 
    private ParkingArea parking; 
    private Vehicle vehicle;
    private double amount;

    public SubscriberVehicle(Vehicle vehicle, boolean annual, ParkingArea parking, double amount) {
        this.annual = annual;
        this.deadline = LocalDateTime.now();
        this.parking = parking;
        this.vehicle = vehicle;
        if(annual){
            deadline = deadline.plusYears(1);
        }else{
            deadline = deadline.plusMonths(1);
        }
        this.amount=amount;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public ParkingArea getParking() {
        return parking;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    
    public String getPlateVehicle(){
        return vehicle.getPlate();
    }

    public double getAmount() {
        return amount;
    }
    
}