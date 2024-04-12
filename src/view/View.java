package view;

/**
 *
 * @author Lisa Innocenti Uccini & Giulia Paone
 */
public interface View {
    public void displayText();
    public default void displayButton(String type){
        System.out.println("Button: "+type);
    }
}
