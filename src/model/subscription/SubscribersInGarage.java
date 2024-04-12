package model.subscription;

import model.parking.ParkingArea;
import model.parking.ParkingCategory;
import model.subscription.SubscriberVehicle;
import model.vehicle.Vehicle;
import support.VehicleFactory;
import java.util.ArrayList;
import java.time.LocalDateTime;
import model.price.Price;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class SubscribersInGarage {

    private VehicleFactory factory = new VehicleFactory();
    private Price pr = Price.getPrice();
    private ArrayList<SubscriberVehicle> subscriber = new ArrayList<SubscriberVehicle>();
    private ParkingCategory parking_spaces;

    public SubscribersInGarage(int max) {
        parking_spaces = new ParkingCategory(max, "S");
    }

    public boolean verifySubscriber(String plate) {
        boolean find = false;
        int i = 0;
        while (!find & (i + 1) <= subscriber.size()) {
            if (plate.equals(subscriber.get(i).getPlateVehicle())) {
                boolean removed = false;
                if (verifyDeadline(i)) {
                    removed = removeSubscriber(i);
                }
                if (!removed) {
                    find = true;
                } else {
                    return false;
                }
            } else {
                i++;
            }
        }
        return find;
    }

    public boolean requestNewSubscriber() {
        if (parking_spaces.checkFreeParking() || searchExpiredSubscription()) {
            return true;
        }
        return false;
    }

    public String newSubscriber(String plate, String category, boolean annual) {
        ParkingArea pa = parking_spaces.assignParking();
        if (pa != null) {
            double amnt = pr.getSubscriptionAmount(category, annual);
            SubscriberVehicle sv = new SubscriberVehicle(factory.getVehicle(category, plate), annual, pa, amnt);
            subscriber.add(sv);
            return pa.getId_parking();
        }
        return "";
    }

    private boolean verifyDeadline(int index) {
        LocalDateTime now = LocalDateTime.now();
        if (index >= 0) {
            if (now.isAfter(subscriber.get(index).getDeadline())) {
                System.out.println(now);
                System.out.println(subscriber.get(index).getDeadline());
                return true;
            }
        }
        return false;
    }

    private boolean removeSubscriber(int index) {
        boolean removed = false;
        if (index >= 0) {
            ParkingArea pa = subscriber.get(index).getParking();
            removed = parking_spaces.releasePaking(pa);
            subscriber.remove(index);
        }
        return removed;
    }

    private boolean searchExpiredSubscription() {
        boolean removed = false;
        int i = 0;
        while (!removed & (i + 1) < subscriber.size()) {
            if (verifyDeadline(i)) {
                removed = removeSubscriber(i);
            } else {
                i++;
            }
        }
        return removed;
    }

    public double returnAmount(String plate) {
        boolean find = false;
        int i = 0;
        while (!find & (i + 1) <= subscriber.size()) {
            if (plate.equals(subscriber.get(i).getPlateVehicle())) {
                find = true;
            } else {
                i++;
            }
        }
        return subscriber.get(i).getAmount();
    }

}
