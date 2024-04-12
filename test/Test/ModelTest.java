package Test;

import controller.ValidityController;
import controller.ValidityController;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import model.garage.Garage;
import model.manager.Manager;
import model.parking.ParkingCategory;
import model.vehicle.Vehicle;
import support.VehicleFactory;
import java.util.concurrent.TimeUnit;
import org.junit.BeforeClass;
import model.price.Price;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ModelTest {

    static Garage garage;
    static Manager manager;
    static VehicleFactory factory;
    static ValidityController vc;
    static ParkingCategory pc;
    static Price pr;

    @BeforeClass
    public static void setUpClass() {
        garage = new Garage(18, 2, 3, 3, 3, 3, 3, 3);
        manager = new Manager("GiuLi", "GiuLi");
        factory = new VehicleFactory();
        vc = new ValidityController();
        pr = Price.getPrice();
    }

    @AfterClass
    public static void TearDownClass() {
        garage = null;
        manager = null;
        vc = null;
        pr.refactor();
    }

    @Before
    public void setUp() {
        garage.refactorGarage();
    }

    @Test
    public void testCalculateAmount() {
        System.out.println("TEST CALCULATE AMOUNT");
        String plate = "GP21231";
        String category = "MT";
        String parking_area = garage.EnterNewVehicle(plate, category, "");
        int amount = (int) garage.Exit(plate);
        assertEquals((int) pr.getPrice_first_hour(category), amount);
    }

    @Test
    public void testAssignParking() {
        System.out.println("TEST ASSIGN PARKING");
        String plate = "GP21212";
        String category = "MT";
        String parking_area = garage.EnterNewVehicle(plate, category, "");
        String correct_parking = "MT0";
        assertEquals(correct_parking, parking_area);
    }

    @Test
    public void testModifyMaxParking() {
        int max_old = garage.getMax();

        System.out.println("TEST MODIFY MAX PARKING UNSUCCESSFUL");
        String plate1 = "GP212LU";
        String category1 = "MX";
        String plate2 = "GP212LI";
        String category2 = "MX";
        String parking_area1 = garage.EnterNewVehicle(plate1, category1, "D");
        String parking_area2 = garage.EnterNewVehicle(plate2, category2, "I");

        assertFalse(garage.setMax(2));
        assertFalse(garage.setMax(6));
        assertEquals(max_old, garage.getMax());
    }

    @Test
    public void testCreateVehicle() {
        System.out.println("TEST CREATE VEHICLE");
        String plate = "LU345NA";
        String category = "MX";
        Vehicle v = factory.getVehicle(category, plate);
        Vehicle maxi_test = new Vehicle(plate, Vehicle.Type.MX);

        assertEquals(maxi_test.getClass(), v.getClass());
    }

    @Test
    public void testCheckPlateSuccessfull() {
        System.out.println("TEST TO VERIFY THE CHECK PLATE METHOD WITH RIGHT PLATE");
        String plate = "FE789VC";
        assertTrue(vc.checkPlateAuto(plate));
    }

    @Test
    public void testCheckWrongPlate() {
        System.out.println("TEST TO VERIFY THE CHECK PLATE METHOD WITH WRONG PLATE");
        String plate = "FE78954";
        assertFalse(vc.checkPlateAuto(plate));
    }

}
