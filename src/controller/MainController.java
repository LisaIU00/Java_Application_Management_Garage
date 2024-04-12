package controller;


import support.Observer;
import model.garage.Garage;
import model.manager.Manager;
import view.ChooseNumParkingView;
import view.ChooseOptionView;
import view.ChoosePaymentMethodView;
import view.ChoosePricePlanView;
import view.ChooseSpecialVehicleView;
import view.ChooseSubscriptionView;
import view.ChooseVehicleView;
import view.ErrorDialogView;
import view.GarageMainView;
import view.InsertLicencePlateView;
import view.ManagerView;
import view.ModifyGarageView;
import view.ModifyParametersView;
import view.ModifyPasswordView;
import view.ModifyPriceView;
import view.ModifyUsernameView;
import view.NewTotalView;
import view.ShowAmountView;
import view.ShowParkingAreaView;
import view.SignInView;
import view.View;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class MainController implements Observer {

    private Garage garage;
    private Manager manager;
    private View currentView;
    private View previousView;
    private ValidityController ValidityController = new ValidityController();
    private static final ChooseNumParkingView ChooseNumParkingView = new ChooseNumParkingView(null);
    private static final ChooseOptionView ChooseOptionView = new ChooseOptionView(null);
    private static final ChoosePaymentMethodView ChoosePaymentMethodView = new ChoosePaymentMethodView(null);
    private static final ChoosePricePlanView ChoosePricePlanView = new ChoosePricePlanView(null);
    private static final ChooseSpecialVehicleView ChooseSpecialVehicleView = new ChooseSpecialVehicleView(null);
    private static final ChooseSubscriptionView ChooseSubscriptionView = new ChooseSubscriptionView(null);
    private static final ChooseVehicleView ChooseVehicleView = new ChooseVehicleView(null);
    private static final ErrorDialogView ErrorDialogView = new ErrorDialogView(null);
    private static final GarageMainView GarageMainView = new GarageMainView(null);
    private static final InsertLicencePlateView InsertLicencePlateView = new InsertLicencePlateView(null);
    private static final ManagerView ManagerView = new ManagerView(null);
    private static final ModifyGarageView ModifyGarageView = new ModifyGarageView(null);
    private static final ModifyParametersView ModifyParametersView = new ModifyParametersView(null);
    private static final ModifyPasswordView ModifyPasswordView = new ModifyPasswordView(null);
    private static final ModifyPriceView ModifyPriceView = new ModifyPriceView(null);
    private static final ModifyUsernameView ModifyUsernameView = new ModifyUsernameView(null);
    private static final NewTotalView NewTotalView = new NewTotalView(null);
    private static final ShowAmountView ShowAmountView = new ShowAmountView(null);
    private static final ShowParkingAreaView ShowParkingAreaView = new ShowParkingAreaView((Object) null);
    private static final SignInView SignInView = new SignInView(null);

    private String plate;
    private String category;
    private String subcategory = "";
    private boolean annual;
    private boolean subscription;
    private String parking = "";
    private boolean exit = false;

    private String plane;

    public MainController(Garage garage, Manager manager, View view) {
        this.garage = garage;
        this.manager = manager;
        this.currentView = view;
        this.previousView = null;
    }

    public MainController(View view) {
        this.currentView = view;
        this.previousView = null;
    }

    /**
     *
     * @param view
     * @param action
     */
    @Override
    public void retriveData(View view, String action) {
        if (view.getClass() == GarageMainView.getClass()) {
            if (action.equals("signin")) {
                currentView = new SignInView();
                ((SignInView) currentView).addController(this);
            } else if (action.equals("enter") || action.equals("exit")) {
                if (action.equals("exit")) {
                    exit = true;
                }
                currentView = new InsertLicencePlateView();
                ((InsertLicencePlateView) currentView).addController(this);
            }

        } else if (view.getClass() == ErrorDialogView.getClass()) {
            errorDialog(action);

        } else if (view.getClass() == InsertLicencePlateView.getClass()) {
            if (action.equals("plate")) {
                plate = (((InsertLicencePlateView) view).getPlate());
                if (!exit) {
                    entryRequest(plate);
                } else {
                    currentView = new ChoosePaymentMethodView();
                    ((ChoosePaymentMethodView) currentView).addController(this);
                }
            }

        } else if (view.getClass() == ChooseOptionView.getClass()) {
            if (action.equals("submit")) {
                subscription = true;
                currentView = new ChooseSubscriptionView();
                ((ChooseSubscriptionView) currentView).addController(this);
            } else if (action.equals("enterGarage")) {
                subscription = false;
                previousView = view;
                currentView = new ChooseVehicleView();
                ((ChooseVehicleView) currentView).addController(this);
            }

        } else if (view.getClass() == ChooseSubscriptionView.getClass()) {
            if (action.equals("typeSubmit")) {
                this.annual = ((ChooseSubscriptionView) currentView).isAnnual();
                boolean request = garage.SubscriptionRequest(plate);
                if (!request) {
                    currentView = new ErrorDialogView(null);
                    ((ErrorDialogView) currentView).addController(this);
                    ((ErrorDialogView) currentView).runOutSubscription();
                } else {
                    previousView = view;
                    currentView = new ChooseVehicleView();
                    ((ChooseVehicleView) currentView).addController(this);
                }
            }
        } else if (view.getClass() == ChooseVehicleView.getClass()) {
            boolean noError = true;
            if (action.equals("category")) {
                category = ((ChooseVehicleView) view).getCategory();
                if(previousView.getClass() == ChoosePricePlanView.getClass()){
                    chooseVehicle();
                }else if (category.equals("LC") || category.equals("MNC") || category.equals("MXC") || category.equals("MDC")) {
                    if (previousView.getClass() == ChooseOptionView.getClass() || previousView.getClass() == ChooseSubscriptionView.getClass()) {
                        if (!ValidityController.checkPlateAuto(plate)) {
                            currentView = new ErrorDialogView(null);
                            ((ErrorDialogView) currentView).addController(this);
                            ((ErrorDialogView) currentView).wrongPlate();
                            noError = false;
                        }
                    }
                    if (noError) {
                        currentView = new ChooseSpecialVehicleView();
                        ((ChooseSpecialVehicleView) currentView).addController(this);
                    }
                } else if (category.equals("MT")) {
                    if (previousView.getClass() == ChooseOptionView.getClass() || previousView.getClass() == ChooseSubscriptionView.getClass()) {
                        if (!ValidityController.checkPlateMoto(plate)) {
                            currentView = new ErrorDialogView(null);
                            ((ErrorDialogView) currentView).addController(this);
                            ((ErrorDialogView) currentView).wrongPlate();
                            noError = false;
                        }
                    }
                    if (noError) {
                        chooseVehicle();
                    }
                } else {
                    chooseVehicle();
                }
            }
        } else if (view.getClass() == ChooseSpecialVehicleView.getClass()) {
            if (action.equals("subcategory")) {
                subcategory = ((ChooseSpecialVehicleView) view).getCategory();
                chooseVehicle();
            }

        } else if (view.getClass() == ChoosePaymentMethodView.getClass()) {
            if (action.equals("paymentMethod")) {
                double amount = -1;
                if (!exit) {
                    amount = garage.amountSubscription(plate);
                } else {
                    amount = garage.Exit(plate);
                }
                currentView = new ShowAmountView(amount);
                ((ShowAmountView) currentView).addController(this);
                ((ShowAmountView) currentView).showAmountConfirm();
            }

        } else if (view.getClass() == ShowAmountView.getClass()) {
            if (action.equals("amount")) {
                if (exit) {
                    currentView = new GarageMainView(parking);
                    ((GarageMainView) currentView).addController(this);
                    exit = false;
                } else if (previousView.getClass() == ChooseSubscriptionView.getClass()) {
                    currentView = new ShowParkingAreaView(parking);
                    ((ShowParkingAreaView) currentView).addController(this);
                    ((ShowParkingAreaView) currentView).showParkingConfirm();
                    currentView = new GarageMainView(parking);
                    ((GarageMainView) currentView).addController(this);
                }
            }
            subcategory = "";

        } else if (view.getClass() == SignInView.getClass()) {
            if (action.equals("submitData")) {
                boolean correct = manager.SignIn(((SignInView) currentView).getUsername(), ((SignInView) currentView).getPassword());
                if (correct) {
                    currentView = new ManagerView();
                    ((ManagerView) currentView).addController(this);
                } else {
                    currentView = new ErrorDialogView(parking);
                    ((ErrorDialogView) currentView).addController(this);
                    ((ErrorDialogView) currentView).errorSignIn();
                }
            } else if (action.equals("goback")) {
                currentView = new GarageMainView(parking);
                ((GarageMainView) currentView).addController(this);
            }

        } else if (view.getClass() == ManagerView.getClass()) {
            switch (action) {
                case "modifyPrice":
                    currentView = new ChoosePricePlanView();
                    ((ChoosePricePlanView) currentView).addController(this);
                    break;
                case "modifyGarage":
                    currentView = new ModifyGarageView();
                    ((ModifyGarageView) currentView).addController(this);
                    break;
                case "modifyParameters":
                    currentView = new ModifyParametersView();
                    ((ModifyParametersView) currentView).addController(this);
                    break;
                case "exit":
                    currentView = new GarageMainView(parking);
                    ((GarageMainView) currentView).addController(this);
                    break;
                default:
                    break;
            }

        } else if (view.getClass() == ModifyParametersView.getClass()) {
            switch (action) {
                case "psw":
                    currentView = new ModifyPasswordView();
                    ((ModifyPasswordView) currentView).addController(this);
                    break;
                case "user":
                    currentView = new ModifyUsernameView();
                    ((ModifyUsernameView) currentView).addController(this);
                    break;
                case "goback":
                    currentView = new ManagerView();
                    ((ManagerView) currentView).addController(this);
                    break;
                default:
                    break;
            }

        } else if (view.getClass() == ModifyPasswordView.getClass()) {
            if (action.equals("psw")) {
                if (ValidityController.checkSignInParameter(((ModifyPasswordView) view).getPsw())) {
                    manager.setPassword(((ModifyPasswordView) view).getPsw());
                    currentView = new ManagerView(parking);
                    ((ManagerView) currentView).addController(this);
                } else {
                    currentView = new ErrorDialogView(parking);
                    ((ErrorDialogView) currentView).addController(this);
                    ((ErrorDialogView) currentView).errorPsw();
                }
            }

        } else if (view.getClass() == ModifyUsernameView.getClass()) {
            if (action.equals("user")) {
                if (ValidityController.checkSignInParameter(((ModifyUsernameView) view).getUser())) {
                    manager.setPassword(((ModifyUsernameView) view).getUser());
                    currentView = new ManagerView(parking);
                    ((ManagerView) currentView).addController(this);
                } else {
                    currentView = new ErrorDialogView(parking);
                    ((ErrorDialogView) currentView).addController(this);
                    ((ErrorDialogView) currentView).errorPsw();
                }
            }

        } else if (view.getClass() == ChoosePricePlanView.getClass()) {
            if (action.equals("plan")) {
                this.plane = ((ChoosePricePlanView) view).getPlan();
                this.previousView = view;
                currentView = new ChooseVehicleView();
                ((ChooseVehicleView) currentView).addController(this);
            }

        } else if (view.getClass() == ModifyPriceView.getClass()) {
            if (action.equals("price")) {
                if (ValidityController.checkGreaterZero(((ModifyPriceView) view).getPrice())) {
                    currentView = new ErrorDialogView(parking);
                    ((ErrorDialogView) currentView).addController(this);
                    ((ErrorDialogView) currentView).errorPrice();
                } else {
                    boolean modifiable = manager.modifyPrice(category, plane, ((ModifyPriceView) view).getPrice());
                    if (!modifiable) {
                        currentView = new ErrorDialogView(parking);
                        ((ErrorDialogView) currentView).addController(this);
                        ((ErrorDialogView) currentView).errorPrice();
                    } else {
                        System.out.println("Piano tariffario " + this.plane + " del tipo di veicolo " + this.category + "modificato");
                        currentView = new ManagerView(parking);
                        ((ManagerView) currentView).addController(this);
                    }
                }
            }

        } else if (view.getClass() == ModifyGarageView.getClass()) {
            this.previousView = view;
            if (action.equals("ristruttura")) {
                currentView = new NewTotalView();
                ((NewTotalView) currentView).addController(this);
            } else if (action.equals("riorganizza")) {
                currentView = new ChooseVehicleView();
                ((ChooseVehicleView) currentView).addController(this);
            }

        } else if (view.getClass() == NewTotalView.getClass()) {
            if (action.equals("newtotal")) {
                boolean modifiable = garage.setMax(((NewTotalView) view).getMax());
                if (modifiable) {
                    currentView = new ChooseVehicleView();
                    ((ChooseVehicleView) currentView).addController(this);
                } else {
                    currentView = new ErrorDialogView(null);
                    ((ErrorDialogView) currentView).addController(this);
                    ((ErrorDialogView) currentView).errorSetMax();
                }
            }

        } else if (view.getClass() == ChooseNumParkingView.getClass()) {
            if (action.equals("newnumparking")) {
                int num = ((ChooseNumParkingView) view).getNum();
                int result = garage.modifyNParking(category, subcategory, num);
                switch (result) {
                    case -1: //numero inserito non corretto
                        currentView = new ErrorDialogView(null);
                        ((ErrorDialogView) currentView).addController(this);
                        ((ErrorDialogView) currentView).numParking();
                        break;
                    case 0: //modifica andata a buon fine
                        currentView = new ManagerView();
                        ((ManagerView) currentView).addController(this);
                        break;
                    case 1: //il n° di posti totale non corrisponde alla somma dei n° di posti totale
                        currentView = new ErrorDialogView(null);
                        ((ErrorDialogView) currentView).addController(this);
                        ((ErrorDialogView) currentView).notCorresponding();
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public void errorDialog(String action) {
        if (action.equals("errorPlate") || action.equals("fullParking") || action.equals("runOutSubscription") || action.equals("subscriberPlate")) {
            currentView = new GarageMainView(parking);
            ((GarageMainView) currentView).addController(this);
        } else if (action.equals("wrongplate")) {
            currentView = new InsertLicencePlateView(parking);
            ((InsertLicencePlateView) currentView).addController(this);
        } else if (action.equals("errorSignIn")) {
            currentView = new SignInView(parking);
            ((SignInView) currentView).addController(this);
        } else if (action.equals("errorPsw")) {
            currentView = new ModifyPasswordView(parking);
            ((ModifyPasswordView) currentView).addController(this);
        } else if (action.equals("errorUsername")) {
            currentView = new ModifyUsernameView(parking);
            ((ModifyUsernameView) currentView).addController(this);
        } else if (action.equals("errorPrice")) {
            currentView = new ManagerView(parking);
            ((ManagerView) currentView).addController(this);
        } else if (action.equals("errorSetMax")) {
            currentView = new NewTotalView(parking);
            ((NewTotalView) currentView).addController(this);
        } else if (action.equals("numParking")) {
            if (garage.verifyNParking()) {
                currentView = new ModifyGarageView();
                ((ModifyGarageView) currentView).addController(this);
            } else {
                currentView = new ErrorDialogView(null);
                ((ErrorDialogView) currentView).addController(this);
                ((ErrorDialogView) currentView).notCorresponding();
            }
        } else if (action.equals("notCorresponding")) {
            currentView = new ChooseVehicleView(parking);
            ((ChooseVehicleView) currentView).addController(this);
        }
    }

    public void entryRequest(String plate) {
        boolean validity = ValidityController.checkPlate(plate);
        int result = -1;
        if (!validity) {
            currentView = new ErrorDialogView(null);
            ((ErrorDialogView) currentView).addController(this);
            ((ErrorDialogView) currentView).wrongPlate();
        } else {
            result = garage.EntryRequest(plate);
            if (result == 0) {
                currentView = new ChooseOptionView();
                ((ChooseOptionView) currentView).addController(this);
            } else {
                currentView = new ErrorDialogView(null);
                ((ErrorDialogView) currentView).addController(this);
                if (result == 1) {
                    ((ErrorDialogView) currentView).subscriberPlate();

                } else {
                    ((ErrorDialogView) currentView).errorPlate();
                }
            }
        }
    }

    public void chooseVehicle() {
        if (previousView.getClass() == ChooseOptionView.getClass()) {
            parking = garage.EnterNewVehicle(plate, category, subcategory);
            if (parking.equals("")) {
                currentView = new ErrorDialogView(null);
                ((ErrorDialogView) currentView).addController(this);
                ((ErrorDialogView) currentView).fullParking();

            } else {
                currentView = new ShowParkingAreaView(parking);
                ((ShowParkingAreaView) currentView).addController(this);
                ((ShowParkingAreaView) currentView).showParkingConfirm();
                currentView = new GarageMainView(parking);
                ((GarageMainView) currentView).addController(this);
            }
        } else if (previousView.getClass() == ChooseSubscriptionView.getClass()) {
            parking = garage.newSubscription(plate, category, annual);
            if (parking.equals("")) {
                currentView = new ErrorDialogView(null);
                ((ErrorDialogView) currentView).addController(this);
                ((ErrorDialogView) currentView).runOutSubscription();
            } else {
                currentView = new ChoosePaymentMethodView(parking);
                ((ChoosePaymentMethodView) currentView).addController(this);
                ((ChoosePaymentMethodView) currentView).paymentMethodClicked();
            }
        } else if (previousView.getClass() == ChoosePricePlanView.getClass()) {
            currentView = new ModifyPriceView(null);
            ((ModifyPriceView) currentView).addController(this);

        } else if (previousView.getClass() == ModifyGarageView.getClass()) {
            currentView = new ChooseNumParkingView(null);
            ((ChooseNumParkingView) currentView).addController(this);
        }
    }

    public View getCurrentView() {
        return currentView;
    }

    public String getPlate() {
        return plate;
    }

    @Override
    public String toString() {
        return "MainController{" + garage.toString() + '}';
    }

}