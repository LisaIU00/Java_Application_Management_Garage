package support;

import model.vehicle.Vehicle;

/**
 *
 * @author l.uccini
 */
public class VehicleFactory {

    public Vehicle getVehicle(String category, String plate) {
        return switch (category) {
            case "LC" ->
                new Vehicle(plate, Vehicle.Type.LC);
            case "MXC" ->
                new Vehicle(plate, Vehicle.Type.MXC);
            case "MDC" ->
                new Vehicle(plate, Vehicle.Type.MDC);
            case "MNC" ->
                new Vehicle(plate, Vehicle.Type.MNC);
            case "MX" ->
                new Vehicle(plate, Vehicle.Type.MX);
            case "MT" ->
                new Vehicle(plate, Vehicle.Type.MT);
            default ->
                null;
        };
    }
}
