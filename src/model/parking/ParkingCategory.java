package model.parking;

import java.util.ArrayList;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ParkingCategory {

    private int max;
    private String category;
    private int n_occupied;
    private ArrayList<ParkingArea> parking_spaces = new ArrayList<ParkingArea>();

    public ParkingCategory(int max, String category) {
        this.max = max;
        this.category = category;
        n_occupied = 0;
        for (int i = 0; i < max; i++) {
            parking_spaces.add(new ParkingArea(category + i, false, category));
        }
    }

    public void refactorParkingCategory(int oldmax) {
        n_occupied = 0;
        for (int i = 0; i < max; i++) {
            if (parking_spaces.get(i).isOccupied()) {
                parking_spaces.get(i).setOccupied(false);
            }
        }
        if (max < oldmax) {
            for (int i = max; i < oldmax; i++) {
                parking_spaces.add(new ParkingArea(category + i, false, category));
            }
        } else {
            for (int i = max-1; i >= oldmax; i--) {
                parking_spaces.remove(i);
            }
        }
        max = oldmax;

    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getN_occupiedForMax() {
        if (n_occupied > 1) {
            return n_occupied - 1;
        }
        return 0;
    }

    public boolean checkFreeParking() {
        return (n_occupied < max);
    }

    public ParkingArea assignParking() {
        if (checkFreeParking()) {
            boolean find = false;
            int i = 0;
            while (!find & (i + 1) <= parking_spaces.size()) {
                if (!parking_spaces.get(i).isOccupied()) {
                    find = true;
                    parking_spaces.get(i).setOccupied(true);
                    n_occupied = n_occupied + 1;
                } else {
                    i++;
                }
            }
            return parking_spaces.get(i);
        } else {
            return null;
        }
    }

    public boolean releasePaking(ParkingArea pa) {
        boolean find = false;
        int i = 0;
        while ((i + 1) < max & !find) {
            if (pa.getId_parking().equals(parking_spaces.get(i).getId_parking())) {
                find = true;
                parking_spaces.get(i).setOccupied(false);
            } else {
                i++;
            }
        }
        return find;
    }

    private boolean consentModify(int n) {
        return n_occupied <= n;
    }

    public boolean modifyMax(int n, String category) {
        boolean modifiable = consentModify(n);
        if (modifiable) {
            if (n > max) {
                for (int i = max; i < n; i++) {
                    parking_spaces.add(new ParkingArea(category + i, false, category));
                }
            } else if (n < max) {
                int index = 0;
                int removed = 0;
                while ((index + 1) < parking_spaces.size() & removed < (max - n)) {
                    if (!parking_spaces.get(index).isOccupied()) {
                        parking_spaces.remove(index);
                        removed++;
                    } else {
                        index++;
                    }
                }
            }
            max = n;
            redeployIdParking(category);
        }
        return modifiable;
    }

    private void redeployIdParking(String category) {
        for (int i = 0; i < max; i++) {
            parking_spaces.get(i).setId_parking(category + i);
        }
    }
}
