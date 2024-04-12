/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.garage;

import model.parking.ParkingArea;
import model.parking.ParkingCategory;
import model.subscription.SubscribersInGarage;
import model.vehicle.Vehicle;
import java.util.ArrayList;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class Garage {

    private SubscribersInGarage subscriber;
    private ArrayList<VehicleInGarage> parked_vehicles = new ArrayList<VehicleInGarage>();
    private ParkingCategory parking_A;
    private ParkingCategory parking_E;
    private ParkingCategory parking_MX;
    private ParkingCategory parking_MT;
    private ParkingCategory parking_D;
    private ParkingCategory parking_I;
    private int max;
    private int maxSubscriber;
    private int max_A, max_E, max_MX, max_MT, max_D, max_I;


    public Garage(int max, int maxSubscriber, int max_A, int max_E, int max_MX, int max_MT, int max_D, int max_I) {
        this.max = max;
        this.maxSubscriber = maxSubscriber;
        subscriber = new SubscribersInGarage(maxSubscriber);
        parking_A = new ParkingCategory(max_A, "A");
        parking_E = new ParkingCategory(max_E, "E");
        parking_MX = new ParkingCategory(max_MX, "MX");
        parking_MT = new ParkingCategory(max_MT, "MT");
        parking_D = new ParkingCategory(max_D, "D");
        parking_I = new ParkingCategory(max_I, "I");
        this.max_A = max_A;
        this.max_E = max_E;
        this.max_MX = max_MX;
        this.max_MT = max_MT;
        this.max_D = max_D;
        this.max_I = max_I;
    }

    public void refactorGarage() {
        subscriber = new SubscribersInGarage(maxSubscriber);
        parked_vehicles = new ArrayList<VehicleInGarage>();
        parking_A.refactorParkingCategory(max_A);
        parking_E.refactorParkingCategory(max_E);
        parking_MX.refactorParkingCategory(max_MX);
        parking_MT.refactorParkingCategory(max_MT);
        parking_D.refactorParkingCategory(max_D);
        parking_I.refactorParkingCategory(max_I);
    }

    public int EntryRequest(String plate) {
        if (subscriber.verifySubscriber(plate)) {
            System.out.println("La targa è associata ad un abbonamento");
            return 1;
        }
        if (findVehicle(plate) >= 0) {
            System.out.println("ERRORE: Il veicolo associato alla targa inserita è già all'interno del garage");
            return 2;
        }
        return 0;
    }

    public String EnterNewVehicle(String plate, String category, String subcategory) {
        boolean free = false;
        ParkingArea pa = null;
        if (category.equals("MX")) {
            free = parking_MX.checkFreeParking();
            if (free) {
                pa = parking_MX.assignParking();
            }
        } else if (category.equals("MT")) {
            free = parking_MT.checkFreeParking();
            if (free) {
                pa = parking_MT.assignParking();
            }
        } else if (category.equals("LC") || category.equals("MXC") || category.equals("MDC") || category.equals("MNC")) {
            if (subcategory.equals("E")) {
                free = parking_E.checkFreeParking();
                if (free) {
                    pa = parking_E.assignParking();
                }
            } else if (subcategory.equals("D")) {
                free = parking_D.checkFreeParking();
                if (free) {
                    pa = parking_D.assignParking();
                }
            } else if (subcategory.equals("I")) {
                free = parking_I.checkFreeParking();
                if (free) {
                    pa = parking_I.assignParking();
                }
            } else if (subcategory.equals("N")) {
                free = parking_A.checkFreeParking();
                if (free) {
                    pa = parking_A.assignParking();
                }
            }
        }
        if (free && pa != null) {
            VehicleInGarage new_vehicle = new VehicleInGarage(pa, plate, category);
            parked_vehicles.add(new_vehicle);
            return pa.getId_parking();
        }
        System.out.println("ERRORE: NON C'E' POSTO PER TE");
        return "";
    }

    public double Exit(String plate) {
        boolean exit = false;
        boolean is_subscriber = subscriber.verifySubscriber(plate);
        if (!is_subscriber) {
            int index = findVehicle(plate);
            if (index >= 0) {
                String category = parked_vehicles.get(index).getVehicle().getType();
                double amount = parked_vehicles.get(index).calcolateAmount(category);
                ParkingArea pa = parked_vehicles.get(index).exit();
                parked_vehicles.remove(index);
                exit = releaseParking(pa);
                if (exit) {
                    return amount;
                }
            }
        } else {
            return 0;
        }
        return -1;
    }

    private boolean releaseParking(ParkingArea pa) {
        if (pa.getCategory().equalsIgnoreCase("MX")) {
            return parking_MX.releasePaking(pa);
        } else if (pa.getCategory().equalsIgnoreCase("MT")) {
            return parking_MT.releasePaking(pa);
        } else if (pa.getCategory().equalsIgnoreCase("E")) {
            return parking_E.releasePaking(pa);
        } else if (pa.getCategory().equalsIgnoreCase("D")) {
            return parking_D.releasePaking(pa);
        } else if (pa.getCategory().equalsIgnoreCase("I")) {
            return parking_I.releasePaking(pa);
        } else if (pa.getCategory().equalsIgnoreCase("A")) {
            return parking_A.releasePaking(pa);
        }
        return false;
    }

    public int modifyNParking(String category, String subcategory, int n) {
        boolean modifiable = false;
        if (category.equals("MX")) {
            modifiable = parking_MX.modifyMax(n, category);
        } else if (category.equals("MT")) {
            modifiable = parking_MT.modifyMax(n, category);
        } else if (subcategory.equals("D")) {
            modifiable = parking_D.modifyMax(n, subcategory);
        } else if (subcategory.equals("E")) {
            modifiable = parking_E.modifyMax(n, subcategory);
        } else if (subcategory.equals("I")) {
            modifiable = parking_I.modifyMax(n, subcategory);
        } else if (subcategory.equals("N")) {
            modifiable = parking_A.modifyMax(n, "A");
        }
        if (!modifiable) {
            return -1;
        } else if (verifyNParking()) {
            return 0;
        }
        return 1;
    }

    public boolean verifyNParking() {
        return (max == (parking_A.getMax() + parking_E.getMax() + parking_MX.getMax() + parking_MT.getMax() + parking_D.getMax() + parking_I.getMax()));
        }

    public boolean setMax(int max) {
        if (max >= 6 + (parking_A.getN_occupiedForMax() + parking_E.getN_occupiedForMax() + parking_MX.getN_occupiedForMax() + parking_MT.getN_occupiedForMax() + parking_D.getN_occupiedForMax() + parking_I.getN_occupiedForMax())) {
            this.max = max;
            return true;
        }
        return false;
    }

    public boolean SubscriptionRequest(String plate) {
        return subscriber.requestNewSubscriber();
    }

    public String newSubscription(String plate, String category, boolean annual) {
        return subscriber.newSubscriber(plate, category, annual);
    }

    public double amountSubscription(String plate) {
        return subscriber.returnAmount(plate);
    }

    private int findVehicle(String plate) {
        int find = -1;
        int i = 0;
        while (i < parked_vehicles.size() & find < 0) {
            if (plate.equals(parked_vehicles.get(i).getPlateVehicle())) {
                find = i;
            } else {
                i++;
            }
        }
        return find;
    }

    public boolean verifyVehicleInGarage(String plate) {
        return (findVehicle(plate) >= 0);

    }

    public boolean findSubscriber(String plate) {
        return subscriber.verifySubscriber(plate);
    }

    public int getMax() {
        return max;
    }

    public int getMaxMoto() {
        return parking_MT.getMax();
    }


}
