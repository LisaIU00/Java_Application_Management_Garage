package model.garage;

import model.parking.ParkingArea;
import model.vehicle.Vehicle;
import support.VehicleFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import model.price.Price;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class VehicleInGarage {
    private ParkingArea parking;
    private Vehicle vehicle;
    private LocalDateTime  enter_time;
    private VehicleFactory factory = new VehicleFactory();
    private Price pr = Price.getPrice();

    public VehicleInGarage(ParkingArea parking, String plate, String category) {
        enter(parking, plate, category);
    }
    
    public void enter(ParkingArea parking, String plate, String category){
        this.parking = parking;
        vehicle = factory.getVehicle(category, plate);
        enter_time = LocalDateTime.now();
    }
    public ParkingArea exit(){
        return parking;
    }
    
    public double calcolateAmount(String category){
        
        double amount = pr.getPrice_first_hour(category);
        LocalDateTime exit_time = LocalDateTime.now();
        
        long minutes;
        minutes = ChronoUnit.MINUTES.between(enter_time, exit_time);
       
        if(minutes>=1440){
            amount = (1+minutes/1440)*pr.getPrice_day(category);
        }else{
            if(minutes>60)
                amount=(1+minutes/60)*pr.getPrice_hour(category);
        }        
        return amount;
    }

    public Vehicle getVehicle() {
        return vehicle;
        
    }
    
    public String getPlateVehicle(){
        return vehicle.getPlate();
    }
}
