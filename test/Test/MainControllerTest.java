package Test;

import controller.MainController;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import controller.MainController;
import model.garage.Garage;
import model.manager.Manager;
import model.price.Price;
import view.ChooseNumParkingView;
import view.ChooseOptionView;
import view.ChoosePricePlanView;
import view.ChooseSpecialVehicleView;
import view.ChooseSubscriptionView;
import view.ChooseVehicleView;
import view.GarageMainView;
import view.InsertLicencePlateView;
import view.ManagerView;
import view.ModifyGarageView;
import view.ModifyPriceView;
import view.SignInView;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class MainControllerTest {

    static Garage garage;
    static Manager manager;
    static MainController controller;

    @BeforeClass
    public static void setUpClass() {
        garage = new Garage(18, 2, 3, 3, 3, 3, 3, 3);
        manager = new Manager("GiuLi", "GiuLi");
    }

    @AfterClass
    public static void TearDownClass() {
        garage = null;
        manager = null;
        controller = null;
    }

    @Before
    public void setUp() {
        garage.refactorGarage();
        GarageMainView gmv = new GarageMainView();
        controller = new MainController(garage, manager, gmv);
        gmv.addController(controller);
        
    }

    @Test
    public void testCreateSubscription() {
        System.out.println("TEST SUSCRIPTION CREATED SUCCESSFULLY");
        String plate = "GL243YV";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate);
        ((ChooseOptionView) controller.getCurrentView()).createSubmitClicked();
        ((ChooseSubscriptionView) controller.getCurrentView()).chooseSubscriptionConfirm("annual");
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("auto luxury");
        ((ChooseSpecialVehicleView) controller.getCurrentView()).insertSubcategoryConfirm("elettrica");

        GarageMainView viewTest = new GarageMainView();
        assertEquals(viewTest.getClass(), controller.getCurrentView().getClass());
        assertTrue(garage.findSubscriber(plate));
        System.out.println();
    }

    @Test
    public void testCreateSubscriptionSamePlate() {
        System.out.println("TEST CREATE SUBSCRIPTION WITH PLATE ALREADY SUBSCRIBED");
        String plate1 = "GL243YV";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate1);
        ((ChooseOptionView) controller.getCurrentView()).createSubmitClicked();
        ((ChooseSubscriptionView) controller.getCurrentView()).chooseSubscriptionConfirm("annual");
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("auto luxury");
        ((ChooseSpecialVehicleView) controller.getCurrentView()).insertSubcategoryConfirm("elettrica");

        String plate2 = "KE897FD";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate2);
        ((ChooseOptionView) controller.getCurrentView()).createSubmitClicked();
        ((ChooseSubscriptionView) controller.getCurrentView()).chooseSubscriptionConfirm("monthly");
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("auto media");
        ((ChooseSpecialVehicleView) controller.getCurrentView()).insertSubcategoryConfirm("");

        String plate3 = "DO345GP";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate3);
        ((ChooseOptionView) controller.getCurrentView()).createSubmitClicked();
        ((ChooseSubscriptionView) controller.getCurrentView()).chooseSubscriptionConfirm("annual");

        GarageMainView viewTest = new GarageMainView();
        assertEquals(viewTest.getClass(), controller.getCurrentView().getClass());
        assertTrue(garage.findSubscriber(plate1));
        assertTrue(garage.findSubscriber(plate2));
        assertFalse(garage.findSubscriber(plate3));
        System.out.println();
    }

    @Test
    public void testRunOutSubscription() {
        System.out.println("TEST RUN OUT SUBSCRIPTION");
        String plate = "GL243YV";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate);
        ((ChooseOptionView) controller.getCurrentView()).createSubmitClicked();
        ((ChooseSubscriptionView) controller.getCurrentView()).chooseSubscriptionConfirm("annual");
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("auto luxury");
        ((ChooseSpecialVehicleView) controller.getCurrentView()).insertSubcategoryConfirm("elettrica");

        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate);

        GarageMainView viewTest = new GarageMainView();
        assertEquals(viewTest.getClass(), controller.getCurrentView().getClass());
        System.out.println();
    }

    @Test
    public void testEnterGarageSuccessfull() {
        System.out.println("TEST ENTER GARAGE SUCCESSFULLY");
        String plate = "GI212LU";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate);
        ((ChooseOptionView) controller.getCurrentView()).enterGarageClicked();
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("auto mini");
        ((ChooseSpecialVehicleView) controller.getCurrentView()).insertSubcategoryConfirm("");

        GarageMainView viewTest = new GarageMainView();
        assertEquals(viewTest.getClass(), controller.getCurrentView().getClass());
        
        assertTrue(garage.verifyVehicleInGarage(plate));
        System.out.println();
    }

    @Test
    public void testEnterGarageWrongPlate() {
        System.out.println("TEST ENTER GARAGE WRONG PLATE");
        String plate = "GL1428L";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate);
        ((ChooseOptionView) controller.getCurrentView()).enterGarageClicked();
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("moto");

        InsertLicencePlateView viewTest = new InsertLicencePlateView();
        assertEquals(viewTest.getClass(), controller.getCurrentView().getClass());
        assertFalse(garage.verifyVehicleInGarage(plate));
        System.out.println();
    }

    @Test
    public void testEnterFullGarage() {

        System.out.println("TEST ENTER GARAGE WITH A FULL CATEGORY");
        String plate1 = "GL14285";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate1);
        ((ChooseOptionView) controller.getCurrentView()).enterGarageClicked();
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("moto");

        String plate2 = "YU45676";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate2);
        ((ChooseOptionView) controller.getCurrentView()).enterGarageClicked();
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("moto");

        String plate3 = "BT56834";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate3);
        ((ChooseOptionView) controller.getCurrentView()).enterGarageClicked();
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("moto");

        String plate4 = "WE34580";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate4);
        ((ChooseOptionView) controller.getCurrentView()).enterGarageClicked();
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("moto");

        GarageMainView viewTest = new GarageMainView();
        assertEquals(viewTest.getClass(), controller.getCurrentView().getClass());
        assertTrue(garage.verifyVehicleInGarage(plate1));
        assertTrue(garage.verifyVehicleInGarage(plate2));
        assertTrue(garage.verifyVehicleInGarage(plate3));
        assertFalse(garage.verifyVehicleInGarage(plate4)); 
        System.out.println();
    }

    @Test
    public void testModifyPricePlane() {
        System.out.println("TEST MODIFY PRICE OF TARIFF PLANE");
        double newPrice = 39;
        ((GarageMainView) controller.getCurrentView()).signInButtonClicked();
        ((SignInView) controller.getCurrentView()).submitButtonClicked("GiuLi", "GiuLi");
        ((ManagerView) controller.getCurrentView()).modifyPriceButtonClicked();
        ((ChoosePricePlanView) controller.getCurrentView()).choosePlanConfirm("day");
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("auto mini");
        ((ModifyPriceView) controller.getCurrentView()).insertPriceConfirm(7);

        Price p = Price.getPrice();

        ManagerView viewTest = new ManagerView();
        assertEquals(viewTest.getClass(), controller.getCurrentView().getClass());
        assertNotEquals(newPrice, p.getPrice_day("MNC"));
        System.out.println();
    }

    @Test
    public void testModifyWrongPricePlane() {
        System.out.println("TEST MODIFY WRONG PRICE OF TARIFF PLANE");
        double newPrice = 2;
        ((GarageMainView) controller.getCurrentView()).signInButtonClicked();
        ((SignInView) controller.getCurrentView()).submitButtonClicked("GiuLi", "GiuLi");
        ((ManagerView) controller.getCurrentView()).modifyPriceButtonClicked();
        ((ChoosePricePlanView) controller.getCurrentView()).choosePlanConfirm("year");
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("auto mini");
        ((ModifyPriceView) controller.getCurrentView()).insertPriceConfirm(2);

        Price p = Price.getPrice();

        ManagerView viewTest = new ManagerView();
        assertEquals(viewTest.getClass(), controller.getCurrentView().getClass());
        assertNotEquals(newPrice, p.getSubscriptionAmount("MNC", true));
        System.out.println();
    }

    @Test
    public void testModifyNegativePricePlane() {
        System.out.println("TEST MODIFY NEGATIVE PRICE OF TARIFF PLANE");
        double newPrice = -5;
        ((GarageMainView) controller.getCurrentView()).signInButtonClicked();
        ((SignInView) controller.getCurrentView()).submitButtonClicked("GiuLi", "GiuLi");
        ((ManagerView) controller.getCurrentView()).modifyPriceButtonClicked();
        ((ChoosePricePlanView) controller.getCurrentView()).choosePlanConfirm("day");
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("auto mini");
        ((ModifyPriceView) controller.getCurrentView()).insertPriceConfirm(newPrice);

        Price p = Price.getPrice();

        ManagerView viewTest = new ManagerView();
        assertEquals(viewTest.getClass(), controller.getCurrentView().getClass());
        assertNotEquals(newPrice, p.getSubscriptionAmount("MNC", true));
        System.out.println();
    }

    @Test
    public void testSignIn() {
        System.out.println("TEST SIGN IN");
        ((GarageMainView) controller.getCurrentView()).signInButtonClicked();
        ((SignInView) controller.getCurrentView()).submitButtonClicked("GiuLi", "GiuLi");

        ManagerView viewTest = new ManagerView();
        assertEquals(viewTest.getClass(), controller.getCurrentView().getClass());
        System.out.println();
    }

    @Test
    public void testSignInWrongPassword() {
        System.out.println("TEST SIGN IN WITH WRONG PASSWORD");
        ((GarageMainView) controller.getCurrentView()).signInButtonClicked();
        ((SignInView) controller.getCurrentView()).submitButtonClicked("GiuLi", "GiuLii");

        ManagerView viewTest = new ManagerView();
        assertNotEquals(viewTest.getClass(), controller.getCurrentView().getClass());
        assertNotEquals("GiuLii", manager.getPassword());
        assertEquals("GiuLi", manager.getUsername());
        System.out.println();
    }

    @Test
    public void testModifyMaxCategory() {
        System.out.println("TEST MODIFY MAX CATEGORY AND CHECK WITH MAX GARAGE");
        int newMaxMoto = 5;
        int newMaxMaxi = 2;

        ((GarageMainView) controller.getCurrentView()).signInButtonClicked();
        ((SignInView) controller.getCurrentView()).submitButtonClicked("GiuLi", "GiuLi");
        ((ManagerView) controller.getCurrentView()).modifyGarageButtonClicked();
        ((ModifyGarageView) controller.getCurrentView()).reorganizeButtonClicked();
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("moto");
        ((ChooseNumParkingView) controller.getCurrentView()).insertNumConfirm(newMaxMoto);
        
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("maxi");
        ((ChooseNumParkingView) controller.getCurrentView()).insertNumConfirm(newMaxMaxi);

        ChooseVehicleView viewTest = new ChooseVehicleView();
        assertEquals(viewTest.getClass(), controller.getCurrentView().getClass());
        assertFalse(garage.verifyNParking());
        System.out.println();
    }
    
    @Test
    public void testModifyMaxCategorySuccessfull() {
        System.out.println("TEST MODIFY MAX CATEGORY WITH A RIGHT NUMBER");
        String plate1 = "GL14285";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate1);
        ((ChooseOptionView) controller.getCurrentView()).enterGarageClicked();
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("moto");

        String plate2 = "YU45676";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate2);
        ((ChooseOptionView) controller.getCurrentView()).enterGarageClicked();
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("moto");

        int newMaxMoto = 5;
        int newMax = 1;
        
        ((GarageMainView) controller.getCurrentView()).signInButtonClicked();
        ((SignInView) controller.getCurrentView()).submitButtonClicked("GiuLi", "GiuLi");
        ((ManagerView) controller.getCurrentView()).modifyGarageButtonClicked();
        ((ModifyGarageView) controller.getCurrentView()).reorganizeButtonClicked();
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("moto");
        ((ChooseNumParkingView) controller.getCurrentView()).insertNumConfirm(newMaxMoto);
        
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("auto luxury");
        ((ChooseSpecialVehicleView) controller.getCurrentView()).insertSubcategoryConfirm("disabile");
        ((ChooseNumParkingView) controller.getCurrentView()).insertNumConfirm(newMax);
        
        ManagerView viewTest = new ManagerView();
        assertEquals(viewTest.getClass(), controller.getCurrentView().getClass());
        assertEquals(garage.getMaxMoto(), newMaxMoto);

        System.out.println();
    }

    @Test
    public void testErrorModifyMaxCategory() {
        System.out.println("TEST MODIFY MAX CATEGORY WITH A WRONG NUMBER");
        String plate1 = "GL14285";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate1);
        ((ChooseOptionView) controller.getCurrentView()).enterGarageClicked();
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("moto");

        String plate2 = "YU45676";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate2);
        ((ChooseOptionView) controller.getCurrentView()).enterGarageClicked();
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("moto");

        String plate3 = "KI09745";
        ((GarageMainView) controller.getCurrentView()).enterButtonClicked();
        ((InsertLicencePlateView) controller.getCurrentView()).insertPlateConfirm(plate3);
        ((ChooseOptionView) controller.getCurrentView()).enterGarageClicked();
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("moto");

        int newMax = 2;
        int oldMax = garage.getMaxMoto();

        ((GarageMainView) controller.getCurrentView()).signInButtonClicked();
        ((SignInView) controller.getCurrentView()).submitButtonClicked("GiuLi", "GiuLi");
        ((ManagerView) controller.getCurrentView()).modifyGarageButtonClicked();
        ((ModifyGarageView) controller.getCurrentView()).reorganizeButtonClicked();
        ((ChooseVehicleView) controller.getCurrentView()).insertCategoryConfirm("moto");
        ((ChooseNumParkingView) controller.getCurrentView()).insertNumConfirm(newMax);

        ModifyGarageView viewTest = new ModifyGarageView();
        assertEquals(viewTest.getClass(), controller.getCurrentView().getClass());
        assertNotEquals(oldMax, newMax);

        System.out.println();
    }
     

}
