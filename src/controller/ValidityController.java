package controller;


import java.util.regex.*;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public class ValidityController {

    public ValidityController() {}
    
    public boolean checkPlate(String plate){
        return plate.length()==7;
    }
    
    public boolean checkPlateAuto(String plate){
        plate = plate.toUpperCase();
        return Pattern.matches("[A-Z]{2}[0-9]{3}[A-Z]{2}", plate);
    }
    
    public boolean checkPlateMoto(String plate){
        plate = plate.toUpperCase();
        return Pattern.matches("[A-Z]{2}[0-9]{5}", plate);
    }
    
    public boolean checkSignInParameter(String parameter){
        return parameter.trim().length()>=4 && parameter.trim().length()<=16 && parameter.matches("[a-zA-Z0-9$%&!?,-_;:°#]*") && parameter.matches("[a-zA-Z0-9$%&!?,-_;:°#]*");
    }
    
    public boolean checkGreaterZero(double value){
        return value>0;
    }
    
    
}

